package fr;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
		Faker faker = new Faker();
		// NE JAMAIS METTRE L'ID
		// remplir les noms de colonnes MySQL
		List<String> userColumns = Arrays.asList(
				"firstname",
				"lastname");

		// remplir les fakers des colonnes précédentes
		Supplier<?>[] userSuppliers = new Supplier[] {
				() -> faker.name().firstName(),
				() -> faker.name().lastName()
		};

		// mettre le nom de la table entre double ET simple quote
		// mettre le nombre de ligne souhaitée
		fixtures("'user'", 10, userColumns, userSuppliers);
		/***********************************************/
		List<String> testColumns = Arrays.asList(
				"role",
				"avion",
				"date");

		Supplier<?>[] testSuppliers = new Supplier[] {
				() -> faker.name().prefix(),
				() -> faker.starTrek().character(),
				() -> faker.date().birthday()
		};
		fixtures("'test'", 100, testColumns, testSuppliers);

		/***********************************************/
	}
/********************************************************************************************/
	// DO NOT TOUCH
	private void fakingDB(int number, List<String> columns, String tableName, Supplier<?>[] suppliers) {
		int columnsNumber = columns.size();
		tableName = tableName.replace("'", "");
		String insert = "INSERT INTO " + tableName + " (id,";
		String values = " VALUES (?,";

		for (int i = 0; i < columnsNumber; i++) {
			if ((i + 1) == columnsNumber) {
				insert += columns.get(i) + ")";
				values += "?)";
			} else {
				insert += columns.get(i) + ",";
				values += "?,";
			}
		}
		for (int id = 1; id <= number; id++) {
			Object[] params = new Object[suppliers.length + 1];
			params[0] = id;
			for (int i = 0; i < suppliers.length; i++) {
				params[i + 1] = suppliers[i].get();
			}
			jdbcTemplate.update(insert + values, params);
		}
	}

	private void fixtures(String tableName, int number, List<String> columns, Supplier<?>[] suppliers) {
		if (isTableExist(tableName)) {
			// La table existe, on la vide
			deleteTable(tableName);
			// On ajoute des données fictives
			fakingDB(number, columns, tableName, suppliers);

		} else {
			// La table n'existe pas, on renvoie une erreur
			throw new RuntimeException("La table " + tableName + " n'existe pas.");
		}
	}

	private boolean isTableExist(String tableName) {

		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = " + tableName,
				Integer.class) > 0;
	}

	private void deleteTable(String tableName) {
		tableName = tableName.replace("'", "");
		jdbcTemplate.update("DELETE FROM " + tableName);
	}
}
