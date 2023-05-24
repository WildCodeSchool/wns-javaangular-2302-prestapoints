package fr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.dto.UserDto;
import fr.mapper.UserMapper;
import fr.model.User;
import fr.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping("/createUser")
    public UserDto createUsers(@RequestBody UserDto userDto) {
        System.out.println("je suis dans le controller");
        User user = userService.createUser(userDto);
        return userMapper.convertToDto(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userMapper.convertToDto(userService.getUserById(id));
    }
}