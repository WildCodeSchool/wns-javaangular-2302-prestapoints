package fr.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.exception.ExceptionJsonDetail;
import fr.dto.PrestationDto;
import fr.entity.Image;
import fr.service.ImageService;
import fr.service.PrestationService;

@RestController
@CrossOrigin("*")
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


    
    @PostMapping("/prestations")
    public ResponseEntity<String> createPrestation(@RequestParam(value = "image", required = false) MultipartFile image,
                                         @RequestParam("prestation") String prestationJson){
       
           Image imageSave = new Image();
       if (image != null){
            try {
            imageSave = imageService.saveImage(image);
            } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        
        
        //gestion dde l'enrigistrement de l'entité
        ObjectMapper objectMapper = new ObjectMapper();
        PrestationDto prestationDto;
        try {
            prestationDto = objectMapper.readValue(prestationJson, PrestationDto.class);
            List <Image> images = new ArrayList<Image>();
            images.add(imageSave);
            prestationDto.setImages(images);

        } catch (JsonProcessingException e) {
            // Gérer l'erreur de désérialisation
            return ResponseEntity.badRequest().body("Invalid prestation data");
        }
        
        try {
            
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(prestationService.createPrestation(prestationDto));
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(exceptionJsonDetail.getNotFound());
        }

      
    }

    @DeleteMapping("/prestations/{id}")
    public void deletePrestation(@PathVariable Integer id){

        prestationService.deletePrestationById(id);
    }
}
