package com.sd.server.Packages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sd.server.Base.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonInclude(Include.NON_NULL)
public class BasePackage<T> {
    private static final Logger logger = LogManager.getLogger(BasePackage.class);

    private String action;

    private T data;

    private boolean error;

    private String message;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BasePackage() {
        // Default constructor
    }
    public BasePackage(String action, T data, boolean error, String message) {
        this.action = action;
        this.data = data;
        this.error = error;
        this.message = message;
    }

    public BasePackage(String action,boolean error, String message) {
        this.action = action;
        this.error = error;
        this.message = message;
    }

    ObjectMapper jackson = new ObjectMapper();
    public String toJson() throws JsonProcessingException {
        String json = jackson.writeValueAsString(this);
        logger.info("Enviado: "+json);
        return json;
    }
    public static <T> BasePackage<T> fromJson(String json, Class<T> dataClass) throws JsonProcessingException {
        ObjectMapper jackson = new ObjectMapper();
        JavaType javaType = jackson.getTypeFactory().constructParametricType(BasePackage.class, dataClass);
        return jackson.readValue(json, javaType);
    }
    public static BaseRequest simpleFromJson(String json) throws JsonProcessingException {
        logger.info("Recebido: "+json);
        ObjectMapper jackson = new ObjectMapper();
        jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return jackson.readValue(json, BaseRequest.class);
    }


}
