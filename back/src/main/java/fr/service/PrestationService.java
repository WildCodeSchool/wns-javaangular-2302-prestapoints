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
import fr.dto.LocationDto;
import fr.dto.PrestationDto;
import fr.entity.Image;
import fr.entity.Location;
import fr.entity.Prestation;
import fr.mapper.LocationMapper;
import fr.mapper.PrestationMapper;
import fr.repository.ImageRepository;
import fr.repository.LocationRepository;
import fr.repository.PrestationRepository;

@Service
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private PrestationMapper prestationMapper;

    @Autowired
    private LocationMapper locationMapper;


    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LocationRepository locationRepository;

    public List<PrestationDto> getAllPrestations() {
        List<PrestationDto> prestationDtos = new ArrayList<>();
        List<Prestation> prestations = prestationRepository.findAll();

        for (Prestation prestation : prestations) {
            prestationDtos.add(prestationMapper.convertToDto(prestation));
        }

        return prestationDtos;
    }

    public String getPrestationById(Integer id) throws ExceptionJsonDetail {
        Prestation prestation = prestationRepository.findById(id).orElseThrow(() -> new ExceptionJsonDetail());
        PrestationDto prestationDto = prestationMapper.convertToDto(prestation);
        JSONObject object = new JSONObject(prestationDto);
        return  object.toString();
    }

    public ResponseEntity<String> createPrestation(PrestationDto prestationDto, LocationDto locationDto, MultipartFile picture)throws ExceptionJsonDetail {


        Location location = new Location(); 
        try {
            location = locationMapper.convertToEntity(locationDto);
            location = locationRepository.save(location);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save prestation", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        Prestation prestation = new Prestation();
        try {
            prestation = prestationMapper.convertToEntity(prestationDto);
            prestation.setLocation(location) ;
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

    public Prestation createPrestation(PrestationDto prestationDto) {
        Prestation prestation = prestationMapper.convertToEntity(prestationDto);

        return prestationRepository.save(prestation);
    }

    public void deletePrestationById(int id) {
        prestationRepository.deleteById(id);
    }

    public Prestation subtractOnePlaceAvailableInPrestationById(Integer id) {
        Prestation prestation = prestationRepository.findById(id).get();
        
        if (prestation.getPlaceAvailable() > 0) {
            prestation.setPlaceAvailable(prestation.getPlaceAvailable() - 1);
        }
        
        return prestationRepository.save(prestation);
    }
}
