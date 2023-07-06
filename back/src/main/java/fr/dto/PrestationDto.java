package fr.dto;

import java.sql.Timestamp;
import java.util.List;

import fr.entity.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {

    private Integer id;
    private String title;
    private Long duration;
    private Integer addPoint;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private String state;
    private Integer maxUser;
    private Integer placeAvailable;
    private String description;
    private String littleDescription;
    private String practicalInformation;
    private String language;
    private String personalInfos;
    private TypeDto type;
    private LocationDto location;

    private List<Image> images;
    private List<RegistrationDto> registrations;

}
