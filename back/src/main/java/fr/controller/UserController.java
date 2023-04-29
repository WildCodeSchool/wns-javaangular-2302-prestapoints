package fr.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.dto.PrestationDto;
import fr.dto.UserDto;
import fr.mapper.PrestationMapper;
import fr.mapper.UserMapper;
import fr.model.Prestation;
import fr.service.PrestationService;
import fr.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PrestationMapper prestationMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PrestationService prestationService;

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<UserDto> showAll() {
        return userMapper.convertAllToDto(userService.findAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "user/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userMapper.convertToDto(userService.getUserById(id));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "user/{id}/prestations")
    public List<PrestationDto> getPrestationsUser(@PathVariable("id") Long id) {
        List<PrestationDto> prestationDtos = new ArrayList<>();
        for (Prestation prestation : prestationService.findPrestationsByUserId(id)) {
            prestationDtos.add(prestationMapper.convertToDto(prestation));
        }
        return prestationDtos;
    }

}