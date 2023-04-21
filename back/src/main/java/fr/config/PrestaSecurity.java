// package fr.config;

// import java.util.List;

// import org.slf4j.Logger;

// import static org.springframework.security.config.Customizer.withDefaults;
// import org.slf4j.LoggerFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// // @Configuration
// @EnableWebSecurity
// public class PrestaSecurity {

//     private static Logger logger = LoggerFactory.getLogger(PrestaSecurity.class);
//     public static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return passwordEncoder;
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests((authz) -> {
//             try {
//                 authz
//                         .anyRequest().authenticated()
//                         .and().formLogin(withDefaults()).httpBasic(withDefaults());
//             } catch (Exception e) {

//                 e.printStackTrace();
//             }
//         });
//         return http.build();
//     }

//     @Bean
//     public InMemoryUserDetailsManager userDetailsService() {
//         PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    
//         UserDetails user = User
//                 .withUsername("user")
//                 .password(encoder.encode("password"))
//                 .roles("")
//                 .build();
    
//         UserDetails admin = User
//                 .withUsername("admin")
//                 .password(encoder.encode("12345678"))
//                 .roles("ADMIN")
//                 .build();
    
//         return new InMemoryUserDetailsManager(List.of(user, admin));
//     }
// }
