package fr.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dto.UserDto;
import fr.entity.Role;
import fr.entity.User;
import fr.enums.MessageApiEnum;
import fr.enums.RoleEnum;
import fr.exception.ExceptionJsonDetail;
import fr.mapper.UserMapper;
import fr.model.ResponseApi;
import fr.repository.RoleRepository;
import fr.repository.UserRepository;
import fr.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthController authController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/admin/utilisateurs")
    public List<UserDto> getUsersDto() {

        return userService.getUsersDto();
    }

    @PostMapping("/admin/utilisateurs/utilisateur/creation")
    public ResponseEntity<ResponseApi> saveUser(@RequestBody UserDto userDto) {
        User userConnected = authController.getUserConnected();

        try {
            //TODO verif qu'il reste toujours un admin s'il retire son role admin
            userService.createUser(userDto);

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseApi(true, MessageApiEnum.DELETE_SUCCESS.getMessage()));
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseApi(false, exceptionJsonDetail.getNotFound()));
        }
    }
    
    
    @PostMapping("/admin/utilisateurs/utilisateur/modification")
    public ResponseEntity<ResponseApi> updateUser(@RequestBody UserDto userDto) {
        User userConnected = authController.getUserConnected();

        try {
            //TODO verif qu'il reste toujours un admin s'il retire son role admin
            userService.createUser(userDto);

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(new ResponseApi(true, MessageApiEnum.UPDATE_SUCCESS.getMessage()));
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseApi(false, exceptionJsonDetail.getNotFound()));
        }
    }

    @PostMapping("/admin/utilisateurs/utilisateur/suppression")
    public ResponseEntity<ResponseApi> deleteUser(@RequestBody UserDto userDto) {
        User userConnected = authController.getUserConnected();
        User userToDelete = userService.findUserByEmail(userDto.getEmail()).get();

        if (userConnected.equals(userToDelete)) {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseApi(false, MessageApiEnum.DELETE_FAILED.getMessage()));
        }

        try {
            ResponseApi response = userService.deleteUser(userDto);

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseApi(false, exceptionJsonDetail.getNotFound()));
        }
    }

    @PostMapping("/admin/utilisateurs/utilisateur/suppressions")
    public ResponseEntity<ResponseApi> deleteUsers(@RequestBody List<UserDto> usersDto) {
        User userConnected = authController.getUserConnected();
        List<User> usersToDelete = new ArrayList<User>();
        for (UserDto userDto : usersDto) {
            User user = userService.findUserByEmail(userDto.getEmail()).get();
            if (user != null && !userConnected.equals(user)) {
                usersToDelete.add(user);
            }
        }

        try {
            ResponseApi response = userService.deleteUsers(usersToDelete);

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (ExceptionJsonDetail exceptionJsonDetail) {

            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON)
                    .body(new ResponseApi(false, exceptionJsonDetail.getNotFound()));
        }
    }
}
