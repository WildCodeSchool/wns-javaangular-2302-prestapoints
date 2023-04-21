package fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.github.javafaker.Faker;

@SpringBootApplication
public class PrestapointsApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(PrestapointsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userFixture(10);
	}


	private void userFixture(int number) {
		if (jdbcTemplate.queryForObject(
			"SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user'",
				Integer.class) > 0) {

			// La table existe, on la vide
			jdbcTemplate.update("DELETE FROM user");

			// On ajoute des données fictives
			Faker faker = new Faker();
			for (int id = 1; id <= number; id++) {
				String firstname = faker.name().firstName();
				String lastname = faker.name().lastName();
			
				//on insère dans la BDD
				jdbcTemplate.update(
					"INSERT INTO user (id, firstname, lastname) VALUES (?, ?, ?)",
						id, firstname, lastname);
			}
		} else {
			// La table n'existe pas, on renvoie une erreur
			throw new RuntimeException("La table 'user' n'existe pas.");
		}
	}
}
