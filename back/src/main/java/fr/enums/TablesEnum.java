package fr.enums;

public enum TablesEnum {

    USER("'user'"),
    USERREGISTER("'user_register'"),
    ROLE("'role'"),
    PARTNER("'partner'"),
    CATEGORY("'category'"),
    TYPE("'type'"),
    LOCATION("'location'"),
    ADDRESS_LOCATION("'address_location'"),
    ROLE_USER("'role_user'"),
    PRESTATION("'prestation'"),
    FAVORY("'favory'"),
    CREATION("'creation'");

    private String tableName;

    TablesEnum(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

}
