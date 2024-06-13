package org.openlyrics.openlyrics.controller;

import org.springframework.web.bind.annotation.RestController;
import org.openlyrics.openlyrics.dto.LoginDto;
import org.openlyrics.openlyrics.dto.LoginResponseDto;
import org.openlyrics.openlyrics.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(service.authenticateUser(loginDto), HttpStatus.OK);
    }
    
}
