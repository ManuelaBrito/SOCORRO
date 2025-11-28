package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.App;

public class SecondaryController {

    @FXML
    private Label lblMessage;

    @FXML
    private Button btnVoltar;

    @FXML
    private void switchToPrimary() {
        try {
            // Volta para a tela primary
            App.setRoot("primary");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        lblMessage.setText("Esta é a tela secundária!");
    }
}