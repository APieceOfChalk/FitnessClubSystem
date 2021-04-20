package main.java.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import main.java.models.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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

            String sUrl = "http://localhost:8080/users";
            URL url = new URL(sUrl);
            URLConnection request = url.openConnection();
            request.connect();


            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonArray array = root.getAsJsonArray();
            for (JsonElement element : array) {
                JsonObject object = element.getAsJsonObject();
                String loginId = object.get("login").getAsString();
                String passwordId = (object.get("password").getAsString());


                if (loginField.getText().equals(loginId) && DigestUtils.shaHex(passwordField.getText()).equals((passwordId))) {

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
