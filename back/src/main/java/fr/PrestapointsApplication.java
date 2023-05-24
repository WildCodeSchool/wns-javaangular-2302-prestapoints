package fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fixture.PrestationFixtures;
import fr.fixture.UserFixtures;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PrestapointsApplication {

	@Autowired
	private UserFixtures userFixtures;
	@Autowired
	private PrestationFixtures prestationFixtures;


	/* STOP FIXTURES = false   # START FIXTURES = true */ 
	private boolean loadFixtures = true;

	@PostConstruct
	public void init() {
		if (this.loadFixtures) {
			//Faire attention à l'ordre des dépendences !
			userFixtures.prepareFixtures();
			prestationFixtures.prepareFixtures(); // depends on user 
			}
	}

	public static void main(String[] args) {
		SpringApplication.run(PrestapointsApplication.class, args);
	}

}
