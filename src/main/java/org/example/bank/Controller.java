package org.example.bank;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.threads.Time;
import org.example.bank.webscrap.ExchangeRate;
import org.example.bank.webscrap.News;

public class Controller {


    @FXML
    private Label time, plnValue, euroValue, dollarValue, news1, news2, news3, news4, news5, news6;

    private Time threadTime;
    private ExchangeRate exchangeRate = new ExchangeRate();
    private String
            dollarRate = exchangeRate.getDollar(),
            euroRate = exchangeRate.getEuro(),
            plnRate = exchangeRate.getPln();





    public void initialize() {
        threadTime = new Time(time);
        threadTime.start();
        plnValue.setText(plnRate);
        euroValue.setText(euroRate);
        dollarValue.setText(dollarRate);

        News news  = new News();
        news1.setText(news.getNews1());
        news2.setText(news.getNews2());
        news3.setText(news.getNews3());
        news4.setText(news.getNews4());
        news5.setText(news.getNews5());
        news6.setText(news.getNews6());



    }

}
