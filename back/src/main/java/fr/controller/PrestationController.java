package fr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.exception.ExceptionJsonDetail;
import fr.model.Prestation;
import fr.service.PrestationService;

@RestController
public class PrestationController {
    @Autowired
    PrestationService prestationService;

    @GetMapping("/prestations")
    public List<Prestation> getAllPrestations() {
        return prestationService.getAllPrestations();
    }

    @GetMapping("/prestations/{id}")
    public ResponseEntity<String> getPrestation(@PathVariable int id){
        try {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(prestationService.getPerstationById(id));
        } catch (ExceptionJsonDetail exceptionJsonDetail) {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(exceptionJsonDetail.getNotFound());
        }
    }

    @PostMapping("/prestations")
    public Prestation createVehicle(@RequestBody Prestation prestation){
        return prestationService.createPrestation(prestation);
    }

    @DeleteMapping("/prestations/{id}")
    public void deletePlanet(@PathVariable int id){
        prestationService.deletePrestationById(id);
    }
    
}
