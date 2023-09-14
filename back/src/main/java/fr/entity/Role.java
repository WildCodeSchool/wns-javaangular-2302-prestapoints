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

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(){}

    public Role(String name) {
        this.name = name;
    }

}
