package com.example.modal;

import com.example.server.Server;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;


    public HelloController() throws IOException {
        Server.start();
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        // do what you have to do
        stage.close();
        Server.start();
    }
}