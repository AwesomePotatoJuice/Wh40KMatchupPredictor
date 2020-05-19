package fetcher;

import data.FactionEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Fetcher {
    private Jsoup jsoup;
    private static final String adeptusMechanicusUrl = "http://wahapedia.ru/wh40k8ed/factions/adeptus-mechanicus/datasheets.html";

    public Document getWahapediaData(){
        return  getWahapediaDataDocument(FactionEnum.ADEPTUS_MECHANICUS, 0);
    }

    private Document getWahapediaDataDocument(FactionEnum faction, int limit){
        try {
            Document doc = Jsoup.connect(adeptusMechanicusUrl).get();
            System.out.print(doc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
