package com.sd.server.repositories;

import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.Server;
import com.sd.server.exceptions.NotFoundException;
import com.sd.server.auth.JwtTokenProvider;
import com.sd.server.exceptions.UnauthorizedUserException;
import com.sd.server.exceptions.WrongCredentialsException;
import com.sd.server.models.JWTSession;
import com.sd.server.models.User;
import com.sd.server.package_data.response.login.LoginResponseData;
import com.sd.server.packages.BasePackage;
import com.sd.server.packages.login.LoginPackage;
import com.sd.server.packages.logout.LogoutPackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.threads.SocketThread;
import org.mindrot.jbcrypt.BCrypt;

import java.net.Socket;

public class LoginRepository {

    UserDAO userDAO = new UserDAO();
    JWTSessionDAO jwt_session_dao = new JWTSessionDAO();

    public BasePackage login(String action, String login_request) throws JsonProcessingException, NotFoundException, UnauthorizedUserException, WrongCredentialsException {
        LoginPackage request = LoginPackage.fromJson(login_request, LoginPackage.class);

        User user  = userDAO.getUserByEmail(request.getData().getEmail());

        authUser(request.getData().getPassword(),user);

        String jwt = JwtTokenProvider.createJwt(user.getId().toString(),user.isAdm());

        String remote_ip = SocketThread.ip;

        jwt_session_dao.addJWTSession(new JWTSession(jwt,user,remote_ip));

        LoginResponseData data = new LoginResponseData(jwt);
        return new BasePackage(action,data,false,"Logado com sucesso");
    }
    public BasePackage logout(String action, String logout_request) throws JsonProcessingException {
        LogoutPackage request = LogoutPackage.fromJson(logout_request, LogoutPackage.class);
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
