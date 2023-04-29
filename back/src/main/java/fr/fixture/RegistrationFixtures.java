package fr.fixture;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import fr.enums.TablesEnum;

@Component
public class RegistrationFixtures {

        @Autowired
        private Fixtures fixtures;

        public void prepareFixtures() {
                String table = TablesEnum.REGISTRATION.getTableName();
                Integer numberOfLigne = 50;

                List<String> columns = Arrays.asList(
                                "id",
                                "comment",
                                "evaluation",
                                "prestation_id",
                                "user_id");

                Faker faker = new Faker();
                Supplier<?>[] suppliers = new Supplier[] {
                                () -> fixtures.id(),
                                () -> faker.lorem().sentence(faker.number().numberBetween(10, 25)),
                                () -> faker.number().numberBetween(0, 5),
                                () -> faker.number().numberBetween(1, 50),
                                () -> faker.number().numberBetween(1, 10)
                };

                fixtures.launchFixtures(table, numberOfLigne, columns, suppliers);
        }
}
