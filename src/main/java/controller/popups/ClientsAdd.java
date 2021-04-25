package main.java.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.controller.Clients;
import main.java.utils.RestApi;

/**
 * Контроллер для формы добавления клиента.
 */
public class ClientsAdd {

    /**
     * ФИО.
     */
    @FXML
    private TextField name;
    /**
     * Паспортные данные.
     */
    @FXML
    private TextField passport;
    /**
     * Телефон.
     */
    @FXML
    private TextField phone;
    @FXML
    private Label title;
    @FXML
    private Label message;

    private RestApi myApiSession = new RestApi();

    /**
     * Выход из окна.
     */
    @FXML
    private void handleCancel() {
        name.getScene().getWindow().hide();
    }

    /**
     * Открытие окна.
     */
    public static void showAddView() {
        try {

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(ClientsEdit.class.getResource("/views/popups/ClientsAdd.fxml"));
            stage.setScene(new Scene(loader.load()));

            ClientsAdd controller = loader.getController();
            controller.setTitle();

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка на корректность введенных данных.
     * @return сообщение об ошибке.
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "Не валидно имя клиента";
        }
        else if (passport.getText() == null || passport.getText().length() == 0) {
            errorMessage += "Не валидны пасспортные данные";
        }
        else if (phone.getText() == null || phone.getText().length() == 0) {
            errorMessage += "Не валиден номер телефона";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            message.setText(errorMessage);
            return false;
        }
    }

    /**
     * Создание нового клиента на нажатие кнопки ОК.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Clients client = new Clients();
            client.setName(name.getText());
            client.setPassport(passport.getText());
            client.setPhone(phone.getText());
            myApiSession.createClient(client);
            name.getScene().getWindow().hide();
        }
    }

    /**
     * Название формы.
     */
    public void setTitle() {
        title.setText("Создать нового клиента");
    }
}
