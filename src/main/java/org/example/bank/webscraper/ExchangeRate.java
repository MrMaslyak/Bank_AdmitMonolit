package org.example.bank.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ExchangeRate {

    private final String url = "https://privatbank.ua/rates-archive";

    public String getCurrencyRate(String currencyCode) {
        try {

            Document doc = Jsoup.connect(url).get();
            Elements currencyPairs = doc.select(".currency-pairs");


            for (Element pair : currencyPairs) {
                Element nameElement = pair.selectFirst(".names span");
                if (nameElement != null && nameElement.text().contains(currencyCode)) {
                    Element rateElement = pair.selectFirst(".purchase span");
                    if (rateElement != null) {
                        return rateElement.text();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Not Available";
    }

    public String getDollar() {
        return getCurrencyRate("USD");
    }

    public String getEuro() {
        return getCurrencyRate("EUR");
    }

    public String getPln() {
        return getCurrencyRate("PLN");
    }
}
