package org.example.bank;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bank.systems.StageManager;
import org.example.bank.systems.SystemAuthorization;
import org.example.bank.until.TimeLobby;
import org.example.bank.webscraper.ExchangeRate;
import org.example.bank.webscraper.News;
import org.slf4j.Logger;

import java.awt.*;
import java.net.URI;


public class LobbyController {

    public Button btn1, btn2, btn3, btn4, btn5, btn6;
    @FXML
    private Button registration;
    @FXML
    private TextField loginL;
    @FXML
    private PasswordField passwordL;
    @FXML
    private Label time, plnValue, euroValue, dollarValue, news1, news2, news3, news4, news5, news6, errorL, textTime;

    private TimeLobby threadTimeLobby;

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LobbyController.class);
    private final ExchangeRate exchangeRate = new ExchangeRate();




    public void initialize() {
        initializeThread();
        loadExchangeRates();
        loadNews();
        setupNewsButtons();
    }

    private void initializeThread() {
        threadTimeLobby = new TimeLobby(time, textTime);
        threadTimeLobby.start();
    }

    private void loadExchangeRates() {
        plnValue.setText(exchangeRate.getPln());
        euroValue.setText(exchangeRate.getEuro());
        dollarValue.setText(exchangeRate.getDollar());
    }

    private void loadNews() {
        News news = new News();
        news1.setText(news.getNews(1));
        news2.setText(news.getNews(2));
        news3.setText(news.getNews(3));
        news4.setText(news.getNews(4));
        news5.setText(news.getNews(5));
        news6.setText(news.getNews(6));

    }

    private void setupNewsButtons() {
        setupButton(btn1, 1);
        setupButton(btn2, 2);
        setupButton(btn3, 3);
        setupButton(btn4, 4);
        setupButton(btn5, 5);
        setupButton(btn6, 6);
    }

    public void setRegistration() {
        StageManager.switchScene(registration, "/org/example/bank/fxml/registration.fxml");
        logger.info("Переход на страницу регистрации");
    }

    public void Authorization() {
        SystemAuthorization systemAuthorization = new SystemAuthorization(registration, loginL, passwordL, errorL);
        systemAuthorization.setLogin();
    }

    private void setupButton(Button button, int newsIndex) {
        String link = News.getNewsLink(newsIndex);
        if (link != null){
            if (link.startsWith("/news/")) {
                link = "https://www.pravda.com.ua/rus" + link;
                String finalLink = link;
                button.setOnAction(event -> openLink(finalLink));
            }
            else {
                String finalLink1 = link;
                button.setOnAction(event -> openLink(finalLink1));
            }
        } else {
            button.setDisable(true);
        }
    }

    private void openLink(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            System.err.println("Ошибка при открытии ссылки: " + e.getMessage());
        }
    }
}
