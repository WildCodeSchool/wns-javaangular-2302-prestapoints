package fr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.model.Provider;
import fr.repository.ProviderRepository;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }

    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    public Provider getProviderById(Long id) {

        return providerRepository.getReferenceById(id);
    }
}
