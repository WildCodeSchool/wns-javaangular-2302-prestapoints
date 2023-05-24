package fr.controller;

import java.io.IOException;
import java.nio.file.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import fr.exception.ExceptionJsonDetail;
import fr.dto.PrestationDto;
import fr.entity.Prestation;
import fr.service.PrestationService;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class PrestationController {
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

    
    @PostMapping(value = "/prestations/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestPart("image") MultipartFile image) {
        // Votre logique de traitement pour enregistrer l'image sur le serveur
        if (!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
                
                // Chemin de destination sur le serveur
                String uploadDirectory =  System.getProperty("user.home") + "/uploads/";
                String fileName = image.getOriginalFilename();
                Path destinationPath = Path.of(uploadDirectory + fileName);

                // Enregistrez les bytes de l'image sur le serveur
                Files.copy(image.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                
                String imageUrl = "http://localhost:8080/prestations/images/" + fileName;
                return "{\"imageUrl\":\"" + imageUrl + "\"}";
            } catch (Exception e) {
                return "{\"error\":\"Error uploading image\"}";
            }
        }
        return "{\"error\":\"No image selected\"}";
    }

    @GetMapping(value = "prestations/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        // Chemin du répertoire où sont stockées les images
        String imageDirectory = System.getProperty("user.home") + "/uploads/";

        try {
            Path imagePath = Paths.get(imageDirectory, imageName);
            byte[] imageBytes = Files.readAllBytes(imagePath);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (IOException e) {
            // Gérer l'erreur si l'image n'est pas trouvée
            return ResponseEntity.notFound().build();
        }
    }
}
