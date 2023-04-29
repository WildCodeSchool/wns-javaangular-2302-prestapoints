package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.dto.PrestationDto;
import fr.mapper.PrestationMapper;
import fr.model.Prestation;
import fr.repository.PrestationRepository;

@Service
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private PrestationMapper prestationMapper;

    public void createPrestation(PrestationDto prestationDto) {
        Prestation prestation = prestationMapper.convertToEntity(prestationDto);
        prestationRepository.save(prestation);
    }

    public Prestation getPrestationById(Long id) {

        return prestationRepository.getReferenceById(id);
    }

    public Iterable<Prestation> findAllPrestations() {

        return prestationRepository.findAll();
    }

    public void deletePrestation(Long id) {
        prestationRepository.deleteById(id);
    }

}
