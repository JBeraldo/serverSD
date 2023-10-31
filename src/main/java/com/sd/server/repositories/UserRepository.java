package com.sd.server.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.Exceptions.EmailAlreadyUsedException;
import com.sd.server.Exceptions.NoSessionException;
import com.sd.server.Exceptions.UnauthorizedUserException;
import com.sd.server.Models.User;
import com.sd.server.Packages.data.request.user.*;
import com.sd.server.Packages.data.response.user.FindUserResponseData;
import com.sd.server.Packages.data.response.user.GetUserResponseData;
import com.sd.server.Packages.BasePackage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;


public class UserRepository {

    UserDAO userDAO = new UserDAO();
    JWTSessionDAO jwtSessionDAO = new JWTSessionDAO();

    public BasePackage create(String action, String create_request) throws JsonProcessingException, UnauthorizedUserException, NoSessionException, EmailAlreadyUsedException {
        BasePackage<CreateUserRequestData> request = BasePackage.fromJson(create_request, CreateUserRequestData.class);
        int user_id = AuthRepository.getUserId(request.getData().getToken());
        this.validateAdminUser(user_id);
        User user = new User(request.getData());
        user.setPassword(hashUserPassw(user.getPassword()));
        userDAO.addUserIfNotExistByEmail(user);
        return new BasePackage(action,false,"Usuário cadastrado com sucesso");
    }

    public String hashUserPassw(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private void validateAdminUser(int user_id) throws UnauthorizedUserException, NoSessionException {
        if(!userDAO.isUserAdmin(user_id)){
            throw new UnauthorizedUserException();
        }
        if(!jwtSessionDAO.hasUserActiveSession(user_id)){
            throw new NoSessionException();
        }
    }
    private void validateLoggedUser(int user_id) throws UnauthorizedUserException, NoSessionException {
        if(!jwtSessionDAO.hasUserActiveSession(user_id)){
            throw new NoSessionException();
        }
    }

    public BasePackage<FindUserResponseData> find(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<FindUserRequestData> request = BasePackage.fromJson(get_request, FindUserRequestData.class);
        int logged_user_id = AuthRepository.getUserId(request.getData().getToken());
        Long user_id = request.getData().getUser_id();
        this.validateLoggedUser(logged_user_id);
        User user = userDAO.getUserById(user_id);
        FindUserResponseData response_data = new FindUserResponseData(user);
        return new BasePackage<FindUserResponseData>(action,response_data,false,"Sucesso");
    }

    public BasePackage<FindUserResponseData> findSelf(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<FindSelfUserRequestData> request = BasePackage.fromJson(get_request, FindSelfUserRequestData.class);
        int logged_user_id = AuthRepository.getUserId(request.getData().getToken());
        this.validateLoggedUser(logged_user_id);
        User user = userDAO.getUserById(logged_user_id);
        FindUserResponseData response_data = new FindUserResponseData(user);
        return new BasePackage<FindUserResponseData>(action,response_data,false,"Sucesso");
    }

    public BasePackage destroy(String action, String destroy_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<DeleteUserRequestData> request = BasePackage.fromJson(destroy_request, DeleteUserRequestData.class);
        int adm_id = AuthRepository.getUserId(request.getData().getToken());
        long user_id = request.getData().getUser_id();
        this.validateAdminUser(adm_id);
        userDAO.deleteUser(user_id);
        jwtSessionDAO.deleteAllUserJWTSession(user_id);
        return new BasePackage(action,false,"Usuário removido com sucesso");
    }

    public BasePackage<GetUserResponseData> get(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<GetUserRequestData> request = BasePackage.fromJson(get_request, GetUserRequestData.class);
        int user_id = AuthRepository.getUserId(request.getData().getToken());
        this.validateAdminUser(user_id);
        List<User> users = userDAO.getAllUsers();
        GetUserResponseData response_data = new GetUserResponseData(users);
        return new BasePackage(action,response_data,false,"Sucesso");
    }
    public BasePackage update(String action, String update_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<EditUserRequestData> request = BasePackage.fromJson(update_request, EditUserRequestData.class);
        int user_id = AuthRepository.getUserId(request.getData().getToken());
        this.validateLoggedUser(user_id);
        User user = userDAO.getUserById(request.getData().getId());
        if(AuthRepository.isAdmin(request.getData().getToken())){
            user.setType(request.getData().getType());
        }
        if(request.getData().getPassword() != null){
            user.setPassword(hashUserPassw(request.getData().getPassword()));
        }
        user.setName(request.getData().getName());
        user.setEmail(request.getData().getEmail());
        userDAO.updateUser(user);
        return new BasePackage(action,null,false,"Sucesso");
    }
}

