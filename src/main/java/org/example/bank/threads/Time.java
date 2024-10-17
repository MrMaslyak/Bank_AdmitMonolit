package org.example.bank.threads;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.time.LocalTime;

public class Time  extends Thread{

    private LocalTime currentTime = LocalTime.now();
    private final Label time;



    public Time(javafx.scene.control.Label time) {
        this.time = time;
    }

    @Override
    public void run() {
        try {
            while (true) {
                LocalTime currentTime = LocalTime.now();
                String timeString = String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());

                Platform.runLater(() -> time.setText(timeString));

                Time.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
