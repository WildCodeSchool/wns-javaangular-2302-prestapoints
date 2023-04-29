package fr.fixture;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import fr.enums.TablesEnum;

@Component
public class PrestationFixtures {

    @Autowired
    private Fixtures fixtures;

    public void prepareFixtures() {
        String table = TablesEnum.PRESTATION.getTableName();
        Integer numberOfLigne = 50;

        List<String> columns = Arrays.asList(
                "id",
                "title",
                "duration",
                "add_point",
                "creator_id");

        Faker faker = new Faker();
        Supplier<?>[] suppliers = new Supplier[] {
                () -> fixtures.id(),
                () -> faker.lorem().sentence(faker.number().numberBetween(1, 10)),
                () -> faker.number().numberBetween(1, 100),
                () -> faker.number().numberBetween(10, 500),
                () -> faker.number().numberBetween(1, 10)
        };

        fixtures.launchFixtures(table, numberOfLigne, columns, suppliers);
    }
}
