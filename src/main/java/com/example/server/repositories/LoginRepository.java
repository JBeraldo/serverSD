package com.example.server.repositories;

import com.example.server.exceptions.NotFoundException;
import com.example.server.auth.JwtTokenProvider;
import com.example.server.database.DatabaseService;
import com.example.server.exceptions.UnauthorizedUserException;
import com.example.server.models.User;
import com.example.server.package_data.response.login.LoginResponseData;
import com.example.server.packages.BasePackage;
import com.example.server.packages.login.LoginPackage;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.*;

public class LoginRepository {
    public BasePackage login(String action,String login_request) throws JsonProcessingException, NotFoundException, UnauthorizedUserException {
        LoginPackage request = LoginPackage.fromJson(login_request, LoginPackage.class);

        User user  = findByEmail(request.getData().getEmail());

        authUser(request.getData().getPassword(),user);

        String jwt = JwtTokenProvider.createJwt(user.getId().toString());
        LoginResponseData data = new LoginResponseData(jwt);
        return new BasePackage(action,data,false,"Logado com sucesso");
    }
    public void logout(){}

    public User findByEmail(String email) throws NotFoundException{
        Connection cnt = DatabaseService.getConnection();
        try {
            String sql = "SELECT * FROM users WHERE email like ?";
            PreparedStatement stmt = cnt.prepareStatement(sql);
            stmt.setString(1,email);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                throw new NotFoundException("Usuário não encontrado");
            }
            return new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void authUser(String password,User user) throws UnauthorizedUserException {
        if(!user.getPassword().equals(password)){
            throw new UnauthorizedUserException("Usuário não autenticado");
        }
    }
}
