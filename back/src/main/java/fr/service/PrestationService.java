package fr.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.exception.ExceptionJsonDetail;
import fr.controller.AuthController;
import fr.dto.LocationDto;
import fr.dto.PrestationDto;
import fr.dto.TypeDto;
import fr.dto.UserDto;
import fr.entity.Location;
import fr.entity.Prestation;
import fr.entity.Type;
import fr.entity.User;
import fr.mapper.PrestationMapper;
import fr.mapper.TypeMapper;
import fr.repository.PrestationRepository;
import fr.repository.TypeRepository;

@Service
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private PrestationMapper prestationMapper;
    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private LocationService locationService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private AuthController authController;

    public List<PrestationDto> getAllPrestations() {
        List<PrestationDto> prestationDtos = new ArrayList<>();
        List<Prestation> prestations = prestationRepository.findAll();

        for (Prestation prestation : prestations) {
            prestationDtos.add(prestationMapper.convertToDto(prestation));
        }

        return prestationDtos;
    }

    public PrestationDto getPrestationById(Integer id) throws ExceptionJsonDetail {
        Prestation prestation = prestationRepository.findById(id).orElseThrow(() -> new ExceptionJsonDetail());
        PrestationDto prestationDto = prestationMapper.convertToDto(prestation);

        return prestationDto;
    }

    public PrestationDto createPrestation(PrestationDto prestationDto) {
        Prestation prestation = prestationMapper.convertToEntity(prestationDto);
        
        User user = authController.getUserConnected();
        
        TypeDto typeDto = prestationDto.getType();
        Type type = typeService.createType(typeDto);

        LocationDto locationDto = prestationDto.getLocation();
        Location location = locationService.createLocation(locationDto);

        prestation.setUser(user);
        prestation.setType(type);
        prestation.setLocation(location);
        prestation = prestationRepository.save(prestation);

        return prestationMapper.convertToDto(prestation);
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
    
    public Prestation addOnePlaceAvailableInPrestationById(Integer id) {
        Prestation prestation = prestationRepository.findById(id).get();

        if (prestation != null) {
            prestation.setPlaceAvailable(prestation.getPlaceAvailable() + 1);
        }

        return prestationRepository.save(prestation);
    }
}
