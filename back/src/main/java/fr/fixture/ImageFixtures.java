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
    private static final String IMAGE_PATH = "src/main/resources/imageFixtures/";

    public void prepareFixtures() {
	    String table = TablesEnum.IMAGE.getTableName();
        int numberOfPrestations = prestationRepository.findAll().size();

        if (fixtures.isDatatableExistAndDelete(table)){
        
            for (Integer i = 1; i <= numberOfPrestations; i++) {
                try {
                    byte[] imageData = loadImageData(IMAGE_PATH + i.toString()+".jpg");
                    Image image = new Image(imageData);
                    image.setId(i);
                    Prestation prestation = new Prestation();
                    prestation = prestationRepository.getReferenceById(i);
                    image.setPrestation(prestation);
                    imageRepository.save(image);
                } catch (Exception e) {
                        e.printStackTrace();
                } 

            }
                
            for (Integer i = 1; i <= numberOfPrestations; i++) {
                try {
                    Integer j = i+10;
                    byte[] imageData = loadImageData(IMAGE_PATH + j.toString()+".jpg");
                    Image image = new Image(imageData);
                    image.setId(i+10);
                    Prestation prestation = new Prestation();
                    prestation = prestationRepository.getReferenceById(i);
                    image.setPrestation(prestation);
                    imageRepository.save(image);
                } catch (Exception e) {
                    e.printStackTrace();
                } 

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