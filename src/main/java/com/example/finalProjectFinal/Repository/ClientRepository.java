package com.example.finalProjectFinal.Repository;

import com.example.finalProjectFinal.Model.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByPassword(String password);
    Boolean existsByEmail(String email);
    Void updateClientPassword(UUID clientId,String password);


}
