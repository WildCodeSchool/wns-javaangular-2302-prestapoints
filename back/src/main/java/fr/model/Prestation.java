package fr.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor //lombok
public class Prestation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String title;
  private String duration;
  private String addPoint;
  private String dateStart;
  private String dateEnd;
  private String state;
  @Column(columnDefinition="TEXT")
  private String description;
  private String maxUser;

    public Prestation() {
    }

    public Prestation(int id, String title, String duration, String addPoint, String dateStart, String dateEnd, String state, String description, String maxUser) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.addPoint = addPoint;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.state = state;
        this.description = description;
        this.maxUser = maxUser;
    }

}
