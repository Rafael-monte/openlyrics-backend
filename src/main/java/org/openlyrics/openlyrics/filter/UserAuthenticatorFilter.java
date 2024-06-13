package org.openlyrics.openlyrics.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.openlyrics.openlyrics.config.SecurityConfig;
import org.openlyrics.openlyrics.model.User;
import org.openlyrics.openlyrics.model.UserDetailsImpl;
import org.openlyrics.openlyrics.repository.UserRepository;
import org.openlyrics.openlyrics.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticatorFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER="Authorization";

    private static final Function<String, String> removeBearerPrefixFromAuthHeader = (authToken) -> {
        return authToken.replace("Bearer", "");
    };

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (this.isEnpointPublic(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = this.retrieveTokenFromHeader(request);
        
        if (token == null) {
            throw new TokenExpiredException("Invalid token.", null);
        }

        String subject = jwtService.getSubjectFromToken(token);
        User user = userRepository.findByEmail(subject).get();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            userDetails.getEmail(),
         null,
          userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }


    private Boolean isEnpointPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        List<String> publicURLs = Arrays.asList(SecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED);
        return publicURLs.contains(requestURI);
    }

    private String retrieveTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null) return authorizationHeader;
        return removeBearerPrefixFromAuthHeader.apply(authorizationHeader);
    }
    
}
