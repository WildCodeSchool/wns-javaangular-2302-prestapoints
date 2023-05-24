package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.repository.RegistrationRepository;

@Service
public class ProviderService {

    @Autowired
    RegistrationRepository providerRepository;

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}
