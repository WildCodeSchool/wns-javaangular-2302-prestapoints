package fr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.exception.ExceptionJsonDetail;
import fr.dto.PrestationDto;
import fr.entity.Prestation;
import fr.service.PrestationService;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class EvaluationController {
    @Autowired
    PrestationService prestationService;

    @GetMapping("/prestations")
    public List<PrestationDto> getAllPrestations() {

        return prestationService.getAllPrestations();
    }

    @GetMapping("/prestations/{id}")
    public ResponseEntity<String> getPrestation(@PathVariable Integer id){
        try {
            
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(prestationService.getPrestationById(id));
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(exceptionJsonDetail.getNotFound());
        }
    }

    @PostMapping("/prestations")
    public Prestation createPrestation(@RequestBody PrestationDto prestationDto){

        return prestationService.createPrestation(prestationDto);
    }

    @DeleteMapping("/prestations/{id}")
    public void deletePrestation(@PathVariable Integer id){

        prestationService.deletePrestationById(id);
    }
}
