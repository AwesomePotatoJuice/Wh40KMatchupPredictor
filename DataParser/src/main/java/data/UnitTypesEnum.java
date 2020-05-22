package data;

public enum UnitTypesEnum {
    HQ("HQ"),
    TROOPS("Troops"),
    ELITES("Elites"),
    FAST_ATTACK("Fast-Attack"),
    HEAVY_SUPPORT("Heavy-Support"),
    LORDS_OF_WAR("Lords-of-War"),
    UNALIGNED("Unaligned"),
    DEFAULT_UNREGISTERED("Default-Unregistered");

    String value;

    UnitTypesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
