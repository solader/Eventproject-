package com.projetFe.Event.service;

import com.projetFe.Event.Appuser;
import com.projetFe.Event.exception.ResourceNotFoundException;
import com.projetFe.Event.repository.Appuser_repository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUseerdetailService implements UserDetailsService {
    @Autowired
    private Appuser_repository appuserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Appuser user = appuserRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));

        System.out.println("Email trouvé : " + user.getEmail());
        System.out.println("Mot de passe hashé : " + user.getPassword());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
