package fr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.dto.UserDto;
import fr.entity.User;
import fr.mapper.UserMapper;
import fr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.getReferenceById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
