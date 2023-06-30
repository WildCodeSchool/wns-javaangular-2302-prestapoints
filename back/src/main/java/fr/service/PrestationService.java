package fr.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.exception.ExceptionJsonDetail;

import fr.dto.PrestationDto;
import fr.entity.Prestation;
import fr.mapper.PrestationMapper;
import fr.repository.PrestationRepository;

@Service
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private PrestationMapper prestationMapper;

    public List<PrestationDto> getAllPrestations() {
        List<PrestationDto> prestationDtos = new ArrayList<>();
        List<Prestation> prestations = prestationRepository.findAll();

        for (Prestation prestation : prestations) {
            prestationDtos.add(prestationMapper.convertToDto(prestation));
        }

        return prestationDtos;
    }

    public String getPrestationById(Integer id) throws ExceptionJsonDetail {
        Prestation prestation = prestationRepository.findById(id).orElseThrow(() -> new ExceptionJsonDetail());
        prestationMapper.convertToDto(prestation);
        JSONObject object = new JSONObject(prestation);

        return object.toString();
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
}
