package fr.fixture;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fr.entity.Category;
import fr.enums.TablesEnum;
import fr.repository.CategoryRepository;
import fr.repository.TypeRepository;

@Component
public class CategoryFixtures {

    private List<String> categories = Arrays.asList(
            "Ameublement",
            "Animaux",
            "Artisanat",
            "Bijoux",
            "Bricolage",
            "CréationMeubles",
            "DressageChiens",
            "Poterie",
            "Peinture",
            "Couture",
            "Jardinage",
            "Cuisine",
            "Photographie",
            "Danse",
            "Théâtre",
            "Yoga",
            "Dessin",
            "Musique",
            "Fitness");

    @Autowired
    private Fixtures fixtures;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TypeRepository typeRepository;

    public void prepareFixtures() {
        String table = TablesEnum.CATEGORY.getTableName();

        Category category = new Category();

        if (fixtures.isDatatableExistAndDelete(table)) {

            Integer numberOfLigne = this.categories.size();

            for (int i = 1; i <= numberOfLigne; i++) {
                category.setId(i);
                category.setName(categories.get(i));

                categoryRepository.save(category);
            }
        }
    }
}
