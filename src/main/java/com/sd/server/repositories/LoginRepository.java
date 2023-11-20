package com.sd.server.repositories;

import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.Exceptions.NotFoundException;
import com.sd.server.Exceptions.UnauthorizedUserException;
import com.sd.server.Exceptions.WrongCredentialsException;
import com.sd.server.Models.JWTSession;
import com.sd.server.Models.User;
import com.sd.server.Packages.data.request.login.LoginRequestData;
import com.sd.server.Packages.data.request.logout.LogoutRequestData;
import com.sd.server.Packages.data.response.login.LoginPackageData;
import com.sd.server.Packages.BasePackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginRepository {

    UserDAO userDAO = new UserDAO();
    JWTSessionDAO jwt_session_dao = new JWTSessionDAO();

    public BasePackage<LoginPackageData> login(String action, String login_request, String ip) throws JsonProcessingException, NotFoundException, UnauthorizedUserException, WrongCredentialsException {
        BasePackage<LoginRequestData> request = BasePackage.fromJson(login_request,LoginRequestData.class);

        User user  = userDAO.getUserByEmail(request.getData().getEmail());

        authUser(request.getData().getPassword(),user);

        String jwt = AuthRepository.createJwt(user.getId().toString(),user.isAdm());

        jwt_session_dao.addJWTSession(new JWTSession(jwt,user,ip));

        LoginPackageData data = new LoginPackageData(jwt);
        return new BasePackage<LoginPackageData>(action,data,false,"Logado com sucesso");
    }
    public BasePackage logout(String action, String logout_request) throws JsonProcessingException {
        BasePackage<LogoutRequestData> request = BasePackage.fromJson(logout_request,LogoutRequestData.class);
        String token = request.getData().getToken();
        jwt_session_dao.logout(token);
        return  new BasePackage(action,false,"Logout realizado com sucesso");
    }

    public void authUser(String password,User user) throws WrongCredentialsException {
        if(!BCrypt.checkpw(password,user.getPassword())){
            throw new WrongCredentialsException();
        }
    }
}
