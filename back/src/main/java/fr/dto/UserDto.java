package fr.dto;

import java.util.List;

import fr.model.Prestation;
import fr.model.Registration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String firstname;
    private List<Registration> registrations;
    private List<Prestation> creations;
}
