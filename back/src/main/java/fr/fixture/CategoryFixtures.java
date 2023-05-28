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
public class CategoryFixtures {

    @Autowired
    private Fixtures fixtures;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TypeRepository typeRepository;


    public void prepareFixtures() {

        String table = TablesEnum.CATEGORY.getTableName();
        Faker faker = new Faker();
        Category category = new Category();

        if (fixtures.isDatatableExistAndDelete(table)){
                        
            Integer numberOfLigne = 5;

            for (int i = 1; i <= numberOfLigne; i++) {
                category.setId(i);
                category.setName(faker.lorem().word());

                categoryRepository.save(category);
            }
        }
    } 
}
