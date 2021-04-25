package main.java.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.controller.Places;
import main.java.utils.RestApi;

/**
 * Контроллер для формы добавления зала.
 */
public class PlacesAdd {

    /**
     * Название зала.
     */
    @FXML
    private TextField name;
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

            FXMLLoader loader = new FXMLLoader(PlacesEdit.class.getResource("/views/popups/PlacesAdd.fxml"));
            stage.setScene(new Scene(loader.load()));

            PlacesAdd controller = loader.getController();
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
            errorMessage += "Не валидно название зала";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            message.setText(errorMessage);
            return false;
        }
    }

    /**
     * Создание нового зала на нажатие кнопки ОК.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Places place = new Places();
            place.setName(name.getText());
            myApiSession.createPlace(place);
            name.getScene().getWindow().hide();
        }
    }

    /**
     * Название формы.
     */
    public void setTitle() {
        title.setText("Создать новый зал");
    }
}
