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
    private Thread thread1;
    private Thread thread2;
    private volatile boolean isRunning;

    private int seconds = 0;

    public void initialize() {
        btn_startStop.setText("Start");
        btn_back.setVisible(true);
        lbl_thread1.setText("0");
        lbl_thread2.setText("0");
    }

    /**
     * Method that starts/stops the count using a thread for the seconds units and
     * a different thread for the seconds tens.
     */
    @FXML
    private void startCount() {
        if (isRunning) {
            isRunning = false;
            btn_startStop.setText("Start");
        } else {
            isRunning = true;
            btn_startStop.setText("Stop");

            // Units Thread
            thread1 = new Thread(() -> {
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds++;
                    int units = seconds % 10;
                    updateLabel(lbl_thread1, units);
                }
            });
            thread1.start();

            // Tens Thread
            thread2 = new Thread(() -> {
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int tens = seconds / 10;
                    updateLabel(lbl_thread2, tens);
                }
            });
            thread2.start();
        }
    }

    /**
     * Method that updates a label.
     * @param label the label to update.
     * @param value the value to represent.
     */
    private void updateLabel(Label label, int value) {
        javafx.application.Platform.runLater(() -> label.setText(Integer.toString(value)));
    }

    /**
     * Method that stops both threads using join() before going back to the main menu.
     * @throws IOException
     */
    @FXML
    private void back() throws IOException {
        isRunning = false;
        try {
            if (thread1 != null) thread1.join();
            if (thread2 != null) thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        App.setRoot("main");
    }
}
