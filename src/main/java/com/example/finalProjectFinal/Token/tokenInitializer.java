package com.example.finalProjectFinal.Token;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;
@Repository
public class tokenInitializer implements tokenMethods{
    HashMap<UUID, String> tokenData = new HashMap<>();

    @Override
    public String generateTokenForUser(String username) {
        tokenData.put(UUID.randomUUID(),username);
        return "Token generated";
    }



    @Override
    public UUID findToken(String username) {
        UUID token = null;

        for (HashMap.Entry<UUID, String> pair : tokenData.entrySet()) {
            if (pair.getValue().equals(username)) {
                token = pair.getKey();
            }
        }

        return token;
    }



    @Override
    public boolean isAdmin(UUID id) {
        for (HashMap.Entry<UUID, String> pair : tokenData.entrySet()){
            if(pair.getValue().equals("admin")){
                if(pair.getKey().equals(id)){
                    return true;
                }
            }
        }
        return false;
}


    @Override
    public boolean isTokenValid(UUID id) {
        for (HashMap.Entry<UUID, String> pair : tokenData.entrySet()){
         if(pair.getKey().equals(id)){
             return true;
         }
        }
        return false;
    }

}
