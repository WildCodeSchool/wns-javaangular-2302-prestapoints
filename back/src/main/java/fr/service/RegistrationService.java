package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.entity.Prestation;
import fr.entity.Registration;
import fr.entity.User;
import fr.mapper.RegistrationMapper;
import fr.repository.RegistrationRepository;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    RegistrationMapper registrationMapper;

    public Registration addRegistrationByUserIdAndPrestationId(User user, Prestation prestation) {
        
        Registration registration = new Registration();
        registration.setPrestation(prestation);
        registration.setUser(user);

        return registrationRepository.save(registration);
    }

    public Registration getRegistrationById(Integer id) {

        return registrationRepository.getReferenceById(id);
    }
    
    public Registration getRegistrationByUserIdAndPrestationId(Integer userId, Integer prestationId) {
            
        return registrationRepository.getRegistrationByUserIdAndPrestationId(userId, prestationId);
    }

    public void deleteRegistration(Integer id) {
        registrationRepository.deleteById(id);
    }
}
