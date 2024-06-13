package org.openlyrics.openlyrics.dto;

import org.openlyrics.openlyrics.interfaces.SimplexDtoWrapper;
import org.openlyrics.openlyrics.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginDto implements SimplexDtoWrapper<User> {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @Override
    public User toModel() {
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setUsername(this.getUsername());
        return user;
    }
    
}
