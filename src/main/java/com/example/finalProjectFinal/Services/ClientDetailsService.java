package com.example.finalProjectFinal.Services;

import com.example.finalProjectFinal.Model.Client.Client;
import com.example.finalProjectFinal.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientDetailsService implements UserDetailsService {
    private ClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("Email not valid", email)));
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public String changePassword(UUID id, String password) {
        return bCryptPasswordEncoder.encode(password);

    }
}









