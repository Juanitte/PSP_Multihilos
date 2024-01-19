package com.juanite.controller;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class AController {

    @FXML
    public Label lbl_thread1;
    @FXML
    public Label lbl_thread2;
    @FXML
    public Button btn_startStop;
    @FXML
    public Button btn_back;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
