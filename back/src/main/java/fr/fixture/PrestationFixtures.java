package fr.fixture;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import fr.entity.Prestation;
import fr.enums.TablesEnum;
import fr.repository.LocationRepository;
import fr.repository.PrestationRepository;
import fr.repository.TypeRepository;

@Component
public class PrestationFixtures {

    @Autowired
    private Fixtures fixtures;
    @Autowired
    private PrestationRepository prestationRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private LocationRepository locationRepository;

    public void prepareFixtures() {

        String table = TablesEnum.PRESTATION.getTableName();
        Faker faker = new Faker();
        Prestation prestation = new Prestation();

        if (fixtures.isDatatableExistAndDelete(table)){
                        
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Integer numberOfLigne = 20;

            for (int i = 0; i < numberOfLigne; i++) {
                prestation.setId(i);
                prestation.setTitle(faker.lorem().sentence(faker.number().numberBetween(1, 6)));
                prestation.setDuration(String.valueOf(faker.number().numberBetween(1, 100)));
                prestation.setAddPoint(String.valueOf(faker.number().numberBetween(100, 500)));
                prestation.setDateEnd(String.valueOf(sdf.format(faker.date().future(30,TimeUnit.DAYS))));
                prestation.setDateStart(String.valueOf(sdf.format(faker.date().future(30,TimeUnit.DAYS))));
                prestation.setState(String.valueOf(faker.number().numberBetween(1, 3)));
                prestation.setDescription(faker.lorem().sentence(faker.number().numberBetween(1, 6)));
                prestation.setMaxUser(String.valueOf(faker.number().numberBetween(1,6)));
                prestation.setImage(fixtures.imageFakerRandom(200, 300));
                prestation.setType(typeRepository.getReferenceById(faker.number().numberBetween(1, 5)));
                prestation.setLocation(locationRepository.getReferenceById(faker.number().numberBetween(1, 50)));

                prestationRepository.save(prestation);
            }
        }
    } 
}
