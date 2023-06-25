package fr;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.springframework.http.MediaType;
import fr.config.JwtAuthenticationFilter;
import fr.config.PasswordEncoderConfig;
import fr.config.WebSecurityConfig;
import fr.controller.PrestationController;
import fr.controller.UserController;
import fr.dto.UserDto;
import fr.entity.User;
import fr.fixture.CategoryFixtures;
import fr.fixture.LocationFixtures;
import fr.fixture.PrestationFixtures;
import fr.fixture.RegistrationFixtures;
import fr.fixture.TypeFixtures;
import fr.fixture.UserFixtures;
import fr.helper.JwtUtils;
import fr.mapper.UserMapper;
import fr.service.SecurityUserService;
import fr.service.UserService;

@WebMvcTest
@Import({ WebSecurityConfig.class, PasswordEncoderConfig.class, JwtUtils.class, JwtAuthenticationFilter.class, })
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTests {

    // must MockBean all fixtures classes to not have Exceptions errors with the PostConstruct.
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
    
    // we MockBean also this list in consequences of the test
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private SecurityUserService securityUserService;
    @MockBean
    private UserService userService;
    @MockBean
    private PrestationController prestationController;
    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUser_ShouldReturnStatusOk() throws Exception {
        // Arrange
        // Act & Assert
        mockMvc.perform(
                get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUser_ShouldReturnStatusNotFound() throws Exception {
        // Arrange
        // Act & Assert
        mockMvc.perform(
                get("/users/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetUser_ShouldReturnStatusBadResquet() throws Exception {
        // Arrange
        // Act & Assert
        mockMvc.perform(
                get("/users/toto"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateUser_ShouldReturnStatusOk() throws Exception {
        // Arrange
        UserDto userDto = new UserDto();
        String bodyUser = new ObjectMapper().writeValueAsString(userDto);

        // Act & Assert
        mockMvc.perform(post("/public/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyUser))
                .andExpect(status().isOk());
    }

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
}
