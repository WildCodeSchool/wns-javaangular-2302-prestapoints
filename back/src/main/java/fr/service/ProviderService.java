package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.repository.ProviderRepository;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}
