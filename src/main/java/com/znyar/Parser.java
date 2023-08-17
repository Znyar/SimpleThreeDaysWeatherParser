package com.znyar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {

    private static Document getPage() throws IOException {

        String url = "https://world-weather.ru/pogoda/russia/moscow/24hours/?ysclid=lkiombnza441380607";
        return Jsoup.parse(new URL(url), 3000);

    }

    public static Element getData() throws IOException {
        Document page = getPage();
        Element fieldWeather = page.select("div[id=content-left]").first();
        return fieldWeather;
    }

    public static Elements getDates(Element fieldWeather) throws IOException {
        return fieldWeather.select("div[class=dates], div[class=dates red]");
    }

    public static Elements getPeriods(Element fieldWeather) throws IOException {
        return fieldWeather.select("td[class=weather-day]");
    }

    public static Elements getTemperatures(Element fieldWeather) throws IOException {
        return fieldWeather.select("td[class=weather-temperature]");
    }

}


