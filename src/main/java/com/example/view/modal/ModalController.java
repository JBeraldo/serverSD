package com.example.view.modal;

import com.example.server.Server;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModalController {

    public TextField port_tf;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Stage stage = (Stage) port_tf.getScene().getWindow();
        Integer port_value = Integer.parseInt(port_tf.getText());
        stage.close();
        Server.start(port_value);
    }
}