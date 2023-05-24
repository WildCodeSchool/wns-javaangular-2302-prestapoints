package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.repository.RegistrationRepository;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }
}
