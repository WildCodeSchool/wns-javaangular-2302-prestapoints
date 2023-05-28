package fr.fixture;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import fr.entity.Category;
import fr.entity.Type;
import fr.enums.TablesEnum;
import fr.repository.CategoryRepository;
import fr.repository.TypeRepository;

@Component
public class TypeFixtures {

    @Autowired
    private Fixtures fixtures;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    CategoryRepository categoryRepository;


    public void prepareFixtures() {

        String table = TablesEnum.TYPE.getTableName();
        Faker faker = new Faker();
        Type type = new Type();

        if (fixtures.isDatatableExistAndDelete(table)){
                        
            Integer numberOfLigne = 5;

            for (int i = 1; i <= numberOfLigne; i++) {
                type.setId(i);
                type.setName(faker.lorem().word());
                type.setCategory(categoryRepository.getReferenceById(faker.number().numberBetween(1, 5)));

                typeRepository.save(type);
            }
        }
    } 
}
