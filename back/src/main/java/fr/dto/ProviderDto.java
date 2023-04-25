package fr.dto;

import fr.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDto {

    private Long id;
    private User user;
}
