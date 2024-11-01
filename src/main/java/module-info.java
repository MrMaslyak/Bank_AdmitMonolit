module org.example.bank {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.jsoup;

    opens org.example.bank to javafx.fxml;
    exports org.example.bank;
    exports org.example.bank.registrationSystem;
    opens org.example.bank.registrationSystem to javafx.fxml;
}