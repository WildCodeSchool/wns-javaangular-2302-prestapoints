package fr.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import fr.controller.AuthController;
import fr.dto.UserDto;
import fr.entity.Role;
import fr.entity.User;
import fr.enums.MessageApiEnum;
import fr.enums.RoleEnum;
import fr.enums.TablesEnum;
import fr.mapper.UserMapper;
import fr.model.ResponseApi;
import fr.repository.RoleRepository;
import fr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public User createUser(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Long instant = Instant.now().getEpochSecond();
        Timestamp timestamp = new Timestamp(instant);
        user.setCreation(timestamp);
        Role role = roleRepository.findByName(RoleEnum.ROLE_USER.getRole()).get();
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User getUserById(Integer id) {

        return userRepository.getReferenceById(id);
    }

    public List<UserDto> getUsersDto() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<UserDto>();

        for (User user : users) {
            usersDto.add(userMapper.convertToDto(user));
        }

        return usersDto;
    }

    public Optional<User> findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public ResponseApi deleteUser(@RequestBody UserDto userDto) {
        try {
            User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);

            if (user != null) {
                userRepository.delete(user);

                return new ResponseApi(true, MessageApiEnum.DELETE_SUCCESS.getMessage());
            } else {

                return new ResponseApi(false, "Utilisateur non trouvé");
            }
        } catch (DataIntegrityViolationException e) {
            String blockingTable = extractBlockingTableFromException(e);

            return new ResponseApi(false,
                    "Impossible de supprimer l'utilisateur en raison de sa présence dans : " + blockingTable);
        }
    }

    private String extractBlockingTableFromException(DataIntegrityViolationException exception) {
        String errorMessage = exception.getRootCause().getMessage();
        String[] parts = errorMessage.split("`");
        String table = "inconnue";

        if (parts.length >= 3) {
            switch (parts[3]) {
                case "registration":
                    table = "inscription";
                    break;

                default:
                    break;
            }
        }
        return table;
    }
}
