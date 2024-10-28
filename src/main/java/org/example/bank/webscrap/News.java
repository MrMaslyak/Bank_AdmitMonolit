package org.example.bank.webscrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class News {

    private final String url = "https://www.pravda.com.ua/news/";
    private String news1, news2, news3, news4, news5, news6;

    public String getNews1() {
        try {

            Document doc = Jsoup.connect(url).get();

            Elements newsElements = doc.select(".article_news_list .article_header");
            Element firstNewsElement = newsElements.get(1);

            news1 = firstNewsElement.text();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return news1;
    }

    public String getNews2() {

        try {

            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select(".article_news_list .article_header");

            Element secondNewsElement = newsElements.get(2);
            news2 = secondNewsElement.text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return news2;
    }

    public String getNews3() {

        try {

            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select(".article_news_list .article_header");

            Element secondNewsElement = newsElements.get(3);
            news3 = secondNewsElement.text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return news3;
    }


    public String getNews4() {

        try {

            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select(".article_news_list .article_header");

            Element secondNewsElement = newsElements.get(4);
            news4 = secondNewsElement.text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return news4;
    }

    public String getNews5() {

        try {

            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select(".article_news_list .article_header");

            Element secondNewsElement = newsElements.get(5);
            news5 = secondNewsElement.text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return news5;
    }

    public String getNews6() {

        try {

            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select(".article_news_list .article_header");

            Element secondNewsElement = newsElements.get(6);
            news6 = secondNewsElement.text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return news6;
    }
}
