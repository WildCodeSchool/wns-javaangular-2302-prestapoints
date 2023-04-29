package fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fixture.PrestationFixtures;
import fr.fixture.UserFixtures;
import fr.fixture.RegistrationFixtures;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PrestapointsApplication {

	@Autowired
	private UserFixtures userFixtures;
	@Autowired
	private PrestationFixtures prestationFixtures;
	@Autowired
	private RegistrationFixtures userPrestationFixtures;


	/* STOP FIXTURES = false   # START FIXTURES = true */ 
	private boolean loadFixtures = false;

	@PostConstruct
	public void init() {
		if (this.loadFixtures) {
			//Faire attention à l'ordre des dépendences !
			userFixtures.prepareFixtures();
			prestationFixtures.prepareFixtures(); // depends on user 
			userPrestationFixtures.prepareFixtures();// depends on user and prestation
			}
	}

	public static void main(String[] args) {
		SpringApplication.run(PrestapointsApplication.class, args);
	}

}
