package fr.dto;

import fr.model.Registration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {

    private Long id;
    private String title;
    private Integer duration;
    private Integer add_point;
    private Registration registration;
}
