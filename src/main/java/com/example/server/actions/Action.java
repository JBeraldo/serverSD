package com.example.server.actions;

public enum Action {
    CREATE_USER("criar-usuario"),
    USER_UPDATE_REQUEST("pedido-edicao-usuario"),
    GET_USERS("listar-usuarios"),
    UPDATE_USER("edicao-usuario"),
    DELETE_USER("excluir-usuario"),
    LOGIN("login"),
    LOGOUT("logout");

    Action(String value) {
        this.value = value;
    }

    public final String value;
    public static Action fromValue(String givenName) {
        for (Action action : values()) {
            if (action.value.equals(givenName)) {
                return action;
            }
        }
        return null;
    }


}
