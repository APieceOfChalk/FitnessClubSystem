package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.controller.common.Dialog;
import main.java.utils.Menu;

public class MainFrame {

    @FXML
    private VBox sideBar;
    @FXML
    private StackPane contentView;

    @FXML
    private void initialize() {
        loadView(Menu.Home);
    }

    @FXML
    private void clickMenu(MouseEvent event) {

        Node node = (Node) event.getSource();

        if (node.getId().equals("Exit")) {
            //Подтверждение выхода из приложения
            Dialog.DialogBuilder.builder()
                    .title("Выход")
                    .message("Вы действительно хотите выйти?")
                    .okActionListener(() -> sideBar.getScene().getWindow().hide())
                    .build().show();
        } else {
            Menu menu = Menu.valueOf(node.getId());
            System.out.println(node.getId());
            loadView(menu);
        }
    }

    private void loadView(Menu menu) {
        try {

            for (Node node : sideBar.getChildren()) {

                node.getStyleClass().remove("active");

                if (node.getId().equals(menu.name())) {
                    node.getStyleClass().add("active");
                }
            }

            contentView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + menu.getFxml()));
            Parent view = loader.load();


            contentView.getChildren().add(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainFrame.class.getResource("/views/MainFrame.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}