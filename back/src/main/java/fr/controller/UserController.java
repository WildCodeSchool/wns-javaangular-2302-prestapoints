package fr.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // Inscription du USER :
    @CrossOrigin(origins = "*")
    @PostMapping("/public/sign-in")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    // vérification de l'email d'inscription :
    @CrossOrigin(origins = "*")
    @PostMapping("/public/email/verification")
    public boolean emailVerification(@RequestBody String email) {
        System.out.println(email);
        Optional<User> user = userService.findUserByEmail(email);

        return user.isPresent();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/users/{id}")
    public UserDto getUser(@PathVariable("id") Integer id) {

        return userMapper.convertToDto(userService.getUserById(id));
    }
}