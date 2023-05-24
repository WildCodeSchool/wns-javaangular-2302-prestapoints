package fr.dto;

import fr.entity.Provider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {

    private int id;
    private String title;
    private String duration;
    private String addPoint;
    private String dateStart;
    private String dateEnd;
    private String state;
    private String description;
    private String maxUser;
    private Provider provider;
}
