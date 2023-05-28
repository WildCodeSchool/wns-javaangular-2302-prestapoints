package fr.fixture;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import fr.entity.Location;
import fr.entity.Prestation;
import fr.enums.TablesEnum;
import fr.repository.LocationRepository;
import fr.repository.PrestationRepository;
import fr.repository.TypeRepository;

@Component
public class LocationFixtures {

    @Autowired
    private Fixtures fixtures;

    @Autowired
    LocationRepository locationRepository;

    public void prepareFixtures() {

        String table = TablesEnum.LOCATION.getTableName();
        Faker faker = new Faker();
        Location location = new Location();

        if (fixtures.isDatatableExistAndDelete(table)){
                        
            Integer numberOfLigne = 50;

            for (int i = 0; i < numberOfLigne; i++) {


                location.setId(i);
                location.setPostalCode(faker.address().countryCode());
                location.setCity(faker.address().cityName());

                locationRepository.save(location);
            }
        }
    } 
}
