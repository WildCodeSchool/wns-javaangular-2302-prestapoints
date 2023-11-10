package fr.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.entity.Image;
import fr.exception.ExceptionJsonDetail;
import fr.mapper.ImageMapper;
import fr.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;
    
   @Autowired
    private ImageMapper imageMapper;

    public Image getImageById(Integer id) throws ExceptionJsonDetail {
        Image image = imageRepository.getReferenceById(id);
        //ImageDto imageDto  = imageMapper.convertToDto(image);
        return image;
    } 

    public ResponseEntity<Integer> ImageSav(MultipartFile file) {
        try {
            Image image = new Image();
            image.setData(file.getBytes());
            image  = imageRepository.save(image);
            return ResponseEntity.ok().body(image.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    
}
