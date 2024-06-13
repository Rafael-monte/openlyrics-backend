package org.openlyrics.openlyrics.services;

import org.openlyrics.openlyrics.config.TokenTimeConfig;
import org.openlyrics.openlyrics.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JwtService {
    
    @Value("${config.webtoken.secret.key}")
    private String secretKey;

    @Value("${config.webtoken.issuer}")
    private String issuer;

    public String generateToken(UserDetailsImpl userDetails) throws JWTCreationException {
        TokenTimeConfig tokenTimeConfig = new TokenTimeConfig();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            return JWT.create()
                .withIssuer(this.issuer)
                .withIssuedAt(tokenTimeConfig.getStartTime())
                .withExpiresAt(tokenTimeConfig.getEndTime())
                .withSubject(userDetails.getEmail())
                .sign(algorithm);
        } catch (JWTCreationException jce) {
            throw new JWTCreationException("Couldn't generate token", jce);
        }
    }

    public String getSubjectFromToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);        
        try {
            return JWT.require(algorithm)
                .withIssuer(this.issuer)
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException jce) {
            throw new JWTVerificationException("Couldn't verify token.");
        }
    }
}
