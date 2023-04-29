package fr.dto;

import java.util.List;
import fr.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {

    private Long id;
    private String title;
    private Integer duration;
    private Integer add_point;
    private List<User> registeredUsers;
    private User creator;
}
