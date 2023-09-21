module com.example.serversd {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.modal to javafx.fxml;
    opens com.example.server;
    exports com.example.modal;
    exports com.example.server.responses to com.fasterxml.jackson.databind;
    exports com.example.server.base to com.fasterxml.jackson.databind;
    exports com.example.server.data.login to com.fasterxml.jackson.databind;
}
