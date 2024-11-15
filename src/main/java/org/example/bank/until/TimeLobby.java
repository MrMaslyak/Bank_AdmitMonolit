package org.example.bank.until;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeLobby extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(TimeLobby.class);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private volatile boolean running = true;
    private final Label time;
    private final Label textTime;

    public TimeLobby(Label time, Label textTime) {
        this.time = time;
        this.textTime = textTime;
    }

    @Override
    public void run() {
        try {
            while (running) {
                LocalTime currentTime = LocalTime.now();
                String timeString = currentTime.format(TIME_FORMATTER);

                String greeting = getGreeting(currentTime);
                Platform.runLater(() -> {
                    time.setText(timeString);
                    textTime.setText(greeting);
                });

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.warn("Поток обновления времени был прерван.");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.error("Ошибка при обновлении времени: {}", e.getMessage(), e);
        }
    }


    private String getGreeting(LocalTime currentTime) {
        int hour = currentTime.getHour();
        if (hour >= 6 && hour < 12) {
            return "Добрий ранок";
        } else if (hour >= 12 && hour < 18) {
            return "Добрий день";
        } else if (hour >= 18 && hour < 23) {
            return "Доброго вечора";
        } else {
            return "Доброї ночі";
        }
    }


    public void stopClock() {
        running = false;
        interrupt();
    }
}
