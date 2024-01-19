package com.juanite.controller;

import java.io.IOException;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    public Button btn_a;
    @FXML
    public Button btn_b;
    @FXML
    public Button btn_c;
    @FXML
    public Button btn_d;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
