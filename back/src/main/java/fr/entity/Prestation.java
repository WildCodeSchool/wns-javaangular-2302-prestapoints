package fr.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Prestation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    private String duration;
    private String addPoint;
    private String dateStart;
    private String dateEnd;
    private String state;
    private String description;
    private String maxUser;
    private String image;

    @OneToMany(mappedBy = "prestation")
    private List<UserRegister> userRegisters = new ArrayList<>();

    public Prestation(Integer id, String title, String duration, String addPoint, String dateStart, String dateEnd, String state, String description, String maxUser, String image, List<UserRegister> userRegisters) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.addPoint = addPoint;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.state = state;
        this.description = description;
        this.maxUser = maxUser;
        this.image = image;
        this.userRegisters = userRegisters;
    }

    public Prestation() {
    }


}
