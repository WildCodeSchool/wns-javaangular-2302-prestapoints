package fr.entity;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private Timestamp tokenValidation;
    private Timestamp creation;
    
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // voir à mettre une checkbox sur le formulaire de connexion afin de savoir comment le user souhaite se co
    // ou de mettre cette checkbox dans la navbar.
    // bref, trouver une astuce pour gérer les rôles USER et PRESTATAIRE
    public org.springframework.security.core.userdetails.User securityUser() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(id.toString()));
        if (this.getEmail().contains("admin@")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new org.springframework.security.core.userdetails.User(this.getEmail(), this.getPassword(), grantedAuthorities);
    }
}
