package fr.enums;

public enum TablesEnum {

    USER("'user'"),
    PROVIDER("'provider'"),
    ROLE("'role'"),
    PARTNER("'partner'"),
    CATEGORY("'category'"),
    TYPE("'type'"),
    LOCATION("'location'"),
    ADDRESS_LOCATION("'address_location'"),
    EVALUATION("'evaluation'"),
    ROLE_USER("'role_user'"),
    FAVORITE_PROVIDER("'favorite_provider'"),
    REGISTRATION("'registration'"),
    FAVORITE_PRESTATION("'favorite_prestation'"),
    PRESTATION("'prestation'");

    private String tableName;

    TablesEnum(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

}
