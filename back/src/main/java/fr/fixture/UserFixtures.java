package fr.fixture;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import fr.enums.TablesEnum;

@Component
public class UserFixtures {

        @Autowired
        private Fixtures fixtures;

        public void prepareFixtures() {
                String table = TablesEnum.USER.getTableName();
                Integer numberOfLigne = 10;

                List<String> columns = Arrays.asList(
                        "id",
                        "firstname",
                        "lastname",
                        "provider_id"
                        );
                        
                Faker faker = new Faker();
                Supplier<?>[] suppliers = new Supplier[] {
                                () -> fixtures.id(),
                                () -> faker.name().firstName(),
                                () -> faker.name().lastName(),
                                () -> fixtures.otherRelationId()
                };

                fixtures.launchFixtures(table, numberOfLigne, columns, suppliers);
        }
}
