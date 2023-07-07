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
    private String maxUser;
    private String image;
    private String littleDescription;
    private String practicalInformation;
    private String language;
    private String personalInfos;
    private String locationInfos;
    private String dateComment;
    private String authorComment;
    private String comment;
    
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
            String state, String description, String maxUser, String image, String littleDescription,
            String practicalInformation, String language, String personalInfos, String locationInfos, String dateComment,
            String authorComment, String comment, List<Registration> registrations) {
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
        this.littleDescription = littleDescription;
        this.practicalInformation = practicalInformation;
        this.language = language;
        this.personalInfos = personalInfos;
        this.locationInfos = locationInfos;
        this.dateComment = dateComment;
        this.authorComment = authorComment;
        this.comment = comment;
        this.registrations = registrations;
    }

    public Prestation() {
    }
}
