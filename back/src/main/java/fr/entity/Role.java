package fr.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.ManyToMany;

@Entity
@Getter
@Setter
public class Role {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
