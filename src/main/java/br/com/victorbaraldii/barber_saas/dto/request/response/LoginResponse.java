package br.com.victorbaraldii.barber_saas.dto.request.response;

public class LoginResponse {

    private String token;

    public LoginResponse() {

    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
