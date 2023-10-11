package fr.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.dto.ImageDto;
import fr.entity.Image;
import fr.service.ImageService;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    ImageService imageService;
    
     @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        // Récupérez l'image Blob en fonction de l'ID
        Image image = imageService.getImageById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Remplacez par le type MIME approprié
        headers.setContentLength(image.getData().length);

        return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
    }
}
