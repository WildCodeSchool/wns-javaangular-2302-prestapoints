package fr.dto;


import java.util.List;

import fr.entity.Registration;
import fr.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {

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

    private List<Integer> registration;

}
