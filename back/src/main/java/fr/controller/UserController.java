package fr.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.dto.UserDto;
import fr.entity.User;
import fr.enums.MessageApiEnum;
import fr.enums.RegexEnum;
import fr.mapper.UserMapper;
import fr.model.ResponseApi;
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
    public ResponseApi createUser(@RequestBody UserDto userDto) {

        ResponseApi responseApi = new ResponseApi();
        responseApi.setResponseValid(false);

        if (!userService.findUserByEmail(userDto.getEmail()).isPresent()) {
            if (Pattern.matches(RegexEnum.REGEX_EMAIL.getString(), userDto.getEmail())) {
                userService.createUser(userDto);
                responseApi.setResponseValid(true);
            } else {
                responseApi.setMessage(MessageApiEnum.EMAIL_NOT_VALID.getMessage());
            }
        } else {
            responseApi.setMessage(MessageApiEnum.EMAIL_EXISTING.getMessage());
        }

        return responseApi;
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

    @CrossOrigin(origins = "*")
    @PostMapping("/public/update")
    public ResponseApi updateUser(@RequestBody UserDto userDto) {
        ResponseApi responseApi = new ResponseApi();
        responseApi.setResponseValid(false);
        responseApi.setMessage("coucou");
        System.out.println(">>>>>>>>>>>>>>>>  je suis bien passé la ");
        userService.updateUser(userDto);      
        return responseApi;
    }
}