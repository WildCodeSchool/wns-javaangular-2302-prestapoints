package fr.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import fr.enums.RoleEnum;
import fr.repository.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="`user`")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private String phone;
    private Timestamp tokenValidation;
    private Timestamp creation;

    @ManyToMany
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // TODO :
    // voir à mettre une checkbox sur le formulaire de connexion afin de savoir comment le user souhaite se co
    // ou de mettre cette checkbox dans la navbar.
    // bref, trouver une astuce pour gérer les rôles USER et PRESTATAIRE



    // User (méthode secuityUser)
    // -créé une liste pour les rôles de l'utilisateur (on stocke son ID en premier)
    // -attribut le rôle User
    // -retourne un userdetails.user (nécessaires pour l'authentification)
    public org.springframework.security.core.userdetails.User securityUser() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(id.toString()));
        grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.ROLE_USER.getRole()));

        return new org.springframework.security.core.userdetails.User(this.getEmail(), this.getPassword(),
                grantedAuthorities);
    }
}
