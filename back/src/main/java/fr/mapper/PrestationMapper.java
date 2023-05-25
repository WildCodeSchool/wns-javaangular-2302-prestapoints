package fr.mapper;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dto.PrestationDto;
import fr.entity.Prestation;
import fr.entity.Registration;
import fr.entity.User;
import fr.repository.RegistrationRepository;

@Component
public class PrestationMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RegistrationRepository registrationRepository;

    public PrestationDto convertToDto(Prestation prestation) {
        PrestationDto prestationDto = modelMapper.map(prestation, PrestationDto.class);
        List<Registration> registrations = registrationRepository.findAllByPrestationId(prestation.getId());
        List<User> users = new ArrayList<>();
        prestationDto.setRegistration(users);
        return prestationDto;
    }

    public Prestation convertToEntity(PrestationDto prestationDto) {
        Prestation prestation = modelMapper.map(prestationDto, Prestation.class);

        return prestation;
    }
}
