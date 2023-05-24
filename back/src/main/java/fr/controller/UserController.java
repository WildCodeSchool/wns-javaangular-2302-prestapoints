package fr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import fr.dto.UserDto;
import fr.entity.User;
import fr.mapper.UserMapper;
import fr.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public UserDto showTest() {
        User user = new User();
        user.setFirstname("Toto");

        return userMapper.convertToDto(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable("id") Integer id) {
        return userMapper.convertToDto(userService.getUserById(id));
    }
}