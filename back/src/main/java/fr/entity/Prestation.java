package fr.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter//lombok
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
    
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Prestation() {
    }

    public Prestation(Integer id, String title, String duration, String addPoint, String dateStart, String dateEnd, String state, String description, String maxUser, Integer add_point, Provider provider) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.addPoint = addPoint;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.state = state;
        this.description = description;
        this.maxUser = maxUser;
        this.provider = provider;
    }
}