package fr.entity;

import java.sql.Timestamp;
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
    private Long duration;
    private Integer addPoint;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private String state;
    private String description;
    private Integer maxUser;

    @OneToMany(mappedBy = "prestation")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "prestation")
    private List<Registration> registrations = new ArrayList<>();
    

    public Prestation() {
    }

    public Prestation(Integer id, String title, Long duration, Integer addPoint, Timestamp dateStart, Timestamp dateEnd, String state, String description, Integer maxUser, List<Image> images, List<Registration> registrations) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.addPoint = addPoint;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.state = state;
        this.description = description;
        this.maxUser = maxUser;
        this.images = images;
        this.registrations = registrations;
    }


}
