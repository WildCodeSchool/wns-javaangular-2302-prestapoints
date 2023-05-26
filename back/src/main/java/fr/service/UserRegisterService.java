package fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.repository.UserRegisterRepository;

@Service
public class UserRegisterService {

    @Autowired
    UserRegisterRepository userRegisterRepository;

    public void deleteUserRegister(Integer id) {
        userRegisterRepository.deleteById(id);
    }
}
