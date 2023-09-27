module com.example.serversd {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires jjwt.api;
    requires jjwt.impl;

    opens com.example.view.modal to javafx.fxml;
    opens com.example.view to javafx.fxml;
    opens com.example.server;
    exports com.example.view.modal;
    exports com.example.server.packages to com.fasterxml.jackson.databind;
    exports com.example.server.base to com.fasterxml.jackson.databind;
    exports com.example.server.package_data.request.login to com.fasterxml.jackson.databind;
    exports com.example.server.package_data.response.login to com.fasterxml.jackson.databind;
    exports com.example.server.packages.login to com.fasterxml.jackson.databind;
    exports com.example.view;
    opens com.example.server.exceptions;
}
