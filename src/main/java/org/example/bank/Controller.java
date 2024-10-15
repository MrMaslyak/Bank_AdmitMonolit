package org.example.bank;

import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback; // Правильный импорт

import java.time.LocalDate;

public class Controller {

    @FXML
    private DatePicker datePicker;

    @FXML
    public void initialize() {
        // Установить сегодняшнюю дату
        datePicker.setValue(LocalDate.now());

        // Запретить выбор других дат
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        // Блокировка всех дат, кроме сегодняшней
                        if (!item.equals(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Стиль заблокированных дат
                        }
                    }
                };
            }
        });

        // Отключить возможность редактирования даты вручную
        datePicker.setEditable(false);
    }
}
