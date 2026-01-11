package com.kaua.reservation.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kaua.reservation.entity.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenConfig {

    @Value("{jwt.secret}")
    private String secret;


    public String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try{
         String token = JWT.create()
                 .withClaim("UserEmail", user.getEmail())
                 .withSubject(user.getCpf())
                 .withExpiresAt(genExpiration())
                 .withIssuedAt(Instant.now())
                 .sign(algorithm);
                return token;

        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generate Token", exception);
        }
    }

    public Instant genExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


    public String token(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
                return "";
        }
    }
}
