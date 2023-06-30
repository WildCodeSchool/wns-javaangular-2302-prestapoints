package fr.enums;

public enum MessageApiEnum {
    
    EMAIL_EXISTING("L'email existe déjà !"),
    EMAIL_NOT_VALID("L'email n'est pas conforme."),
    REGISTRATION_FULL("Désolé, il n'y a plus de place disponible pour cette prestation"),
    REGISTRATION_ALREADY("Vous êtes déjà inscrit à cette prestation");
    
    
    private String message;

    MessageApiEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}