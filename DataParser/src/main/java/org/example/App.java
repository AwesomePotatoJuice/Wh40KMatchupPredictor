package org.example;

import fetcher.Fetcher;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Fetcher fetcher = new Fetcher();
        List<List<Element>> wahapediaData = fetcher.getWahapediaData();
    }
}
