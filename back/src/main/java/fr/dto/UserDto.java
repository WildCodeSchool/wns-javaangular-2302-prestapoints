package fr.dto;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer id;
    private String lastname;
    private String firstname;
    private String password;
    private String email;
    private String phone;
    private String creationDate;
    private List<RoleDto> roles;
}
