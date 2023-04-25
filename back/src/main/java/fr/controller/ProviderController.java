package fr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.dto.ProviderDto;
import fr.mapper.ProviderMapper;
import fr.service.ProviderService;

@RestController
public class ProviderController {
    
    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private ProviderService providerService;

    @CrossOrigin(origins = "*")
    @GetMapping("/providers")
    public List<ProviderDto> showAll() {
 
        return providerMapper.convertAllToDto(providerService.findAll());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/provider/{id}")
    public ProviderDto getProvider(@PathVariable("id") Long id) {
        return providerMapper.convertToDto(providerService.getProviderById(id));
    }
}
