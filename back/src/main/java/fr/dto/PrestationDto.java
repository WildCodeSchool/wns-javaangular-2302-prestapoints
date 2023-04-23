package fr.dto;

import fr.model.Provider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {

    private Long id;
    private String title;
    private Integer duration;
    private Integer add_point;
    private Provider provider;
}
