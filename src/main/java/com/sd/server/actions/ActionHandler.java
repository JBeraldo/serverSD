package com.sd.server.actions;

import com.sd.server.exceptions.*;
import com.sd.server.packages.BasePackage;
import com.sd.server.packages.BaseRequest;
import com.sd.server.repositories.LoginRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.repositories.UserRepository;

public class ActionHandler {

    private static final LoginRepository login_repository = new LoginRepository();
    private static final UserRepository user_repository = new UserRepository();

    public static BasePackage dispatch(String response_action) throws JsonProcessingException {
        BaseRequest request = BasePackage.simpleFromJson(response_action);
        String action = request.getAction();
        try {
            return switch (action) {
                case "login" -> login_repository.login(action, response_action);
                case "logout" -> login_repository.logout(action, response_action);
                case "cadastro-usuario" -> user_repository.create(action, response_action);
                case "listar-usuarios" -> user_repository.get(action, response_action);
                case "excluir-usuario" -> user_repository.destroy(action, response_action);
                default -> new BasePackage(response_action, null, true, "Rota não encontrada");
            };
        }catch(Exception e){
            return new BasePackage(action, null, true, e.getMessage());
        }
    }
}
