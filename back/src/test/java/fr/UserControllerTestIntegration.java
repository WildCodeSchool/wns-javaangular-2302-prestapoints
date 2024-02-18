package fr;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import fr.controller.AdminController;
import fr.controller.AuthController;
import fr.controller.CategoryController;
import fr.controller.ImageController;
import fr.controller.PrestationController;
import fr.dto.UserDto;
import fr.dto.UserSignInDto;
import fr.entity.Avatar;
import fr.entity.User;
import fr.mapper.UserMapper;
import fr.model.ResponseApi;
import fr.repository.*;
import fr.service.ImageService;
import fr.service.PrestationService;
import fr.service.RegistrationService;
import fr.service.SecurityUserService;
import fr.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestIntegration {
    @MockBean
    private Avatar avatar;
    @MockBean
    private User user;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private ImageService imageService;
    @MockBean
    private SecurityUserService securityUserService;
    @MockBean
    private UserService userService;
    @MockBean
    private PrestationController prestationController;
    @MockBean
    private AdminController adminController;
    @MockBean
    private ImageController imageController;
    @MockBean
    private AuthController authController;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private CategoryController categoryController;
    @MockBean
    private PrestationRepository prestationRepository;
    @MockBean
    private RegistrationService registrationService;
    @MockBean
    private PrestationService prestationService;
    @MockBean
    private ResponseApi responseApi;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSignInDto userSignInDto;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUser_Success() throws Exception {
        
        userSignInDto.setEmail("charly@test.com");
        userSignInDto.setLastname("YourLastName");
        userSignInDto.setFirstname("YourFirstName");
        userSignInDto.setPhone("0123456789");

        String requestBody = new ObjectMapper().writeValueAsString(userSignInDto);

        when(userService.findUserByEmail("charly@test.com")).thenReturn(Optional.empty());
        when(userService.createUser(any(UserSignInDto.class))).thenReturn(true);

        mockMvc.perform(post("/public/sign-in")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.responseValid").value(true));

        verify(userService).findUserByEmail("charly@test.com");
        verify(userService).createUser(any(UserSignInDto.class));
    }
}

