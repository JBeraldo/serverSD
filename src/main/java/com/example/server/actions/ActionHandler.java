package com.example.server.actions;

import com.example.server.exceptions.NotFoundException;
import com.example.server.exceptions.UnauthorizedUserException;
import com.example.server.packages.BasePackage;
import com.example.server.packages.BaseRequest;
import com.example.server.repositories.LoginRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ActionHandler {

    private LoginRepository login_repository;

    public ActionHandler() {
        this.login_repository = new LoginRepository();
    }

    public BasePackage dispatch(String response_action) throws JsonProcessingException {
        BaseRequest request = BasePackage.simpleFromJson(response_action);
        String action = request.getAction();
        return switch (action) {
            case "login" -> {
                try {
                    yield this.login_repository.login(action,response_action);
                } catch (NotFoundException | UnauthorizedUserException e) {
                    yield new BasePackage(action, null, true, e.getMessage());
                }
            }
            default -> new BasePackage(response_action, null, true, "Rota não encontrada");
        };
    }
}
