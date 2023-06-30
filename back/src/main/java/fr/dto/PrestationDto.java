package fr.dto;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Data;
import org.springframework.web.multipart.MultipartFile;

import fr.entity.Image;
import fr.entity.Registration;
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
    private Integer maxUser;

    private List<Image> images;

    private List<Registration> registrations;

}
