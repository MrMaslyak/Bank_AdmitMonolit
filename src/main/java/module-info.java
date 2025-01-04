module org.example.bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.jsoup;
    requires java.sql;
    requires org.slf4j;
    requires jbcrypt;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires static lombok;
    requires com.auth0.jwt;


    opens org.example.bank to javafx.fxml;
    exports org.example.bank;
    exports org.example.bank.systems;
    opens org.example.bank.systems to javafx.fxml;
    exports org.example.bank.controller;
    opens org.example.bank.controller to javafx.fxml;
    exports org.example.bank.database;
    opens org.example.bank.database to javafx.fxml;
}