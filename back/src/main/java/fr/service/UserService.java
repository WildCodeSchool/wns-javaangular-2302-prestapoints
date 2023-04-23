package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.dto.UserDto;
import fr.mapper.UserMapper;
import fr.model.User;
import fr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void createUser(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        
        return userRepository.getReferenceById(id);
        return userRepository.getReferenceById(id);
    }
}
