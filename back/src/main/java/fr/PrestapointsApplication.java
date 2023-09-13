package fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import fr.fixture.CategoryFixtures;
import fr.fixture.LocationFixtures;
import fr.fixture.PrestationFixtures;
import fr.fixture.RoleFixtures;
import fr.fixture.RegistrationFixtures;
import fr.fixture.TypeFixtures;
import fr.fixture.UserFixtures;
import fr.fixture.ImageFixtures;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PrestapointsApplication {

	@Autowired
	private UserFixtures userFixtures;
	@Autowired
	private PrestationFixtures prestationFixtures;
	@Autowired
	private RegistrationFixtures registrationFixtures;
	@Autowired
	private CategoryFixtures categoryFixtures;
	@Autowired
	private TypeFixtures typeFixtures;
	@Autowired
	private LocationFixtures locationFixtures;
	@Autowired
	private RoleFixtures roleFixtures;
    @Autowired
	private ImageFixtures imageFixtures;

	/* STOP FIXTURES = false # START FIXTURES = true */
	private boolean loadFixtures = false;

	@PostConstruct
	@Profile("!test")
	public void init() {
		if (this.loadFixtures) {
<<<<<<< HEAD
			// Faire attention à l'ordre des dépendences !
			userFixtures.prepareFixtures(); // depends on
			categoryFixtures.prepareFixtures(); // depends on
			typeFixtures.prepareFixtures(); // depends on category
			locationFixtures.prepareFixtures();
			prestationFixtures.prepareFixtures(); // depends on user, category, location
		}
=======
			//Faire attention à l'ordre des dépendences !
			roleFixtures.prepareFixtures();
			userFixtures.prepareFixtures(); //depends on roles
			categoryFixtures.prepareFixtures(); //depends on 
			typeFixtures.prepareFixtures(); //depends on category
			locationFixtures.prepareFixtures();
			prestationFixtures.prepareFixtures(); // depends on user, category, location
			registrationFixtures.prepareFixtures();// depends on prestation
            imageFixtures.prepareFixtures();
			}
>>>>>>> 28047de27167f399e131ee2fb8f4e2eb61d87da1
	}

	public static void main(String[] args) {
		SpringApplication.run(PrestapointsApplication.class, args);
	}

}
