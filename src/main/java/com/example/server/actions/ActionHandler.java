package com.example.server.actions;

import com.example.server.data.login.LoginData;
import com.example.server.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ActionHandler {
    public BaseResponse dispatch(String response_action) throws JsonProcessingException {
    Action action = Action.fromValue(response_action);
        switch (action){
            case CREATE_USER:
                LoginData login_data = new LoginData("Abacate");
                return new BaseResponse(action.value,login_data,false,null);
            case USER_UPDATE_REQUEST:
                break;
            case GET_USERS:
                break;
            case UPDATE_USER:
                break;
            case DELETE_USER:
                break;
            case LOGIN:
                break;
            case LOGOUT:
                break;
        }
        return new  BaseResponse(action.value,null,true,"Rota não encontrada");
    }
}
