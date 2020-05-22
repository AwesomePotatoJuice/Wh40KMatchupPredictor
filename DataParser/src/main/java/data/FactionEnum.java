package data;

public enum FactionEnum {
    ADEPTUS_MECHANICUS("http://wahapedia.ru/wh40k8ed/factions/adeptus-mechanicus/datasheets.html"),
    ASTRA_MILLITARUM("null"),
    ADEPTUS_ASTARTES("http://wahapedia.ru/wh40k8ed/factions/space-marines/datasheets.html"),
    BLOOD_ANGELS("http://wahapedia.ru/wh40k8ed/factions/blood-angels/datasheets.html"),
    ADEPTUS_SORORITAS("http://wahapedia.ru/wh40k8ed/factions/adepta-sororitas/datasheets.html");

    String value;
    FactionEnum(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
