package com.example.finalProjectFinal.Token;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface tokenMethods {
    String generateTokenForUser(String username);


    UUID findToken(String username);


    boolean isAdmin(UUID id);

    boolean isTokenValid(UUID id);


}
