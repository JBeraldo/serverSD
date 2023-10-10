package com.sd.server.repositories;

import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.exceptions.NotFoundException;
import com.sd.server.auth.JwtTokenProvider;
import com.sd.server.exceptions.UnauthorizedUserException;
import com.sd.server.models.JWTSession;
import com.sd.server.models.User;
import com.sd.server.package_data.response.login.LoginResponseData;
import com.sd.server.packages.BasePackage;
import com.sd.server.packages.login.LoginPackage;
import com.sd.server.packages.logout.LogoutPackage;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LoginRepository {

    UserDAO userDAO = new UserDAO();
    JWTSessionDAO jwt_session_dao = new JWTSessionDAO();

    public BasePackage login(String action, String login_request) throws JsonProcessingException, NotFoundException, UnauthorizedUserException {
        LoginPackage request = LoginPackage.fromJson(login_request, LoginPackage.class);

        User user  = userDAO.getUserByEmail(request.getData().getEmail());

        authUser(request.getData().getPassword(),user);

        String jwt = JwtTokenProvider.createJwt(user.getId().toString());

        jwt_session_dao.addJWTSession(new JWTSession(jwt,user));

        LoginResponseData data = new LoginResponseData(jwt);
        return new BasePackage(action,data,false,"Logado com sucesso");
    }
    public BasePackage logout(String action, String logout_request) throws JsonProcessingException {
        LogoutPackage request = LogoutPackage.fromJson(logout_request, LogoutPackage.class);
        Integer user_id = JwtTokenProvider.getUserId(request.getData().getToken());
        jwt_session_dao.deleteUserAllSessions(user_id);
        return  new BasePackage(action,false,"Logout realizado com sucesso");
    }

    public void authUser(String password,User user) throws UnauthorizedUserException {
        if(!user.getPassword().equals(password)){
            throw new UnauthorizedUserException();
        }
    }
}
