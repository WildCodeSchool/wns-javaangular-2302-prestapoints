package fr.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


import fr.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private String phone;
    private String token;
    private Timestamp creationDate;

    @OneToOne(mappedBy = "user")
    private Avatar avatar;

    @OneToOne(mappedBy = "prestation")
    private Prestation prestation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // TODO :
    // voir à mettre une checkbox sur le formulaire de connexion afin de savoir
    // comment le user souhaite se co
    // ou de mettre cette checkbox dans la navbar.
    // bref, trouver une astuce pour gérer les rôles USER et PRESTATAIRE

    // User (méthode secuityUser)
    // -attribut les rôles du User
    // -retourne un userdetails.user (nécessaires pour l'authentification)
    public org.springframework.security.core.userdetails.User securityUser() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(id.toString()));

        try {
            for (Role role : this.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new org.springframework.security.core.userdetails.User(this.getEmail(), this.getPassword(),
                grantedAuthorities);
    }
}
