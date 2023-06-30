package fr.fixture;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import fr.entity.Image;
import fr.entity.Prestation;
import fr.enums.TablesEnum;
import fr.exception.ExceptionJsonDetail;
import fr.repository.ImageRepository;
import fr.repository.PrestationRepository;

@Component
public class PrestationFixtures {

    @Autowired
    private Fixtures fixtures;

    @Autowired
    PrestationRepository prestationRepository;

    @Autowired
    ImageRepository imageRepository;


    public void prepareFixtures() throws ExceptionJsonDetail {

        String table = TablesEnum.PRESTATION.getTableName();
        Faker faker = new Faker();
        Prestation prestation = new Prestation();

        if (fixtures.isDatatableExistAndDelete(table)){
                        
            Integer numberOfLigne = 20;

            for (Integer i = 1; i < numberOfLigne; i++) {

                prestation.setId(i);
                prestation.setTitle(faker.lorem().sentence(faker.number().numberBetween(1, 6)));

                LocalDateTime dateStart = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                long timestamp = dateStart.toInstant(ZoneOffset.UTC).toEpochMilli();
                Timestamp timestampSql = new Timestamp(timestamp);
                prestation.setDateStart(timestampSql);

                // Générer une durée aléatoire au format "hh:mm"
                String randomDuration = String.format("%02d:00", faker.number().numberBetween(0, 23));
                // Convertir la durée en LocalTime
                LocalTime duration = LocalTime.parse(randomDuration);
                Long milliseconds = Duration.between(LocalTime.MIN, duration).toMillis();
                prestation.setDuration(milliseconds);

                // Créer une instance de Duration à partir de la durée
                Duration duree = Duration.ofHours(duration.getHour()).plusMinutes(duration.getMinute());

                // Ajouter la durée à la date de début pour obtenir la date de fin
                LocalDateTime dateEnd = dateStart.plus(duree);
                timestamp = dateEnd.toInstant(ZoneOffset.UTC).toEpochMilli();
                timestampSql = new Timestamp(timestamp);
                prestation.setDateEnd(timestampSql);
                
                prestation.setAddPoint(faker.number().numberBetween(100, 500));
                prestation.setState(String.valueOf(faker.number().numberBetween(1, 3)));
                prestation.setDescription(faker.lorem().sentence(faker.number().numberBetween(1, 6)));
                prestation.setMaxUser(faker.number().numberBetween(1,6));
                
                prestationRepository.save(prestation);
            }

        }
    } 
}
