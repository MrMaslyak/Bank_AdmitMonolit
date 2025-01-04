package org.example.bank;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bank.database.DatabaseR;
import org.example.bank.systems.StageManager;
import org.example.bank.systems.SystemAuthorization;
import org.example.bank.until.TimeLobby;
import org.example.bank.webscraper.ExchangeRate;
import org.example.bank.webscraper.News;
import org.slf4j.Logger;



public class LobbyController {

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

    public void setRegistration() {
        StageManager.switchScene(registration, "/org/example/bank/fxml/registration.fxml");
        logger.info("Переход на страницу регистрации");
    }

    public void Authorization() {
        SystemAuthorization systemAuthorization = new SystemAuthorization(registration, loginL, passwordL, errorL);
        systemAuthorization.setLogin();
    }

}
