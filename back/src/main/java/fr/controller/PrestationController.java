package fr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.exception.ExceptionJsonDetail;
import fr.dto.PrestationDto;
import fr.entity.Image;
import fr.service.ImageService;
import fr.service.PrestationService;

@RestController
//@CrossOrigin("*")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class PrestationController {
    @Autowired
    PrestationService prestationService;

    @Autowired
    ImageService imageService;

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

    //@PostMapping("/prestations")
    //public Prestation createPrestation(@RequestBody PrestationDto prestationDto){
    //    
    //    return prestationService.createPrestation(prestationDto);
    //}


  
   /* @PostMapping(value="/prestations", consumes="multipart/form-data")
    //public ResponseEntity<String> createPrestation(@RequestBody() Map<String, Object>  body){
     public ResponseEntity<?> createPrestation(@RequestPart("picture") MultipartFile picture){                                  
    
        System.out.println(picture);
       // Retrieve values from the request body
        String prestationJson = (String) body.get("prestation");
        // Convert the prestation JSON to your desired object type
        // For example, using Jackson ObjectMapper:
        ObjectMapper objectMapper = new ObjectMapper();
        PrestationDto prestationDto;
        try {
            prestationDto = objectMapper.readValue(prestationJson, PrestationDto.class);
            // Process the prestationDto object as needed
        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            return ResponseEntity.badRequest().body("Invalid prestation JSON");
        }
        
        // Access other values from the body map
        // For example, retrieving the uploaded picture
        MultipartFile picture = (MultipartFile) body.get("picture");
        if (picture != null) {
            // Process the uploaded picture
        }
        
        // Handle the request and return a response
        return ResponseEntity.ok("Prestation created successfully");
    }                 */                  
    
    


    @PostMapping(value="/prestations", consumes="multipart/form-data")
    public ResponseEntity<?> createPrestation(@RequestPart("picture") MultipartFile picture,
                                          @RequestPart("prestation") String prestationJson) {
                                            
                //gestion dde l'enrigistrement de l'entité
        ObjectMapper objectMapper = new ObjectMapper();
        PrestationDto prestationDto;
        try {
            prestationDto = objectMapper.readValue(prestationJson, PrestationDto.class);
        } catch (JsonProcessingException e) {
            // Gérer l'erreur de désérialisation
            return ResponseEntity.badRequest().body("Invalid prestationDto data");
        }
        
        try {
            
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(prestationService.createPrestation(prestationDto, picture));
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(exceptionJsonDetail.getNotFound());
        }

    } 



    @DeleteMapping("/prestations/{id}")
    public void deletePrestation(@PathVariable Integer id){

        prestationService.deletePrestationById(id);
    }
}
