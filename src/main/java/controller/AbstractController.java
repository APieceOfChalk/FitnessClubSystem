package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.utils.Menu;

public abstract class AbstractController {

    @FXML
    private Label title;

    public void setTitle(Menu menu) {
        this.title.setText(menu.getTitle());
    }
}
