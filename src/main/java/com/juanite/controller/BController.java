package com.juanite.controller;

import com.juanite.App;
import com.juanite.model.DAO.DAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BController {

    @FXML
    public Button btn_startStop;
    @FXML
    public Button btn_back;
    @FXML
    public Button btn_post;
    @FXML
    public TextField txt_name;
    @FXML
    public ListView<String> list_products;

    private volatile boolean isRunning;
    private Thread refreshThread;
    private DAO dao;

    public void initialize() {
        this.dao = new DAO();
        isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            btn_startStop.setText("Stop");

            refreshThread = new Thread(() -> {
                while (isRunning) {
                    try {
                        ObservableList<String> products = dao.findAll();
                        updateListView(products);

                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            refreshThread.start();
        } else {
            isRunning = false;
            btn_startStop.setText("Start");
        }
    }

    public void back() throws IOException {
        isRunning = false;
        try {
            if (refreshThread != null) refreshThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        App.setRoot("main");
    }

    public void post() {
        new Thread(() -> {
            try {
                String name = txt_name.getText().trim();
                if (!name.isEmpty()) {
                    dao.save(name);
                    txt_name.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateListView(ObservableList<String> products) {
        javafx.application.Platform.runLater(() -> list_products.setItems(products));
    }
}