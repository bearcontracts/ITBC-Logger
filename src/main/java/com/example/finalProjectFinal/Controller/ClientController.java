package com.example.finalProjectFinal.Controller;

import com.example.finalProject.Model.Registration.RegistrationRequest;
import com.example.finalProjectFinal.Model.Client.Client;
import com.example.finalProjectFinal.Model.Client.ClientRole;
import com.example.finalProjectFinal.Model.Requests.LoginRequest;
import com.example.finalProjectFinal.Repository.ClientRepository;
import com.example.finalProjectFinal.Services.ClientDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ClientController {
    private ClientDetailsService clientDetailsService;
    private BCryptPasswordEncoder encoder;
    private ClientRepository clientRepository;
    private com.example.finalProjectFinal.Token.tokenInitializer tokenInitializer;

    @PostMapping(path = "/api/clients/register")
    public ResponseEntity<String> registerClient(@RequestBody RegistrationRequest registrationRequest){
        if(clientRepository.existsByEmail(registrationRequest.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (registrationRequest.getUsername().length() < 3 || registrationRequest.getPassword().length() < 8
                || (registrationRequest.getEmail().contains("@") && registrationRequest.getEmail().contains("."))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input");
        }

        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setUsername(registrationRequest.getUsername());
        client.setEmail(registrationRequest.getEmail());
        client.setPassword(encoder.encode(registrationRequest.getPassword()));
        client.setClientRole(ClientRole.CLIENT);




        clientRepository.save(client);

        return new ResponseEntity<String>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping(path = "/api/clients/login")
    public ResponseEntity<String> signInUser(@RequestBody LoginRequest loginRequest){
        if(clientRepository.existsByUsername(loginRequest.getUsername()) && clientRepository.existsByPassword(loginRequest.getPassword())){
            tokenInitializer.generateTokenForUser(loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body("Your token is "+tokenInitializer.findToken(loginRequest.getUsername()));
        }
        else return new ResponseEntity<String>(HttpStatus.CONFLICT);
    }

    @GetMapping(path = "/api/clients")
    public ResponseEntity<List> getAllClients(@RequestHeader UUID  token) {

        if (tokenInitializer.isTokenValid(token)) {
            if (!(tokenInitializer.isAdmin(token))) {


                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

        }


        return ResponseEntity.status(HttpStatus.CONTINUE).body(clientDetailsService.getClients());


    }
    @PatchMapping("/api/clients/{clientId}/reset-password")
    public ResponseEntity<String> changeClientPassword(@RequestBody Client client, @RequestParam(name = "clientId") UUID id, @RequestHeader UUID token, @PathVariable String clientId){
        if(!(tokenInitializer.isTokenValid(token))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not admin! You cannot change password");

        }
        if(clientRepository.findById(client.getId()).isPresent()){
            clientDetailsService.changePassword(id,encoder.encode(client.getPassword()));

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("You have successfully changed your password");
    }

    }






