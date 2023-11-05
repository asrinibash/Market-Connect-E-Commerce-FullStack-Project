package com.cutm.marketconnect.Response;

public class AuthResponse {
    private String jwt;
    private String message;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthResponse(String jwt, String message) {
        super();
        this.jwt = jwt;
        this.message = message;
    }
    public AuthResponse(){}
}
