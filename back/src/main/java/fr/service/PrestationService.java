package fr.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.exception.ExceptionJsonDetail;
import fr.dto.PrestationDto;
import fr.entity.Image;
import fr.entity.Prestation;
import fr.mapper.PrestationMapper;
import fr.repository.ImageRepository;
import fr.repository.PrestationRepository;

@Service
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private PrestationMapper prestationMapper;

    @Autowired
    private ImageRepository imageRepository;

    public List<PrestationDto> getAllPrestations() {
        List<PrestationDto> prestationDtos = new ArrayList<>();
        List<Prestation> prestations = prestationRepository.findAll();

        for (Prestation prestation : prestations) {
            prestationDtos.add(prestationMapper.convertToDto(prestation));
        }
        return prestationDtos;
    }

    public String getPrestationById(int id) throws ExceptionJsonDetail {
        Prestation prestation = prestationRepository.findById(id).orElseThrow(() -> new ExceptionJsonDetail());
        PrestationDto prestationDto = prestationMapper.convertToDto(prestation);
        JSONObject object = new JSONObject(prestationDto);
        return  object.toString();
    }

    public ResponseEntity<String> createPrestation(PrestationDto prestationDto, MultipartFile picture)throws ExceptionJsonDetail {

        Prestation prestation = new Prestation();
        
        try {
            prestation = prestationMapper.convertToEntity(prestationDto);
            prestation = prestationRepository.save(prestation);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save prestation", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (picture != null){
             try {
            
                byte[] imageData = picture.getBytes();
                Image image = new Image(imageData);
                image.setPrestation(prestation);
                image =  imageRepository.save(image);
              
             } catch (Exception e) {
                return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
             }
        }
        
        //mise a jour de prestation avec le tableau images
        prestation =prestationRepository.getReferenceById(prestation.getId());
        prestationDto = prestationMapper.convertToDto(prestation);
        JSONObject objectDto = new JSONObject(prestationDto);
        System.out.println(objectDto.toString());
        return new ResponseEntity<>(objectDto.toString(), HttpStatus.OK);          
    }

    public void deletePrestationById(int id){
        prestationRepository.deleteById(id);
    }
}
