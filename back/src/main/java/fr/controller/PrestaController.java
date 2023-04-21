package fr.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.model.User;

@RestController
public class PrestaController {

    String value = "Hello la team";

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public User showTest() {
        User user = new User();
        user.setName("Toto");

        return user;
    }

    @GetMapping("/test")
    @CrossOrigin(origins = "*")
    public String getTest() {

        return "test OK";
    }

    // il faut mettre en place la configuration de la sécurité pour utiliser le put
    // avec postman
    @PutMapping(path = "/test", consumes = "text/plain")
    @ResponseBody
    public String setTest(@RequestBody String newVal) {
        this.value = newVal;
        return this.value;
    }

}