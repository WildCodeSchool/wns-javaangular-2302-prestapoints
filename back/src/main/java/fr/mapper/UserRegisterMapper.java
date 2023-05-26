package fr.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dto.UserRegisterDto;
import fr.entity.UserRegister;

@Component
public class UserRegisterMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public UserRegisterDto convertToDto(UserRegister userRegister) {
        UserRegisterDto userRegisterDto = modelMapper.map(userRegister, UserRegisterDto.class);

        return userRegisterDto;
    }

    public UserRegister convertToEntity(UserRegisterDto userRegisterDto) {
        UserRegister userRegister = modelMapper.map(userRegisterDto, UserRegister.class);

        return userRegister;
    }

    public Iterable<UserRegisterDto> convertAllToDto(List<UserRegister> usersRegister) {
        List<UserRegisterDto> userRegistesDto = new ArrayList<>();

        for (UserRegister userRegister : usersRegister) {
            userRegistesDto.add(modelMapper.map(userRegister, UserRegisterDto.class));
        }

        return userRegistesDto;
    }
}
