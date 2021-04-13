package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.java.controller.exception.AppException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {


    @FXML
    private Label message;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void attachEvent() {
        loginField.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (cancelBtn.isFocused()) {
                    cancel();
                }
                else {
                    login();
                }
            }
        });
    }

    @FXML
    private void login() {
        try {

            String loginId = "root";
            String passwordId = "0102";

            if (loginField.getText().equals(loginId) && passwordField.getText().equals(passwordId)) {

                //открытие приложения
                MainFrame.show();

                //закрытие логина
                cancel();
            } if (loginField.getText().isEmpty()) {
                throw new AppException("Пожалуйста, введите логин.");
            } if (passwordField.getText().isEmpty()) {
                throw new AppException("Пожалуйста, введите пароль.");
            } else {
                throw new AppException("Неверный логин или пароль.");
            }
        }
        catch (AppException e) {
            message.setText(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            cancel();
        }
    }

    @FXML
    private void cancel() {
        loginBtn.getScene().getWindow().hide();
    }

    public static void loadView(Stage stage) {

        try {

            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/views/Login.fxml"));
            Parent view = loader.load();
            stage.setScene(new Scene(view));

            LoginController controller = loader.getController();
            controller.attachEvent();

            stage.show();

        }

        catch (IOException e) {
            e.printStackTrace();

        }
    }

}
