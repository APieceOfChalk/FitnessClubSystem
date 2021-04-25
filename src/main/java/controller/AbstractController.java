package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.utils.Menu;

/**
 * Класс, задающий название страницы.
 */
public abstract class AbstractController {

    @FXML
    private Label title;

    /**
     * Задает название страницы
     * @param menu - страница
     */
    public void setTitle(Menu menu) {
        this.title.setText(menu.getTitle());
    }
}
