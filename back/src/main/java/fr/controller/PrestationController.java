package fr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.exception.ExceptionJsonDetail;
import fr.model.ResponseApi;
import fr.repository.PrestationRepository;
import fr.dto.PrestationDto;
import fr.entity.Prestation;
import fr.entity.Registration;
import fr.entity.User;
import fr.enums.MessageApiEnum;
import fr.enums.RoleEnum;
import fr.service.PrestationService;
import fr.service.RegistrationService;
import fr.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class PrestationController {
    @Autowired
    PrestationService prestationService;

    @Autowired
    PrestationRepository prestationRepository;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    UserService userService;

    @GetMapping("/prestations")
    public List<PrestationDto> getAllPrestations() {

        return prestationService.getAllPrestations();
    }

    @GetMapping("/prestations/{id}")
    public ResponseEntity<String> getPrestation(@PathVariable Integer id) {
        try {

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body(prestationService.getPrestationById(id));
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON)
                    .body(exceptionJsonDetail.getNotFound());
        }
    }

    @PostMapping("/prestations")
    public Prestation createPrestation(@RequestBody PrestationDto prestationDto) {

        return prestationService.createPrestation(prestationDto);
    }

    @DeleteMapping("/prestations/{id}")
    public void deletePrestation(@PathVariable Integer id) {

        prestationService.deletePrestationById(id);
    }

    @GetMapping("/prestations/{prestationId}/registration")
    public ResponseApi bookedRegistration(@PathVariable Integer prestationId) {
        User user = userService.getUserConnected();
        ResponseApi responseApi = new ResponseApi();
        Prestation prestation = prestationRepository.findById(prestationId).get();
        responseApi.setResponseValid(false);
        
        if (user != null) {

            Registration registration = registrationService.getRegistrationByUserIdAndPrestationId(user.getId(),
                    prestationId);

            if (registration != null) {
                responseApi.setMessage(MessageApiEnum.REGISTRATION_ALREADY.getMessage());

            } else if (prestation.getPlaceAvailable() == 0) {
                responseApi.setMessage(MessageApiEnum.REGISTRATION_FULL.getMessage());

            } else {
                prestationService.subtractOnePlaceAvailableInPrestationById(prestationId);
                registrationService.addRegistrationByUserIdAndPrestationId(user, prestation);
                responseApi.setResponseValid(true);
            }
        } else {
                responseApi.setMessage(MessageApiEnum.NEED_TO_BE_CONNECTED.getMessage());
        }
        return responseApi;
    }
}
