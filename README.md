####################################################################################################################
#                                                   FRANCAIS                                                       #
####################################################################################################################
#
# Démarrage
#

1- clone le repository
2- Avec le fichier create-database.sql => créer la base de données et l'utilisateur associé (veillez à bien garder votre BDD connectée)
3- avec le shell, se déplacer à la racine du dossier back et lancer la commande 
        ./mvnw clean spring-boot:run 
afin de vérifier que spring-boot fonctionne correctement ce sera la partie back de l'application
<!-- vous pouvez tester l'URL pour valider le bon fonctionnement avec votre navigateur préféré  : http://localhost:8080/  -->
<!-- //n'oubliez pas que le mot de passe pour accéder à la vue spring est donné dans votre CLI comme ci-dessous  -->
<!-- //Using generated security password: 96e70895-a12b-4dde-9db1-c594058a5b14 -->
4- lancer un nouveau terminal, se déplacer à la racine du dossier front, lancer angular avec la commande 
pour installer toutes les dépendences, 

        npm install 
puis
        npm start
afin de vérifier que ce dernier fonctionne correctement, ce sera la partie front de l'application
<!-- vous pouvez vérifier le résultat du backend avec postman ou tester l'URL : http://localhost:4200/  -->

vérifier également qu'une table user avec un champ <id> et un champ <name> se sont bien créés dans votre BDD.

#
# Variables d'environnement
#
 
1- Sous Windows -> Ouvrir les "propriétés systèmes", aller dans "Paramètres système avancés" puis cliquer sur "Variables d'environnement" 

1- Sous MAC -> Ouvrir les "préférences  systèmes", aller dans "Paramètres système avancés" puis cliquer sur "Variables d'environnement"

2- Dans Variables utilisateur, cliquer sur le bouton "Nouvelle..."

        Remplir les champs
        Nom de la variable :
        Valeur de la variable :

        cliquer sur OK

3- Recommencer pour créer toutes les variables 
        Nom de la variable : DATABASE_USERNAME
        Valeur de la variable :

        Nom de la variable : DATABASE_PASSWORD
        Valeur de la variable : 

        Nom de la variable : DATABASE_URL
        Valeur de la variable : 

        Nom de la variable : JWT_SECRET
        Valeur de la variable : 

        Nom de la variable : JWT_EXPIRATION
        Valeur de la variable : 

4- Dans le fichier application.properties, remplacer les valeurs par les nom des variables d'environnement sous la forme ${DATABASE_USERNAME} par exemple.


Il est possible de devoir redémarrer pour la prise en compte

#
# Architecture Angular
#

<core> et <shared> sont séparés afin de centraliser la sécurité dans <core>. <shared> rassemblera les composants moins sensibles qui peuvent être partagés. 
Les répertoires, nommés pour l'exemple <featureX>, contiennent chacun leurs propres composants, services, modules et routes.
Ainsi, ils ne chargent que ce qui est nécessaire à leur utilisation. Ils pourront charger les composants placés dans <shared> parce qu'il ne faut pas oublier
d'être DRY.

front/src/app/
├── core/
│   ├── services/
│   │   ├── authentication.service.ts
│   │   ├── logging.service.ts
│   │   └── ...
│   ├── guards/
│   │   ├── auth.guard.ts
│   │   └── ...
│   ├── interceptors/
│   │   ├── error.interceptor.ts
│   │   └── ...
│   └── <core.module.ts>
├─ pages  
│   ├── connection/
│   │   ├── signin/
│   │   │   ├── signin.component.ts
│   │   │   ├── signin.component.html
│   │   │   ├── signin.component.scss
│   │   │   └── signin.component.spec.ts
│   │   ├── login/
│   │   │   ├── login.component.ts
│   │   │   ├── login.component.html
│   │   │   ├── login.component.scss
│   │   │   └── login.component.spec.ts
│   │   ├── services/
│   │   │   ├── connection.service.ts
│   │   │   └── ...
│   │   │── connection.component.ts
│   │   │── connection.component.html
│   │   │── connection.component.scss
│   │   │── connection.component.spec.ts
│   │   │
│   │   ├── <connection.module.ts>
│   │   ├── <connection-routing.module.ts>
│   │   └── ...
│   ├── feature2/
│   │   ├── components/
│   │   │   ├── feature2.component.ts
│   │   │   ├── feature2.component.html
│   │   │   ├── feature2.component.scss
│   │   │   └── feature2.component.spec.ts
│   │   ├── services/
│   │   │   ├── feature2.service.ts
│   │   │   └── ...
│   │   ├── <feature2.module.ts>
│   │   ├── <feature2-routing.module.ts>
│   └── ...
├── shared/
│   ├── components/
│   │   ├── navbar/
│   │   │   ├── navbar.component.ts
│   │   │   ├── navbar.component.html
│   │   │   ├── navbar.component.scss
│   │   │   └── navbar.component.spec.ts
│   │   └── ...
│   ├── directives/
│   │   ├── highlight.directive.ts
│   │   └── ...
│   ├── pipes/
│   │   ├── uppercase.pipe.ts
│   │   └── ...
│   ├── models/
│   │   ├── user.ts
│   │   └── ...
│   ├── enums/
│   │   ├── status.enum.ts
│   │   └── ...
│   └── <shared.module.ts>
└── <app.module.ts>



# Architecture Spring

└─ back/src/main/java/fr
    ├─ config
    │   └─ SecurityConfig.java
    ├─ controller
    │   ├─ RestController1.java
    │   └─ RestController2.java
    ├─ repository
    │   ├─ Dao1.java
    │   └─ Dao2.java
    ├─ dto
    │   ├─ DTO1.java
    │   └─ DTO2.java
    ├─ enum
    │   ├─ enum1.java
    │   └─ enum2.java
    ├─ mapper
    │   ├─ Entity1Mapper.java
    │   ├─ Entity2Mapper.java
    │   ├─ DTO1Mapper.java
    │   └─ DTO2Mapper.java
    ├─ model
    │   ├─ Entity1.java
    │   └─ Entity2.java
    └─ service
        ├─ Service1.java
        └─ Service2.java

Les <controllers> sont chargés de la gestion des requêtes HTTP entrantes et sortantes. 
Les contrôleurs utilisent les <mappers> afin de faire la sérialisation et la désérialisation (convertion en JSON et inversement).
Les <DTOs> (Data Transfer Objects) représentent les objets métiers au format JSON (inutile d'envoyer un objet complet si seulement quelques propriétés sont utilisées).
Les <mappers> sont les classes qui effectuent le mapping entre les <models> et les <DTOs>. Ils seront utilisés par les <controllers> pour convertir les <models> en <DTO> et vice versa.
Les <repositories> effectuent des opérations sur la base de données pour les renvoyer au <service>.
<Config> contient les fichiers de configuration, dans notre application, pour paramétrer la sécurité dans un premier temps.

# Les routes

Côté back, la limitation se fait dans le fichier <config/WebSecurityConfig.java>, le requestMatchers permet de déterminer quel rôle à le droit d'accès.
Côté front, il faut mettre en place des <guards>. Attention, s'il y a plusieurs guards sur une route, l'utilisateur doit valider toutes les guards pour y accéder.

<Limiter au ROLE_ADMIN : admin/*> 
côté Angular le {u.id} deviendra "utilisateur", {p.id} deviendra "prestation", ...

admin/
admin/utilisateurs
admin/utilisateurs/creation
admin/utilisateurs/{u.id}/details
admin/utilisateurs/{u.id}/modification
admin/utilisateurs/{u.id}/suppression
admin/prestations/
admin/prestations/{p.id}/details
admin/prestations/{p.id}/modification
admin/prestations/{p.id}/suppression

<Limiter au ROLE_USER : prestapoints/*>
côté Angular le {u.id} deviendra "utilisateur", {p.id} deviendra "prestation", {i.id} deviendra "inscription",...

prestapoints/particulier/deconnexion
prestapoints/particulier/utilisateurs/{u.id}
prestapoints/particulier/prestations/creation

<!-- prestapoints/prestations -->
<!-- prestapoints/prestations/{p.id}/details -->
prestapoints/particulier/prestations/{p.id}/inscriptions   <!-- je ne sais pas si c'est pertinent de l'utiliser, mais utile pour bien comprendre la structure -->
prestapoints/particulier/prestations/{p.id}/inscriptions/creation
prestapoints/particulier/prestations/{p.id}/inscriptions/{i.id}/details
prestapoints/particulier/prestations/{p.id}/inscriptions/{i.id}/modification
prestapoints/particulier/prestations/{p.id}/inscriptions/{i.id}/suppression


<sans limitation : public/*>
prestapoints/
prestapoints/inscription
prestapoints/connexion
prestapoints/authentification
prestapoints/categories
prestapoints/email/verification
prestapoints/prestations
prestapoints/prestations/{p.id}/details

#
## Environnement de Tests ##
#
Pour le back : 
        Pour faire fonctionner le site avec les tests (pour les tests sur les controllers  par exemple) vous devez annoter votre classe de test avec:

        Pour les tests de la BDD :
        <@DataJpaTest> 
        <@TestPropertySource(locations = "classpath:application-test.properties")>

        Pour les autres tests
        <@WebMvcTest>  
        <@Import({ WebSecurityConfig.class, PasswordEncoderConfig.class, JwtUtils.class, JwtAuthenticationFilter.class, })>
        <@TestPropertySource(locations = "classpath:application-test.properties")>

        @TestPropertySource permet de mettre en place une configuration spécifique pour les tests, tel que pointer sur une BDD H2 (pour les tests sur la data) et activer un profil 'tests'.
        @Import permet de Mock la sécurité.

        Dans les 2 cas il faudra aussi @MockBean toutes les classes appelées lors des fixtures. Nous n'avons pas le choix, sinon cela casse.

        La construction d'un test est toujours dans le modèle des 3A (AAA) :
        //Arrange (je prépare les données)
        //Act (j'appelle la/les méthodes à tester)
        //Assert (je check)

        Pour lancer les tests, dans VSCode cliquer sur le triangle en face chaque test (lance le test individuellement), vous pouvez aussi les lancer en mode debug.
        Ainsi que lancer tous les tests de la classe (triangle en face la classe), etc...

Pour le front :
        Les tests se font avec Cypress, en e2e (endToEnd, bout-en-bout) et component.
        La ligne de commande afin de lancer cypress est <npm run cy:open>.

        La construction d'un test est plus proche du modèle GWT (Given-When-Then) avec :
        //Visit (Etant donnée que je visite la page web ??? )
        //Query (Quand je clique sur l'élément id=??? ...)
        //Interact (...et que j'interragie avec l'élément id=???)
        //Assert (Alors je dois avoir le résultat suivant) 

#        Le lien valable 30j (nous sommes le 24/06/2023) pour rejoindre la team Cypress : https://cloud.cypress.io/invitation/e537baa1-ab0c-49ba-8c43-1a6bff16e9e6


#
# Fixtures
#
Pour la préparations des fixtures, voici les consignes :
Dans le dossier <fixture> créez votre fichier de fixture selon l'entité concernée ou complétez celle existante.
Ensuite dans le fichier d'application PrestapointsApplication.java faites l'injection de dépendance et appeler votre méthode ce qui lancera vos fixtures à chaque démarrage.
Exemple : 

//fichier PrestapointsApplication.java
        @Autowired
	private UserFixtures userFixtures;
 
        ...
        userFixtures.prepareFixtures(); --> ajouter la méthode
        ...

//fichier fixture/PrestationFixtures.java
        public void prepareFixtures() {
                
                String table = TablesEnum.USER.getTableName();   --> choisissez la table concernée dans l'énum (il en ressortira la valeur "'user'" pour cet exemple)
                Integer numberOfLigne = 10;    --> ajoutez le nombre de ligne souhaitée, attention à bien prendre en compte les déclarations des autres fixtures s'il y a dépendence
                List<String> columns = Arrays.asList(
                                "id",
                                "firstname",     --> ajouter les colonnes concernées
                                "lastname");
                                
                Faker faker = new Faker();              
                Supplier<?>[] suppliers = new Supplier[] {
                                () -> fixtures.id(),
                                () -> faker.name().firstName(),  -->appliquer les fakers souhaitées
                                () -> faker.name().lastName()               
                };
                ...
        } 

#
# LES MESSAGES D'ALERTES (FRONT)
#
C'est un service, donc faites l'injection de dépendance de AlertService et appeler la méthode SetAlert() en passant les paramètres nécessaire grâce aux enums prévus.
ex :

  constructor(private alertService: AlertService) {}

        trucMachinChoseMethod() {
                ...

                this.alertService.setAlert(
                        AlertEnum.TYPE_SUCCESS,
                        AlertEnum.MESSAGE_LOGIN_SUCCESSED,
                        AlertEnum.TIME_MEDIUM);
                ...
        }


#
# LES ETAPES POUR GIT
#

En partant de la branche dev on tire sa branche de travail construite avec le n° de l'US et ce que fait l'US
Ex: 
        <US1-create_component_card>

La commande est 
        <git checkout -b US1-create_component_card>

Ensuite, une fois le travail terminé, on stage avec 
        <git status> 
afin de vérifier que tous les fichiers sont à ajouter, puis
        <git add .> ou <git add leNomDeMonFichier>
pour ajouter le fichier à l'étape supérieure, enfin il faut faire le commit avec
        <git commit -m "what i did">
Il peut y avoir multiples commits d'effectués sur une même branche. Ce qui est bien pour diviser son travail. 
A ce stade, rien n'est arrivé sur github, tout est en local mais vous pouvez changer de branche et travailler sur différentes tâches (sans oublier de add et commmit bien entendu).

Pour push sur github, il faut faire un 
        <git push origin US1-create_component_card>
A ce stade le travail est arrivé sur github, mais ce n'est pas encore fini, il faut le mettre en review pour faire une nouvelle Pull request
        <ATTENTION A BIEN FAIRE UNE PR US1-create_component_card  vers DEV>
par défaut github va proposer main.

Une fois la PR est validé par les reviewers, il faut merge la branche dans DEV. Une fois que c'est fait, github est en avance sur DEV par rapport à mon DEV en local.
Il faut donc Pull dev de origin vers le local.
        <git pull origin dev>

Un nouveau travail peut commencer avec une nouvelle en reprenant avec la commande   <git checkout -b maNouvelleBranche>

#---SWAGGER----#
Voici le lien pour accéder à SWAGGER
http://localhost:8080/swagger-ui/index.html#/

également lien du tuto
https://www.baeldung.com/openapi-jwt-authentication#3-global-configuration



####################################################################################################################
#                                                   ENGLISH                                                        #
####################################################################################################################

1- clone this repository
2- With create-database.sql file => create the database and add the mysql user
3- move to back folder with your shell and launch with 
        ./mvnw clean spring-boot:run 
command to verify spring-boot is working well, it will be the back part for the application
<!-- you can test the URL to confirm its work well in your favorite browser  : http://localhost:8080/  -->
<!-- //don't forget, the password to access to spring boot is given in the shell like this exemple  -->
<!-- //Using generated security password: 96e70895-a12b-4dde-9db1-c594058a5b14 -->
4- launch new shell, move to front folder, launch to install all dependencies,        
        npm install
and 
        npm start 
to see if angular is working well, it will be front part of the application
<!-- you can verify the results of the backend with postman or test the URL : http://localhost:4200/  -->

verify your DB to confirm the creation of <id> and <name>.


./mvnw -Dspring-boot.run.profiles=dev spring-boot:run