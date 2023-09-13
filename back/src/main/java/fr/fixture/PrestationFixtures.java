package fr.fixture;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import fr.entity.Location;
import fr.entity.Prestation;
import fr.entity.Type;
import fr.enums.TablesEnum;
import fr.repository.LocationRepository;
import fr.repository.PrestationRepository;
import fr.repository.TypeRepository;

@Component
public class PrestationFixtures {

    @Autowired
    private Fixtures fixtures;
    @Autowired
    private PrestationRepository prestationRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private LocationRepository locationRepository;


    public void prepareFixtures() {
        
        String[] titles = { 
            "Apprenez à dresser votre chien avec notre expert canin",
            "Explorez votre créativité avec la poterie",
            "Apprenez à créer vos propres bijoux en perles",
            "Apprenez à construire vos propres meubles en bois",
            "Créez vos vêtements sur mesure avec notre styliste",
            "Apprenez à créer un jardin harmonieux",
            "Découvrez les saveurs des cuisines du monde",
            "Portrait", 
            "Forme toi à la tapisserie d’ameublement"
        };

        String[] littleDescriptions  = {
            "Découvrez les techniques de dressage de chiens et renforcez votre relation avec votre compagnon à quatre pattes.",
            "Découvrez l'art de la poterie et exprimez votre créativité en créant vos propres pièces uniques.",
            "Laissez libre cours à votre imagination et créez des bijoux uniques en apprenant les techniques de création de bijoux en perles.",
            "Découvrez l'art de la construction de meubles en bois et fabriquez votre propre pièce unique.",
            "Explorez le monde de la couture et apprenez à confectionner vos vêtements sur mesure.",
            "Découvrez l'art de l'aménagement paysager et apprenez à créer un jardin harmonieux qui reflète votre style personnel.",
            "Partez pour un voyage culinaire et découvrez les saveurs des cuisines du monde.",
            "Capturez votre essence unique à travers un portrait réalisé par notre talentueux artiste.",
            "Découvrez le monde de la tapisserie d'ameublement et donnez une seconde vie à votre mobilier avec Soazig"
        };

        String[] descriptions  = {
            "Apprenez à dresser votre chien avec notre expert canin",
            "Explorez votre créativité avec la poterie",
            "Apprenez à créer vos propres bijoux en perles",
            "Apprenez à construire vos propres meubles en bois",
            "Créez vos vêtements sur mesure avec notre styliste",
            "Apprenez à créer un jardin harmonieux",
            "Découvrez les saveurs des cuisines du monde",
            "Faites-vous portraituré par nos artistes",
            "Venez apprendre les rudiments de la tapisserie !"
        };

        String[] practicalsInformations  = {
            "Découvrez les techniques de dressage de chiens et renforcez votre relation avec votre compagnon à quatre pattes.",
            "Découvrez l'art de la poterie et exprimez votre créativité en créant vos propres pièces uniques.",
            "Laissez libre cours à votre imagination et créez des bijoux uniques en apprenant les techniques de création de bijoux en perles.",
            "Découvrez l'art de la construction de meubles en bois et fabriquez votre propre pièce unique.",
            "Explorez le monde de la couture et apprenez à confectionner vos vêtements sur mesure.",
            "Découvrez l'art de l'aménagement paysager et apprenez à créer un jardin harmonieux qui reflète votre style personnel.",
            "Partez pour un voyage culinaire et découvrez les saveurs des cuisines du monde.",
            "Capturez votre essence unique à travers un portrait réalisé par notre talentueux artiste.",
            "Découvrez le monde de la tapisserie d'ameublement et donnez une seconde vie à votre mobilier avec Soazig."
        };

        String[] languages  = {
            "Cet atelier est animé en français et anglais.",
            "Cet atelier est animé en français.",
            "Cet atelier est animé en français.",
            "Cet atelier est animé en français et anglais.",
            "Cet atelier est animé en français.",
            "Cet atelier est animé en français et anglais.",
            "Cet atelier est animé en français.",
            "Cet atelier est animé en français.",
            "Cet atelier est animé en français."
        };

        String[] personalsInfos  = {
            "Notre expert canin possède une vaste expérience dans le dressage de chiens et est passionné par leur comportement et leur bien-être. Il vous fournira des conseils personnalisés pour gérer les défis spécifiques de votre chien et vous aidera à établir une communication efficace.",
            "Notre potier expérimenté est spécialisé dans la poterie artisanale et aime partager son savoir-faire avec les participants. Il vous montrera différentes techniques pour créer des bols, des vases, des assiettes et d'autres objets en argile. Vous pourrez également expérimenter avec les émaux et les finitions pour donner vie à vos créations.",
            "Notre expert en bijouterie possède une vaste expérience dans la création de bijoux et est passionné par les perles et les matériaux précieux. Il vous enseignera les techniques de base pour créer vos propres bijoux en perles, ainsi que des conseils sur la sélection des matériaux et des combinaisons de couleurs.",
            "Notre ébéniste expérimenté vous guidera dans la construction de meubles en bois de qualité. Vous apprendrez les techniques de base de la menuiserie, la sélection du bois et les finitions pour créer des meubles durables et esthétiques.",
            "Notre styliste de mode professionnelle vous aidera à créer des vêtements sur mesure qui correspondent à votre style et à vos préférences. Elle vous conseillera sur le choix des tissus, les mesures et les ajustements, et vous guidera tout au long du processus de création.",
            "Notre expert en jardinage paysager vous aidera à concevoir et à créer un jardin harmonieux qui correspond à vos goûts et à votre environnement. Il vous donnera des conseils sur la sélection des plantes, l'aménagement de l'espace et l'entretien du jardin.",
            "Nos chefs cuisiniers expérimentés vous feront découvrir les saveurs exquises des cuisines du monde. Vous apprendrez des recettes authentiques, des techniques de préparation et des astuces culinaires pour créer des plats délicieux.",
            "Nos artistes talentueux captureront votre portrait de manière unique et artistique. Vous pourrez choisir parmi différentes techniques et styles artistiques pour obtenir un portrait qui vous ressemble.",
            "Soazig est tapissière d'ameublement. Elle adore le mobilier et les étoffes, et plus précisément redonner vie aux vieux fauteuils ! Elle effectue des réfections traditionnelles ou contemporaines de sièges, fauteuils, canapés, méridiennes, chaises, têtes de lit, tabourets... À travers la diversité des matières et des couleurs, elle se fera un plaisir de vous parler de son parcours et de vous donner des conseils techniques. Soazig vous accueillera avec plaisir et vous transmettra son savoir au sein d'une belle boutique-atelier partagée située en plein centre-ville !"
        };

        String[] durations  = {
            "02:00",
            "02:30",
            "00:30",
            "01:00",
            "03:00",
            "06:00",
            "10:00",
            "11:00",
            "05:30"
        };

        String table = TablesEnum.PRESTATION.getTableName();
        Faker faker = new Faker();
        Prestation prestation = new Prestation();

        if (fixtures.isDatatableExistAndDelete(table)){            

            for (int i = 0; i < titles.length; i++) {
                prestation.setId(i+1);
                prestation.setTitle(titles[i]);

                LocalDateTime dateStart = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                long timestamp = dateStart.toInstant(ZoneOffset.UTC).toEpochMilli();
                Timestamp timestampSql = new Timestamp(timestamp);
                prestation.setDateStart(timestampSql);

                // Générer une durée aléatoire au format "hh:mm"
                String randomDuration = durations[i];
                // Convertir la durée en LocalTime
                LocalTime duration = LocalTime.parse(randomDuration);
                Long milliseconds = Duration.between(LocalTime.MIN, duration).toMillis();
                prestation.setDuration(milliseconds);
        
                prestation.setAddPoint(300);
                prestation.setState("EN_COURS");
                prestation.setDescription(descriptions[i]);
                prestation.setMaxUser(faker.number().numberBetween(1,10));
                
                Type type = typeRepository.getReferenceById(i+1);
                prestation.setType(type);

                Location location = locationRepository.getReferenceById(i+1);
                prestation.setLocation(location);

                prestation.setLanguage(languages[i]);
                prestation.setLittleDescription(littleDescriptions[i]);
                prestation.setPersonalInfos(personalsInfos[i]);
                prestation.setPlaceAvailable(faker.number().numberBetween(1,prestation.getMaxUser()));
                prestation.setPracticalInformation(practicalsInformations[i]);
        
                prestationRepository.save(prestation);
            
            }
        }
    } 

}
