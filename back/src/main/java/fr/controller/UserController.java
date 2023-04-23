package fr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import fr.dto.UserDto;
import fr.mapper.UserToDtoMapper;
import fr.model.User;
import fr.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserToDtoMapper userToDtoMapper;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public UserDto showTest() {
        User user = new User();
        user.setFirstname("Toto");

        return userToDtoMapper.convertToDto(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userToDtoMapper.convertToDto(userService.getUserById(id));
    }
}