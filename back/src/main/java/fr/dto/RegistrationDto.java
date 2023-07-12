package fr.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {

    private Timestamp date;
    private Integer evaluation;
    private String comment;
    
}
