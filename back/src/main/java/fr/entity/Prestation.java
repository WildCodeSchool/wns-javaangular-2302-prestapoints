package fr.entity;

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

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    public Prestation() {
    }

    public Prestation(Integer id, String title, String duration, String addPoint, String dateStart, String dateEnd, String state, String description, String maxUser, Integer add_point, String image, Registration registration) {
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
        this.registration = registration;
    }
}
