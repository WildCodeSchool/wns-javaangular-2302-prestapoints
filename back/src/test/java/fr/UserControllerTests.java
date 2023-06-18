package fr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.config.JwtAuthenticationFilter;
import fr.config.PasswordEncoderConfig;
import fr.config.WebSecurityConfig;
import fr.controller.PrestationController;
import fr.controller.UserController;
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

@WebMvcTest(controllers = UserController.class)
@Import({ WebSecurityConfig.class, PasswordEncoderConfig.class, JwtUtils.class, JwtAuthenticationFilter.class })
public class UserControllerTests {

    // must call all this list to not have Exceptions errors.
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

    @Autowired
    private MockMvc mockMvc;


 @Test
    public void testGetUser_ShouldReturnTrue() throws Exception{
        // Arrange
        Integer userId = 11;
        // Act & Assert
        mockMvc.perform(
                get("/users/" + userId))
                .andExpect(status().isOk());
    }

}
