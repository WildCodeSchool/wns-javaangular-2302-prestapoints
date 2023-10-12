package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dto.ImageDto;
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
}
