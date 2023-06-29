package fr.fixture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                        
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Integer numberOfLigne = 20;

            for (Integer i = 1; i < numberOfLigne; i++) {

                prestation.setId(i);
                prestation.setTitle(faker.lorem().sentence(faker.number().numberBetween(1, 6)));
                prestation.setDuration(String.valueOf(faker.number().numberBetween(1, 100)));
                prestation.setAddPoint(String.valueOf(faker.number().numberBetween(100, 500)));
                prestation.setDateEnd(String.valueOf(sdf.format(faker.date().future(30,TimeUnit.DAYS))));
                prestation.setDateStart(String.valueOf(sdf.format(faker.date().future(30,TimeUnit.DAYS))));
                prestation.setState(String.valueOf(faker.number().numberBetween(1, 3)));
                prestation.setDescription(faker.lorem().sentence(faker.number().numberBetween(1, 6)));
                prestation.setMaxUser(String.valueOf(faker.number().numberBetween(1,6)));
                
                prestationRepository.save(prestation);
 
                try {
                    URL url = new URL(fixtures.imageFakerRandom(200, 300));
                    InputStream inputStream = url.openStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
        
                    byte[] imageBytes = outputStream.toByteArray();
                    Image image = new Image(imageBytes);
                    image.setData(imageBytes);
                    image.setId(i);
                    image.setPrestation(prestation);
                    imageRepository.save(image);
        
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    } 

    public void saveImageFromUrl() {
        try {
            URL url = new URL(fixtures.imageFakerRandom(200, 300));
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            Image image = new Image(imageBytes);
            imageRepository.save(image);

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
