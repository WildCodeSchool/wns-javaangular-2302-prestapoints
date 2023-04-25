package fr.dto;

import java.util.List;

import fr.model.Prestation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String firstname;
    private List<Prestation> registrations;
}
