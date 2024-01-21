package com.juanite.controller;

import java.io.IOException;

import com.juanite.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    public Button btn_a;
    @FXML
    public Button btn_d;

    /**
     * Method that switches to the "chrono" screen.
     * @throws IOException
     */
    @FXML
    private void switchToA() throws IOException {
        App.setRoot("a");
    }

    /**
     * Method that switches to the DB Access screen.
     * @throws IOException
     */
    @FXML
    private void switchToB() throws IOException {
        App.setRoot("b");
    }
}
