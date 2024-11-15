package org.example.bank.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class News {

    private final String url = "https://www.pravda.com.ua/news/";
    private final List<String> newsList = new ArrayList<>();

    public News() {
        loadNews();
    }

    private void loadNews() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select(".article_news_list .article_header");

            for (int i = 0; i < 6; i++) {
                Element newsElement = newsElements.get(i);
                String newsText = newsElement.text();
                newsList.add(validateFirstWord(newsText));
            }

        } catch (IOException e) {
            System.err.println("Ошибка при загрузке новостей: " + e.getMessage());
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
        if (index < 1 || index > newsList.size()) {
            return "Новость недоступна";
        }
        return newsList.get(index - 1);
    }

    public List<String> getAllNews() {
        return new ArrayList<>(newsList);
    }

}
