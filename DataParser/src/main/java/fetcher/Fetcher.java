package fetcher;

import data.FactionEnum;
import data.UnitTypesEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Fetcher {
    private Jsoup jsoup;
//    private static final String adeptusMechanicusUrl = "http://wahapedia.ru/wh40k8ed/factions/adeptus-mechanicus/datasheets.html";
//    private static final String bloodAngelsUrl = "http://wahapedia.ru/wh40k8ed/factions/blood-angels/datasheets.html";
    private Elements mainContent;

    public List<List<Element>> getWahapediaData(){
//        return  getWahapediaDataDocument(FactionEnum.ADEPTUS_MECHANICUS, 0);
//        return  getWahapediaDataDocument(FactionEnum.BLOOD_ANGELS, 0);
        return  getWahapediaDataDocument(FactionEnum.ADEPTUS_ASTARTES, 0);
    }

    private List<List<Element>> getWahapediaDataDocument(FactionEnum faction, int limit){
        Document doc = null;
        try {
            doc = Jsoup.connect(faction.getValue()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert doc != null;
        mainContent = doc.body().select("a[name]");

        List<List<Element>> result = new ArrayList<>();
        if(mainContent.size() > 0) {
            LinkedHashMap<UnitTypesEnum, Integer> indexesUnitTypes = getIndexesUnitTypesSorted();
            List<Element> elementsHQ = getListUnitsByUnitTypeName(indexesUnitTypes, UnitTypesEnum.HQ);
            List<Element> elementsTROOPS = getListUnitsByUnitTypeName(indexesUnitTypes, UnitTypesEnum.TROOPS);
            List<Element> elementsELITES = getListUnitsByUnitTypeName(indexesUnitTypes, UnitTypesEnum.ELITES);
            List<Element> elementsFAST_ATTACK = getListUnitsByUnitTypeName(indexesUnitTypes, UnitTypesEnum.FAST_ATTACK);
            List<Element> elementsHEAVY_SUPPORT = getListUnitsByUnitTypeName(indexesUnitTypes, UnitTypesEnum.HEAVY_SUPPORT);
            List<Element> elementsLORDS_OF_WAR = getListUnitsByUnitTypeName(indexesUnitTypes, UnitTypesEnum.LORDS_OF_WAR);
            List<Element> elementsUNALIGNED = getListUnitsByUnitTypeName(indexesUnitTypes, UnitTypesEnum.UNALIGNED);
            List<Element> elementsDEFAULT_UNREGISTERED = getUnregisteredUnits();
            result.add(elementsHQ);
            result.add(elementsTROOPS);
            result.add(elementsELITES);
            result.add(elementsFAST_ATTACK);
            result.add(elementsHEAVY_SUPPORT);
            result.add(elementsLORDS_OF_WAR);
            result.add(elementsUNALIGNED);
            result.add(elementsDEFAULT_UNREGISTERED);
            System.out.println("Parsing is OK");
        }

        return result;
    }

    private List<Element> getUnregisteredUnits() {
        return null;
    }

    private LinkedHashMap<UnitTypesEnum, Integer> getIndexesUnitTypesSorted(){
        Map<UnitTypesEnum, Integer> unitTypesEnumIntegerMap = new HashMap<>();
        for (UnitTypesEnum unitType: UnitTypesEnum.values()
             ) {
            Element unitTypeHref = mainContent.select("a[name=" + unitType.getValue() + "]").first();
            int indexOfUnitType = mainContent.indexOf(unitTypeHref);
            if(unitTypeHref != null && indexOfUnitType != -1){
                unitTypesEnumIntegerMap.put(unitType, indexOfUnitType);
                setRead(unitTypeHref, indexOfUnitType);
            }

        }
        return unitTypesEnumIntegerMap.entrySet()
                .stream()
                .sorted((Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private void setRead(Element unitTypeHref, int indexOfUnitType) {
        unitTypeHref.attr("FetcherFound", true);
    }

    private List<Element> getListUnitsByUnitTypeName(LinkedHashMap<UnitTypesEnum, Integer> indexes, UnitTypesEnum unitType) {
        List<Element> elements = new ArrayList<>();
        Iterator<Map.Entry<UnitTypesEnum, Integer>> iterator = indexes.entrySet().iterator();
        int targetIndex = -1;
        int nextUnitIndex = -1;
        while(iterator.hasNext()){
            Map.Entry<UnitTypesEnum, Integer> next = iterator.next();
            if (next.getKey().equals(unitType)){
                targetIndex = next.getValue() + 1;
                nextUnitIndex = iterator.hasNext() ? iterator.next().getValue() : -1;
                break;
            }
        }
        if(nextUnitIndex != -1)
            return this.mainContent.subList(targetIndex, nextUnitIndex);
        else if(targetIndex != -1)
            return this.mainContent.subList(targetIndex, mainContent.size());
        else return null;
    }
}
