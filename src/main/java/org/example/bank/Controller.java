package org.example.bank;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.bank.threads.Time;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class Controller {


    @FXML
    private Label time;
    private Time threadTime;





    public void initialize() {
        threadTime = new Time(time);
        threadTime.start();
    }

}
