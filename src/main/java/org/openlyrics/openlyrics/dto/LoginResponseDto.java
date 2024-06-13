package org.openlyrics.openlyrics.dto;

import org.openlyrics.openlyrics.interfaces.DtoResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginResponseDto implements DtoResponse {
    @JsonProperty("reponse")
    private String response;

    @JsonProperty("token")
    private String token;

    public static LoginResponseDto successfullyAuthenticated(String token) {
        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setResponse("Authentication Successful");
        loginResponse.setToken(token);
        return loginResponse;
    }
}
