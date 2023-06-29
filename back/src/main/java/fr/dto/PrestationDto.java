package fr.dto;

import java.util.Date;
import java.util.List;

import fr.entity.Image;
import fr.entity.Registration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {

    private Integer id;
    private String title;
    private String duration;
    private Date addPoint;
    private Date dateStart;
    private String dateEnd;
    private String state;
    private String description;
    private Integer maxUser;

    private List<Image> images;

    private List<Registration> registrations;

}
