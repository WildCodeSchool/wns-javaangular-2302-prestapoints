package fr.dto;

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
    private Long dateStart;
    private Long dateEnd;
    private String state;
    private String description;
    private String maxUser;
    private TypeDto type;
    private LocationDto location;
    private Integer placeAvailable;
    private List<Image> images;
    private List<RegistrationDto> registrations;

}
