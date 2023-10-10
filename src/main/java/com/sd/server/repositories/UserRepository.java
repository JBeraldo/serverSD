package com.sd.server.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.auth.JwtTokenProvider;
import com.sd.server.exceptions.NoSessionException;
import com.sd.server.exceptions.UnauthorizedUserException;
import com.sd.server.models.User;
import com.sd.server.packages.BasePackage;
import com.sd.server.packages.user.CreateUserRequestPackage;


public class UserRepository {

    UserDAO userDAO = new UserDAO();
    JWTSessionDAO jwtSessionDAO = new JWTSessionDAO();

    public BasePackage create(String action, String create_request) throws JsonProcessingException, UnauthorizedUserException, NoSessionException {
        CreateUserRequestPackage  request = CreateUserRequestPackage.fromJson(create_request, CreateUserRequestPackage.class);
        int user_id = JwtTokenProvider.getUserId(request.getData().getToken());
        this.validateUser(user_id);
        User user = new User(request.getData());
        userDAO.addUser(user);
        return new BasePackage(action,false,"Usuário cadastrado com sucesso");
    }

    private void validateUser(int user_id) throws UnauthorizedUserException, NoSessionException {
        if(!userDAO.isUserAdmin(user_id)){
            throw new UnauthorizedUserException();
        }
        if(!jwtSessionDAO.hasUserActiveSession(user_id)){
            throw new NoSessionException();
        }
    }
}

