package fr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.dto.UserDto;
import fr.entity.User;
import fr.mapper.UserMapper;
import fr.model.UserConnected;
import fr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserConnected userConnected;

    public User createUser(UserDto userDto) {
        PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
        User user = userMapper.convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.getReferenceById(id);
    }

    public Optional<User> findUserByEmail(String email) {     
        return userRepository.findByEmail(email);
    }

    public User updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));

        user.setLastname(userDto.getLastname());
        user.setFirstname(userDto.getFirstname());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        return userRepository.save(user);
    }

    public User getUserConnected() { 
          
        return userConnected.getUserConnected();
    }
}
