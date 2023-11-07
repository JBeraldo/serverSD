package com.sd.server.Actions;

import com.sd.server.Packages.BasePackage;
import com.sd.server.Packages.BaseRequest;
import com.sd.server.repositories.LoginRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.repositories.UserRepository;

public class ActionHandler {

    private static final LoginRepository login_repository = new LoginRepository();
    private static final UserRepository user_repository = new UserRepository();

    public static BasePackage dispatch(String response_action,String ip) throws JsonProcessingException {
        BaseRequest request = BasePackage.simpleFromJson(response_action);
        String action = request.getAction();
        try {
            return switch (action) {
                case "login" -> login_repository.login(action, response_action,ip);
                case "logout" -> login_repository.logout(action, response_action);
                case "cadastro-usuario" -> user_repository.create(action, response_action);
                case "listar-usuarios" -> user_repository.get(action, response_action);
                case "excluir-usuario" -> user_repository.destroy(action, response_action);
                case "pedido-edicao-usuario" -> user_repository.find(action, response_action);
                case "pedido-proprio-usuario" -> user_repository.findSelf(action, response_action);
                case "autoedicao-usuario" -> user_repository.updateSelf(action, response_action);
                case "edicao-usuario" -> user_repository.update(action, response_action);
                case "excluir-proprio-usuario" -> user_repository.destroySelf(action, response_action);
                default -> new BasePackage(response_action, null, true, "Rota não encontrada");
            };
        }catch(Exception e){
            return new BasePackage(action, null, true, e.getMessage());
        }
    }
}
