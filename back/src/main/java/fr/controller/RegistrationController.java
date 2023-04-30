package fr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.dto.RegistrationDto;
import fr.mapper.RegistrationMapper;
import fr.model.Prestation;
import fr.model.Registration;
import fr.service.PrestationService;
import fr.service.RegistrationService;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PrestationService prestationService;

    @Autowired
    private RegistrationMapper registrationMapper;

    @CrossOrigin(origins = "*")
    @GetMapping("/registration/{id}")
    public Iterable<RegistrationDto> getCommentAndEvaluationById(@PathVariable("id") Long id) {
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        for (Registration registration : registrationService.findAllByPrestationId(id)) {
            registrationDtos.add(registrationMapper.convertToDto(registration));
        }
        return registrationDtos;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/registrations")
    public List<RegistrationDto> getAllCommentAndEvaluation() {
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        for (Registration registration : registrationService.findAllWithComment()) {
        registrationDtos.add(registrationMapper.convertToDto(registration));
        }
        return registrationDtos;
   }
}
