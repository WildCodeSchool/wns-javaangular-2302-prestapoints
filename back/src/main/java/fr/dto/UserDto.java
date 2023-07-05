package fr.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String lastname;
    private String firstname;
    private String password;
    private String email;
    private String phone;
    private String creationDate;
    private List<RoleDto> roles;
}
