package org.openlyrics.openlyrics.dto;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openlyrics.openlyrics.interfaces.SimplexDtoWrapper;
import org.openlyrics.openlyrics.model.User;
import org.openlyrics.openlyrics.model.UserRole;
import org.openlyrics.openlyrics.model.enumeration.RoleName;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NewUserDto implements SimplexDtoWrapper<User> {
    
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("document")
    private String document;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("description")
    private String description;

    @JsonProperty("email")
    private String email;

    @Override
    public User toModel() {
        UserRole defaultRole = new UserRole();
        defaultRole.setRole(RoleName.USER);
        User user = new User();
        user.setUsername(username);
        user.setDescription(description);
        user.setPassword(password);
        user.setDocument(document);
        user.setPhoto(photo);
        user.setDescription(description);
        user.setEmail(email);
        user.setRoles(Stream.of(defaultRole).collect(Collectors.toSet()));
        user.setIsAccountDeleted(Boolean.FALSE);
        user.setIsAccountSuspended(Boolean.FALSE);
        user.setSuspendedUntil(null);
        return user;
    }
}
