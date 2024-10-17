package org.example.bank;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.threads.Time;
import org.example.bank.webscrap.ExchangeRate;

public class Controller {


    @FXML
    private Label time, plnValue, euroValue, dollarValue;

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

    }

}
