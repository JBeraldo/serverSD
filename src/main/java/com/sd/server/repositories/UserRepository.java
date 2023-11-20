package com.sd.server.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.DAO.JWTSessionDAO;
import com.sd.server.DAO.UserDAO;
import com.sd.server.Exceptions.EmailAlreadyUsedException;
import com.sd.server.Exceptions.NoSessionException;
import com.sd.server.Exceptions.UnauthorizedUserException;
import com.sd.server.Exceptions.WrongCredentialsException;
import com.sd.server.Models.User;
import com.sd.server.Packages.data.request.user.*;
import com.sd.server.Packages.data.response.user.FindUserPackageData;
import com.sd.server.Packages.data.response.user.GetUserPackageData;
import com.sd.server.Packages.BasePackage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;


public class UserRepository {

    UserDAO userDAO = new UserDAO();

    JWTSessionDAO jwtSessionDAO = new JWTSessionDAO();

    public BasePackage create(String action, String create_request) throws JsonProcessingException, UnauthorizedUserException, NoSessionException, EmailAlreadyUsedException {
        BasePackage<CreateUserRequestData> request = BasePackage.fromJson(create_request, CreateUserRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateAdminUser(user_id);
        User user = new User(request.getData());
        user.setPassword(hashUserPassw(user.getPassword()));
        userDAO.addUserIfNotExistByEmail(user);
        return new BasePackage(action,false,"Usuário cadastrado com sucesso");
    }

    public String hashUserPassw(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public BasePackage<FindUserPackageData> find(String action, String find_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<FindUserRequestData> request = BasePackage.fromJson(find_request, FindUserRequestData.class);
        Long logged_user_id = AuthRepository.getUserId(request.getData().getToken());
        Long user_id = request.getData().getUser_id();
        AuthRepository.validateCommonUser(logged_user_id);
        User user = userDAO.getUserById(user_id);
        FindUserPackageData response_data = new FindUserPackageData(user);
        return new BasePackage<FindUserPackageData>(action,response_data,false,"Sucesso");
    }

    public BasePackage<FindUserPackageData> findSelf(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<FindSelfUserRequestData> request = BasePackage.fromJson(get_request, FindSelfUserRequestData.class);
        Long logged_user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateCommonUser(logged_user_id);
        User user = userDAO.getUserById(logged_user_id);
        FindUserPackageData response_data = new FindUserPackageData(user);
        return new BasePackage<FindUserPackageData>(action,response_data,false,"Sucesso");
    }

    public BasePackage destroy(String action, String destroy_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<DeleteUserRequestData> request = BasePackage.fromJson(destroy_request, DeleteUserRequestData.class);
        Long adm_id = AuthRepository.getUserId(request.getData().getToken());
        long user_id = request.getData().getUser_id();
        AuthRepository.validateAdminUser(adm_id);
        userDAO.deleteUser(user_id);
        jwtSessionDAO.deleteAllUserJWTSession(user_id);
        return new BasePackage(action,false,"Usuário removido com sucesso");
    }

    public BasePackage<GetUserPackageData> get(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<GetUserRequestData> request = BasePackage.fromJson(get_request, GetUserRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateAdminUser(user_id);
        List<User> users = userDAO.getAllUsers();
        GetUserPackageData response_data = new GetUserPackageData(users);
        return new BasePackage(action,response_data,false,"Sucesso");
    }
    public BasePackage update(String action, String update_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<EditUserRequestData> request = BasePackage.fromJson(update_request, EditUserRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateCommonUser(user_id);
        User user = userDAO.getUserById(request.getData().getUser_id());
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

    public BasePackage updateSelf(String action, String update_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<EditSelfUserRequestData> request = BasePackage.fromJson(update_request, EditSelfUserRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateCommonUser(user_id);
        User user = userDAO.getUserById(request.getData().getId());
        if(request.getData().getPassword() != null){
            user.setPassword(hashUserPassw(request.getData().getPassword()));
        }
        user.setName(request.getData().getName());
        user.setEmail(request.getData().getEmail());
        userDAO.updateUser(user);
        return new BasePackage(action,null,false,"Sucesso");
    }

    public BasePackage destroySelf(String action, String destroy_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException, WrongCredentialsException {
        BasePackage<DeleteSelfUserRequestData> request = BasePackage.fromJson(destroy_request, DeleteSelfUserRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateCommonUser(user_id);
        User user = userDAO.getUserById(user_id);
        authUser(request.getData().getPassword(),user);
        userDAO.deleteUser(user_id);
        jwtSessionDAO.deleteAllUserJWTSession(user_id);
        return new BasePackage(action,false,"Usuário removido com sucesso");
    }

    public void authUser(String password,User user) throws WrongCredentialsException {
        if(!BCrypt.checkpw(password,user.getPassword())){
            throw new WrongCredentialsException();
        }
    }
}

