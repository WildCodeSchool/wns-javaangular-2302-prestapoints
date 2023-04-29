package fr.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import fr.dto.PrestationDto;
import fr.mapper.PrestationMapper;
import fr.model.Prestation;
import fr.service.PrestationService;

@RestController
public class PrestationController {

    @Autowired
    private PrestationMapper prestationMapper;

    @Autowired
    private PrestationService prestationService;

    @CrossOrigin(origins = "*")
    @GetMapping("/home/prestations")
    public List<PrestationDto> showPrestations() {
        List<PrestationDto> prestationDtos = new ArrayList<>();
        for (Prestation prestation : prestationService.findAllPrestations()) {
            prestationDtos.add(prestationMapper.convertToDto(prestation));
        }
        return prestationDtos;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/home/prestation/{id}")
    public PrestationDto getPrestation(@PathVariable("id") Long id) {
        return prestationMapper.convertToDto(prestationService.getPrestationById(id));
    }

}
