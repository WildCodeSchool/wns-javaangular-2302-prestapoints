package fr.fixture;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import fr.entity.Role;
import fr.entity.User;
import fr.enums.TablesEnum;
import fr.repository.RoleRepository;
import fr.repository.UserRepository;

@Component
public class UserFixtures {

    @Autowired
    private Fixtures fixtures;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void prepareFixtures() {
        String table = TablesEnum.USER.getTableName();
        String tableJoin = TablesEnum.USER_ROLE.getTableName();
        Integer numberOfLigne = 10;
        Faker faker = new Faker();

        Role roleAdmin = roleRepository.getReferenceById(1);
        Role roleUser = roleRepository.getReferenceById(2);

        if (fixtures.isDatatableExistAndDelete(table)) {
            fixtures.isDatatableExistAndDelete(tableJoin);

            for (Integer i = 0; i < numberOfLigne; i++) {
                List<Role> roles = new ArrayList();
                User user = new User();
                user.setId(i);
                user.setEmail("user" + i.toString() + "@test.com");
                user.setFirstname(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                user.setPhone("012345" + faker.phoneNumber().subscriberNumber());
                user.setPassword(passwordEncoder.encode("t123456789"));
                roles.add(roleUser);
                user.setRoles(roles);
                LocalDate today = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalTime currentTime = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalTime();

                LocalDateTime currentDateTime = LocalDateTime.of(today, currentTime);
                user.setCreation(currentDateTime);

                userRepository.save(user);
            }

            List<Role> roles = new ArrayList();
            User user = new User();

            user.setId(numberOfLigne + 1);
            user.setEmail("admin@test.com");
            user.setFirstname(faker.name().firstName());
            user.setLastname(faker.name().lastName());
            user.setPhone("012345" + faker.phoneNumber().subscriberNumber());
            user.setPassword(passwordEncoder.encode("t123456789"));
            roles.add(roleUser);
            roles.add(roleAdmin);
            user.setRoles(roles);

            LocalDate today = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime currentTime = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

            LocalDateTime currentDateTime = LocalDateTime.of(today, currentTime);

            user.setCreation(currentDateTime);

            userRepository.save(user);

        }
    }
}
