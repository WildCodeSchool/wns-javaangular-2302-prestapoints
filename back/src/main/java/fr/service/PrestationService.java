package fr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import fr.dto.PrestationDto;
import fr.entity.Category;
import fr.entity.Prestation;
import fr.mapper.PrestationMapper;
import fr.model.ExceptionJsonDetail;
import fr.repository.CategoryRepository;
import fr.repository.PrestationRepository;

@Service
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private PrestationMapper prestationMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<PrestationDto> getAllPrestations() {
        List<PrestationDto> prestationDtos = new ArrayList<>();
        List<Prestation> prestations = prestationRepository.findAll();

        for (Prestation prestation : prestations) {
            prestationDtos.add(prestationMapper.convertToDto(prestation));
        }

        return prestationDtos;
    }

    public PrestationDto getPrestationById(Integer id) throws ExceptionJsonDetail {
        Prestation prestation = prestationRepository.findById(id).orElseThrow(() -> new ExceptionJsonDetail("Pas de prestation trouvé"));
        PrestationDto prestationDto = prestationMapper.convertToDto(prestation);

        return prestationDto;
    }

    public Prestation createPrestation(PrestationDto prestationDto) {
        Prestation prestation = prestationMapper.convertToEntity(prestationDto);

        return prestationRepository.save(prestation);
    }

    public void deletePrestationById(int id) {
        prestationRepository.deleteById(id);
    }

    public Prestation subtractOnePlaceAvailableInPrestationById(Integer id) {
        Prestation prestation = prestationRepository.findById(id).get();

        if (prestation.getPlaceAvailable() > 0) {
            prestation.setPlaceAvailable(prestation.getPlaceAvailable() - 1);
        }

        return prestationRepository.save(prestation);
    }
    
    public Prestation addOnePlaceAvailableInPrestationById(Integer id) {
        Prestation prestation = prestationRepository.findById(id).get();

        if (prestation != null) {
            prestation.setPlaceAvailable(prestation.getPlaceAvailable() + 1);
        }

        return prestationRepository.save(prestation);
    }

    public List<PrestationDto> getPrestationsByCategory(Integer categoryId) throws ExceptionJsonDetail {
       Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ExceptionJsonDetail("Catégorie non trouvée"));

        List<Prestation> prestations = prestationRepository.findByTypeCategory(category);

        List<PrestationDto> prestationDtos = prestations.stream()
            .map(prestationMapper::convertToDto)
            .collect(Collectors.toList());

        return prestationDtos;
    }
}
