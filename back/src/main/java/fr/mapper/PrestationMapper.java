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
import fr.repository.UserRepository;

@Component
public class PrestationMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    UserRepository userRepository;

    public PrestationDto convertToDto(Prestation prestation) {
        List<Integer> registrationIds = new ArrayList<>();

        PrestationDto prestationDto = modelMapper.map(prestation, PrestationDto.class);
        List<Registration> registrations = prestation.getRegistration();
        
        for (Registration registration : registrations) {
            registrationIds.add(registration.getId());
        }
        prestationDto.setRegistration(registrationIds);
        
        return prestationDto;
    }

    public Prestation convertToEntity(PrestationDto prestationDto) {
        Prestation prestation = modelMapper.map(prestationDto, Prestation.class);
        
        return prestation;
    }
}
