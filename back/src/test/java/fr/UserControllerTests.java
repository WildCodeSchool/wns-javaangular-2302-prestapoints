package fr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import fr.config.JwtAuthenticationFilter;
import fr.config.PasswordEncoderConfig;
import fr.config.WebSecurityConfig;
import fr.controller.AdminController;
import fr.controller.AuthController;
import fr.controller.CategoryController;
import fr.controller.ImageController;
import fr.controller.PrestationController;
import fr.dto.UserDto;
import fr.entity.Avatar;
import fr.entity.User;
import fr.fixture.CategoryFixtures;
import fr.fixture.ImageFixtures;
import fr.fixture.LocationFixtures;
import fr.fixture.PrestationFixtures;
import fr.fixture.RegistrationFixtures;
import fr.fixture.RoleFixtures;
import fr.fixture.TypeFixtures;
import fr.fixture.UserFixtures;
import fr.helper.JwtUtils;
import fr.mapper.UserMapper;
import fr.model.ResponseApi;

import fr.repository.AvatarRepository;
import fr.service.ImageService;
import fr.service.SecurityUserService;
import fr.service.UserService;

@WebMvcTest
@Import({ WebSecurityConfig.class, PasswordEncoderConfig.class, JwtUtils.class, JwtAuthenticationFilter.class,
        AuthController.class })
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTests {

    // must MockBean all fixtures classes to not have Exceptions errors with the
    // PostConstruct.
    @MockBean
    private ImageFixtures imageFixtures;
    @MockBean
    private UserFixtures userFixtures;
    @MockBean
    private PrestationFixtures prestationFixtures;
    @MockBean
    private CategoryFixtures categoryFixtures;
    @MockBean
    private LocationFixtures locationFixtures;
    @MockBean
    private RegistrationFixtures registrationFixtures;
    @MockBean
    private TypeFixtures typeFixtures;
    @MockBean
    private RoleFixtures roleFixtures;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private Avatar avatar;
    @MockBean
    private User user;

    // we MockBean also this list in consequences of the test
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

    @Autowired
    private MockMvc mockMvc;

    // -----------------------GET USER--------------------------
    // @Test
    // public void testGetUserConnected_ShouldReturnStatusOk() throws Exception {
    //     // Arrange
    //     // Act & Assert
    //     mockMvc.perform(
    //             get("/users/1"))
    //             .andExpect(status().isOk());
    // }

    // @Test
    // public void testGetUser_ShouldReturnStatusNotFound() throws Exception {
    //     // Arrange
    //     // Act & Assert
    //     mockMvc.perform(
    //             get("/users/"))
    //             .andExpect(status().isNotFound());
    // }

    // @Test
    // public void testGetUser_ShouldReturnStatusBadResquet() throws Exception {
    //     // Arrange
    //     // Act & Assert
    //     mockMvc.perform(
    //             get("/users/toto"))
    //             .andExpect(status().isBadRequest());
    // }

    // -----------------------CREATE USER--------------------------
    @Test
    public void testCreateUser_ShouldReturnStatusOk() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setEmail("");
        // userDto.setPassword("");
        String bodyUser = new ObjectMapper().writeValueAsString(userDto);

        // Act & Assert
        mockMvc.perform(post("/public/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyUser))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUser_ShouldReturnValidResponse() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setEmail("to@to.to");
        // userDto.setPassword("toto123456");
        String body = new ObjectMapper().writeValueAsString(userDto);

        ResponseApi responseApi = new ResponseApi();
        responseApi.setResponseValid(true);
        responseApi.setMessage(null);
        String response = new ObjectMapper().writeValueAsString(responseApi);

        // Act & Assert
        mockMvc.perform(post("/public/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    public void testCreateUser_ShouldReturnErrorInvalidEmailResponse() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setEmail("toto.to");
        // userDto.setPassword("toto123456");
        String body = new ObjectMapper().writeValueAsString(userDto);

        ResponseApi responseApi = new ResponseApi();
        responseApi.setResponseValid(false);
        responseApi.setMessage("L'email n'est pas conforme.");
        String response = new ObjectMapper().writeValueAsString(responseApi);

        // Act & Assert
        mockMvc.perform(post("/public/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    public void testCreateUser_ShouldReturnErrorEmailExistResponse() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setEmail("to@to.to");
        // userDto.setPassword("toto123456");
        String body = new ObjectMapper().writeValueAsString(userDto);

        User user = new User();
        user.setEmail("to@to.to");
        user.setPassword("toto123456");

        ResponseApi responseApi = new ResponseApi();
        responseApi.setResponseValid(false);
        responseApi.setMessage("L'email existe d\u00C3\u00A9j\u00C3\u00A0 !");
        String response = new ObjectMapper().writeValueAsString(responseApi);

        when(userService.findUserByEmail(anyString())).thenReturn(Optional.of(user));

        // Act & Assert
        mockMvc.perform(post("/public/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    // -----------------------EMAIL VERIFICATION--------------------------
    @Test
    public void testEmailVerification_ShouldReturnBadRequest() throws Exception {
        // Arrange
        // Act & Assert
        mockMvc.perform(post("/public/email/verification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEmailVerification_ShouldReturnTrue() throws Exception {
        // Arrange
        // UserService is Mocked (no reel creation with createUser() in DB) 
        // so we simulate the return of the method called by the api
        // and we test the DB in DataUserTests.java
        when(userService.findUserByEmail(anyString())).thenReturn(Optional.of(new User()));
        // Act & Assert
        mockMvc.perform(post("/public/email/verification")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"test@test.com\""))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testUpdateUser_ShouldReturnStatusOkAndSuccessMessage() throws Exception {
        //Arrange
        User connectedUser = new User();
        connectedUser.setEmail("user@example.com");
        // when(authController.getUserConnected()).thenReturn(connectedUser);

        //Act
        

        //Assert
        mockMvc.perform(post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Modification enregistrée"));
    }

    @Test
    public void testUpdateUser_Failed() throws Exception {
        User connectedUser = new User();
        connectedUser.setEmail("");

        UserDto userDto = new UserDto();
        userDto.setEmail("user@example.com");

        when(authController.getUserConnected()).thenReturn(connectedUser);

        mockMvc.perform(post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Erreur lors de la modification"));
    }

    @Test
    public void testUploadAvatar_ShouldReturnStatusOk() throws Exception {
        User connectedUser = user;
        connectedUser.setId(1);

        when(authController.getUserConnected()).thenReturn(connectedUser);
        when(connectedUser.getAvatar()).thenReturn(avatar);

        byte[] fileContent = "Test avatar".getBytes();
        MockMultipartFile file = new MockMultipartFile("image", "avatar.png", MediaType.IMAGE_PNG_VALUE, fileContent);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/avatar")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("Avatar mis à jour avec succes"));
    }

    @Test
    public void testUploadAvatar_ShouldReturnStatusOkWithError() throws Exception {
        
        when(authController.getUserConnected()).thenReturn(null);

        byte[] fileContent = "Test avatar".getBytes();
        MockMultipartFile file = new MockMultipartFile("image", "avatar.png", MediaType.IMAGE_PNG_VALUE, fileContent);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/avatar")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("Echec mise à jour Avatar"));

    }
}
