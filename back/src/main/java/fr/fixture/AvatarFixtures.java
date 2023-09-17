package fr.fixture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.entity.Avatar;
import fr.entity.User;
import fr.enums.TablesEnum;
import fr.repository.AvatarRepository;
import fr.repository.UserRepository;
import java.nio.file.Path;
import java.nio.file.Paths;



@Component
public class AvatarFixtures {

    @Autowired
    private Fixtures fixtures;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String IMAGE_PATH1 = "src/main/resources/imageFixtures/avatar.jpg";

    public void prepareFixtures(){
        String table = TablesEnum.AVATAR.getTableName();
        Avatar avatar = new Avatar();

        if (fixtures.isDatatableExistAndDelete(table)){
            for (int i = 1; i <= 10; i++) { 
                try {
                    avatar.setId(i);
                    byte[] imageAvatar = loadImageData(IMAGE_PATH1);
                    avatar.setType("jpg");
                    avatar.setData(imageAvatar);
                    User user = userRepository.findById(i).orElse(null);
                    avatar.setUser(user);
                    avatarRepository.save(avatar);

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
