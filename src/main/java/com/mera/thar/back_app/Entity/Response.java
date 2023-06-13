package com.mera.thar.back_app.Entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chander Parkash
 *
 */
public class Response implements Serializable{

    private String responseCode;
    private String responseMessage;
    private Map<String, Object> responseData;

    public Response() {
        //Do Nothing...
    }

    public Response(String responseCode, String responseMessage, Map<String, Object> responseData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseMessage = responseMessage;
    }

    /**
     * @return the code
     */
    public String getResponseCode() {
        return responseCode;
    }
    /**
     * @param code the code to set
     */
    public void setResponseCode(String code) {
        this.responseCode = code;
    }
    /**
     * @return the message
     */
    public String getResponseMessage() {
        return responseMessage;
    }
    /**
     * @param message the message to set
     */
    public void setResponseMessage(String message) {
        this.responseMessage = message;
    }
    /**
     * @return the data
     */
    public Map<String, Object> getResponseData() {
        return responseData;
    }
    /**
     * @param data the data to set
     */
    public void setResponseData(Map<String, Object> data) {
        this.responseData = data;
    }

    public void addData(String key, Object object){
        if(responseData == null){
            responseData = new HashMap<String, Object>();
            responseData.put(key, object);
        } else {
            responseData.put(key, object);
        }
    }
}

