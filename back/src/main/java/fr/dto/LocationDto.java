package fr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {
    private Integer id;
    private Integer latitude;
    private Integer longitude;
    private String city;
    private String postalCode;
    private String address;
    private String addressNumber;
    private String addressInformation;
}
