package com.sd.server.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.auth.JwtTokenProvider;
import com.sd.server.exceptions.EmailAlreadyUsedException;
import com.sd.server.exceptions.NoSessionException;
import com.sd.server.exceptions.UnauthorizedUserException;
import com.sd.server.models.User;
import com.sd.server.package_data.request.user.DeleteUserRequestData;
import com.sd.server.package_data.request.user.GetUserRequestData;
import com.sd.server.package_data.response.user.GetUserResponseData;
import com.sd.server.packages.BasePackage;
import com.sd.server.packages.user.CreateUserRequestPackage;
import com.sd.server.packages.user.DeleteUserRequestPackage;
import com.sd.server.packages.user.GetUserRequestPackage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;


public class UserRepository {

    UserDAO userDAO = new UserDAO();
    JWTSessionDAO jwtSessionDAO = new JWTSessionDAO();

    public BasePackage create(String action, String create_request) throws JsonProcessingException, UnauthorizedUserException, NoSessionException, EmailAlreadyUsedException {
        CreateUserRequestPackage  request = CreateUserRequestPackage.fromJson(create_request, CreateUserRequestPackage.class);
        int user_id = JwtTokenProvider.getUserId(request.getData().getToken());
        this.validateUser(user_id);
        User user = new User(request.getData());
        user.setPassword(hashUserPassw(user.getPassword()));
        userDAO.addUserIfNotExistByEmail(user);
        return new BasePackage(action,false,"Usuário cadastrado com sucesso");
    }

    public String hashUserPassw(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private void validateUser(int user_id) throws UnauthorizedUserException, NoSessionException {
        if(!userDAO.isUserAdmin(user_id)){
            throw new UnauthorizedUserException();
        }
        if(!jwtSessionDAO.hasUserActiveSession(user_id)){
            throw new NoSessionException();
        }
    }

    public BasePackage get(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        GetUserRequestPackage request = GetUserRequestPackage.fromJson(get_request, GetUserRequestPackage.class);
        int user_id = JwtTokenProvider.getUserId(request.getData().getToken());
        this.validateUser(user_id);
        List<User> users = userDAO.getAllUsers();
        GetUserResponseData response_data = new GetUserResponseData(users);
        return new BasePackage(action,response_data,false,"Sucesso");
    }

    public BasePackage destroy(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        DeleteUserRequestPackage request = DeleteUserRequestPackage.fromJson(get_request, DeleteUserRequestPackage.class);
        int adm_id = JwtTokenProvider.getUserId(request.getData().getToken());
        long user_id = request.getData().getUser_id();
        this.validateUser(adm_id);
        userDAO.deleteUser(user_id);
        jwtSessionDAO.deleteAllUserJWTSession(user_id);
        return new BasePackage(action,false,"Usuário removido com sucesso");
    }
}

