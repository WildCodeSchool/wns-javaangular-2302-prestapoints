// package fr.model;

// import org.springframework.stereotype.Component;

// import fr.entity.User;

// @Component
// public class UserConnected {
//     private static UserConnected instance;
//     private User userConnected;

//     private UserConnected() {
//         // Constructeur privé pour empêcher l'instanciation et donc perdre le userCo
//     }

//     public static UserConnected getInstance() {
//         if (instance == null) {
//                     instance = new UserConnected();
//                 }
//         return instance;
//     }

//     public User getUserConnected() {
//         return this.userConnected;
//     }

//     public void setUserConnected(User userConnected) {
//         this.userConnected = userConnected;
//     }
// }
