package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.dto.UserDto;
import fr.mapper.UserToDtoMapper;
import fr.model.User;
import fr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserToDtoMapper userToDtoMapper;

    public void createUser(UserDto userDto) {
        User user = userToDtoMapper.convertToEntity(userDto);
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        
        return userRepository.getById(id);
    }
}
