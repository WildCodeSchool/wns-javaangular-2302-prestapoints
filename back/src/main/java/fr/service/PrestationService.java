package fr.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.exception.ExceptionJsonDetail;
import fr.model.Prestation;
import fr.repository.PrestationRepository;

@Service
public class PrestationService {

    @Autowired
    PrestationRepository prestationRepository;

    public List<Prestation> getAllPrestations() {
        List<Prestation> prestations = prestationRepository.findAll();
        return prestations;
    }

    public Prestation createPrestation(Prestation prestation) {
        return prestationRepository.save(prestation);
    }

    public void deletePrestationById(int id){
        prestationRepository.deleteById(id);
    }

    public String getPerstationById(int id) throws ExceptionJsonDetail {
        Prestation prestation = prestationRepository.findById(id).orElseThrow(() -> new ExceptionJsonDetail());

        JSONObject object = new JSONObject(prestation);
        return  object.toString();
    }
    
}
