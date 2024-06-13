package org.openlyrics.openlyrics.services;

import org.openlyrics.openlyrics.dto.LoginDto;
import org.openlyrics.openlyrics.dto.LoginResponseDto;
import org.openlyrics.openlyrics.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponseDto authenticateUser(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            loginDto.getEmail(), 
            loginDto.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return LoginResponseDto.successfullyAuthenticated(token);
    }
}
