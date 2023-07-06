package fr.fixture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.entity.Image;
import fr.entity.Prestation;
import fr.enums.TablesEnum;
import fr.repository.ImageRepository;
import fr.repository.PrestationRepository;


@Component
public class ImageFixtures {

    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private Fixtures fixtures;
    private static final String IMAGE_PATH1 = "src/main/resources/imageFixtures/fixtureImage.jpg";
    private static final String IMAGE_PATH2 = "src/main/resources/imageFixtures/fixtureImage2.jpg";
    public void prepareFixtures() {
	    String table = TablesEnum.IMAGE.getTableName();
        

        if (fixtures.isDatatableExistAndDelete(table)){
            try {
                byte[] imageData = loadImageData(IMAGE_PATH1);
                for (int i = 1; i < 11; i++) {
                    Image image = new Image(imageData);
                    image.setId(i);
                    Prestation prestation = new Prestation();
                    prestation = prestationRepository.getReferenceById(i);
                    image.setPrestation(prestation);
                    imageRepository.save(image);
                }

                imageData = loadImageData(IMAGE_PATH2);
                for (int i = 1; i < 11; i++) {
                    Image image = new Image(imageData);
                    image.setId(i+10);
                    Prestation prestation = new Prestation();
                    prestation = prestationRepository.getReferenceById(i);
                    image.setPrestation(prestation);
                    imageRepository.save(image);
                }
                

            } catch (Exception e) {
                e.printStackTrace();
            } 

        }
    }

    private static byte[] loadImageData(String imagePath) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        imagePath = currentDirectory + File.separator + imagePath;;
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }

}