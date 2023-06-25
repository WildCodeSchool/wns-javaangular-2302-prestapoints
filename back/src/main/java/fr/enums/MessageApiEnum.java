package fr.enums;

public enum MessageApiEnum {
    
    EMAIL_EXISTING("L'email existe déjà !"),
    EMAIL_NOT_VALID("L'email toto n'est pas conforme.");
    
    private String message;

    MessageApiEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}