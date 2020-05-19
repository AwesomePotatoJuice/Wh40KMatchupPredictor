package org.example;

import fetcher.Fetcher;
import org.jsoup.nodes.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Fetcher fetcher = new Fetcher();
        Document wahapediaData = fetcher.getWahapediaData();
        System.out.println("wahapediaData = " + wahapediaData.toString());
    }
}
