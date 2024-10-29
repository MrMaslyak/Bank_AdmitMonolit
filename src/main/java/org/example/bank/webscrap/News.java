package org.example.bank.webscrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class News {

    private final String url = "https://www.pravda.com.ua/news/";
    private List<String> newsList = new ArrayList<>();


    private void loadNews() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select(".article_news_list .article_header");

            for (int i = 1; i <= 6; i++) {
                Element newsElement = newsElements.get(i);
                String newsText = newsElement.text();
                newsList.add(validateFirstWord(newsText));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String validateFirstWord(String text) {
        String[] words = text.split(" ", 2);
        if (words.length > 1 && Character.isLowerCase(words[0].charAt(0))) {
            return words[1];
        }
        return text;
    }


    public String getNews(int index) {
        if (newsList.isEmpty()) {
            loadNews();
        }
        return index >= 1 && index <= newsList.size() ? newsList.get(index - 1) : "Новость недоступна";
    }

    public String getNews1() { return getNews(1); }
    public String getNews2() { return getNews(2); }
    public String getNews3() { return getNews(3); }
    public String getNews4() { return getNews(4); }
    public String getNews5() { return getNews(5); }
    public String getNews6() { return getNews(6); }
}
