package org.example.bank.until;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.example.bank.database.DatabaseR;
import org.slf4j.Logger;

import java.time.LocalTime;

public class TimeLobby extends Thread{

    private LocalTime currentTime = LocalTime.now();
    private final Label time, textTime;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TimeLobby.class);

    public TimeLobby(javafx.scene.control.Label time, Label textTime) {
        this.time = time;
        this.textTime = textTime;
    }

    @Override
    public void run() {
        try {
            logger.info("Время обновлено: " + currentTime);
            while (true) {
                LocalTime currentTime = LocalTime.now();
                String timeString = String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
                if (currentTime.getHour() >= 6 && currentTime.getHour() < 12) {
                    textTime.setText("Добрий ранок");
                } else if (currentTime.getHour() >= 12 && currentTime.getHour() < 18) {
                    textTime.setText("Добрий день");
                } else if (currentTime.getHour() >= 18 && currentTime.getHour() < 23) {
                    textTime.setText("Доброго вечора");
                } else {
                    textTime.setText("Доброї ночі");
                }
                Platform.runLater(() -> time.setText(timeString));
                TimeLobby.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.error("Ошибка при обновлении времени: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
