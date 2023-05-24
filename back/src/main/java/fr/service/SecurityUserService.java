package fr.service;

import java.text.MessageFormat;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.repository.UserRepository;

public class SecurityUserService implements UserDetailsService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public SecurityUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        return userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(MessageFormat.format("L'utilisateur avec l'email {0} n'existe pas.", email)))
                .securityUser();
    }

}