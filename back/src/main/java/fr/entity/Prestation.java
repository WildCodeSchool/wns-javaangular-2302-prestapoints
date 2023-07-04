package fr.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="`prestation`")
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
    private Integer placeAvailable;

    @OneToMany(mappedBy = "prestation", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    @JsonIgnoreProperties
    private Type type;

    @OneToMany(mappedBy = "prestation")
    @JsonIgnore
    private List<Registration> registrations = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;


    public Prestation() {
        this.placeAvailable = 0;
    }

    public Prestation(Integer id, String title, Long duration, Integer addPoint, Timestamp dateStart, Timestamp dateEnd, String state, String description, Integer maxUser, Integer placeAvailable, List<Image> images, Type type, List<Registration> registrations, Location location) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.addPoint = addPoint;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.state = state;
        this.description = description;
        this.maxUser = maxUser;
        this.placeAvailable = placeAvailable;
        this.images = images;
        this.type = type;
        this.registrations = registrations;
        this.location = location;
    }



    public void bookedPlace() {
        this.setPlaceAvailable(this.getPlaceAvailable()-1); 
    }

    public void setMaxUser(Integer maxUser) {
        this.maxUser = maxUser;
        calculatePlaceavailable();
    }

    private void calculatePlaceavailable() {
        if (this.getRegistrations() != null && this.getMaxUser() > 0) {
            this.setPlaceAvailable(this.getMaxUser() - this.getRegistrations().size());
        }
    }
    
     
}