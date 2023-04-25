package fr.fixture;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import fr.enums.TablesEnum;

@Component
public class RegistrationsFixtures {
    
    @Autowired
    private Fixtures fixtures;

    public void prepareFixtures() {
        String table = TablesEnum.REGISTRATION.getTableName();
        Integer numberOfLigne = 50;

        List<String> columns = Arrays.asList(
                "user_id",
                "prestation_id"
                );
                
        Faker faker = new Faker();
        Supplier<?>[] suppliers = new Supplier[] {
                        () -> faker.number().numberBetween(1, 10),
                        () -> faker.number().numberBetween(1, 50)
        };

        fixtures.launchFixtures(table, numberOfLigne, columns, suppliers);
    }
}
