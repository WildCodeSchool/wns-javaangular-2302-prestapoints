package fr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dto.RegistrationDto;
import fr.mapper.RegistrationMapper;
import fr.model.Registration;
import fr.repository.RegistrationRepository;

@Service
public class RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationMapper registrationMapper;

    public void createUser(RegistrationDto registrationDto) {
        Registration registration = registrationMapper.convertToEntity(registrationDto);
        registrationRepository.save(registration);
    }

    public Registration getUserById(Long id) {

        return registrationRepository.getReferenceById(id);
    }

    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    public Iterable<Registration> findAllWithComment() {
        return registrationRepository.findAllPrestationWithComment();
    }


    public Iterable<Registration> findAllByPrestationId(Long id) {
        return registrationRepository.findAllByPrestationId(id);
    }

}
