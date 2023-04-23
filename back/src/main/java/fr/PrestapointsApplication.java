package fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fixture.PrestationFixtures;
import fr.fixture.ProviderFixtures;
import fr.fixture.UserFixtures;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PrestapointsApplication {

	@Autowired
	private UserFixtures userFixtures;
	@Autowired
	private PrestationFixtures prestationFixtures;
	@Autowired
	private ProviderFixtures providerFixtures;

	private boolean loadFixtures = true;

	@PostConstruct
	public void init() {
		if (this.loadFixtures) {
			//Faire attention à l'ordre des dépendences !
			userFixtures.prepareFixtures();
			providerFixtures.prepareFixtures(); // depends on user
			prestationFixtures.prepareFixtures(); // depends on user and provider
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(PrestapointsApplication.class, args);
	}

}
