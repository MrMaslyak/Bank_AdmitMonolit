package org.example.bank.webscrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ExchangeRate {

    private final String url = "https://privatbank.ua/rates-archive";
    private String dollar, euro, pln;

    public String getDollar() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements currencyPairs = doc.select(".currency-pairs");


            for (Element pair : currencyPairs) {

                Element nameElement = pair.selectFirst(".names span");

                if (nameElement != null && nameElement.text().contains("USD")){

                    Element saleElement = pair.selectFirst(".purchase span");
                    if (saleElement != null) {
                        dollar = saleElement.text();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dollar;
    }

    public String getEuro() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements currencyPairs = doc.select(".currency-pairs");


            for (Element pair : currencyPairs) {

                Element nameElement = pair.selectFirst(".names span");

                if (nameElement != null && nameElement.text().contains("EUR")){

                    Element saleElement = pair.selectFirst(".purchase span");
                    if (saleElement != null) {
                        euro = saleElement.text();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return euro;
    }

    public String getPln() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements currencyPairs = doc.select(".currency-pairs");


            for (Element pair : currencyPairs) {

                Element nameElement = pair.selectFirst(".names span");

                if (nameElement != null && nameElement.text().contains("PLN")){

                    Element saleElement = pair.selectFirst(".purchase span");
                    if (saleElement != null) {
                        pln = saleElement.text();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pln;
    }

}

