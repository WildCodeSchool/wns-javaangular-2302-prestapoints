package fr.entity;

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
    private String duration;
    private String addPoint;
    private String dateStart;
    private String dateEnd;
    private String state;
    private String description;
    private Integer maxUser;
    private String image;
    private Integer placeAvailable;

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

    public Prestation(Integer id, String title, String duration, String addPoint, String dateStart, String dateEnd,
            String state, String description, Integer maxUser, String image, List<Registration> registrations) {
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
        this.registrations = registrations;
        this.placeAvailable = this.maxUser;
    }

    public Prestation() {
        this.placeAvailable = 0;
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