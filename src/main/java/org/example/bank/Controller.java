package org.example.bank;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bank.database.DatabaseR;
import org.example.bank.threads.Time;
import org.example.bank.webscrap.ExchangeRate;
import org.example.bank.webscrap.News;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Controller {


    public Button registration  ;
    public TextField loginL ;
    public PasswordField passwordL;
    @FXML
    private Label time, plnValue, euroValue, dollarValue, news1, news2, news3, news4, news5, news6, errorL;

    private Time threadTime;
    private ExchangeRate exchangeRate = new ExchangeRate();
    private String
            dollarRate = exchangeRate.getDollar(),
            euroRate = exchangeRate.getEuro(),
            plnRate = exchangeRate.getPln();
    private DatabaseR database = DatabaseR.getInstance();





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

    public void setRegistration() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.DECORATED);

            registration.getScene().getWindow().hide();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLogin() {
        if (database.passBank(loginL.getText(), passwordL.getText())){
            errorL.setVisible(false);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("bank.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.DECORATED);
                registration.getScene().getWindow().hide();

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Неверный логин или пароль");
            errorL.setVisible(true);
            errorL.setText("Incorrect login or password");
        }

    }
}
