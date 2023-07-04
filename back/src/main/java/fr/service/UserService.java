package fr.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.dto.UserDto;
import fr.entity.Role;
import fr.entity.User;
import fr.enums.RoleEnum;
import fr.mapper.UserMapper;
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
}
