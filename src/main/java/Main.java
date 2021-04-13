package main.java;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.controller.LoginController;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setResizable(false);
        LoginController.loadView(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
