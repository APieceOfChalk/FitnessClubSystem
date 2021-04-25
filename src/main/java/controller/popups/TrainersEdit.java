package main.java.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.controller.Trainers;
import main.java.utils.RestApi;

/**
 * Контроллер для формы изменения тренера.
 */
public class TrainersEdit {

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
    /**
     * Адрес.
     */
    @FXML
    private TextField address;
    @FXML
    private Label title;
    @FXML
    private Label message;

    private RestApi myApiSession = new RestApi();
    private Trainers trainer;


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
    public static void showEditView(Trainers trainer) {
        try {

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(TrainersEdit.class.getResource("/views/popups/TrainersEdit.fxml"));
            stage.setScene(new Scene(loader.load()));

            TrainersEdit controller = loader.getController();
            controller.setTrainer(trainer);


            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохранение изменений на нажатие кнопки ОК.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            trainer.setName(name.getText());
            trainer.setPassport(passport.getText());
            trainer.setPhone(phone.getText());
            trainer.setAddress(address.getText());
            myApiSession.updateTrainer(trainer);
            name.getScene().getWindow().hide();
        }
    }

    /**
     * Автозаполнение предыдущих занчений.
     * @param trainer - предыдущий тренер.
     */
    public void setTrainer(Trainers trainer) {
        this.trainer = trainer;
        title.setText("Редактировать клиента");

        name.setText(trainer.getName());
        passport.setText(trainer.getPassport());
        phone.setText(trainer.getPhone());
        address.setText(trainer.getAddress());
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
        else if (address.getText() == null || address.getText().length() == 0) {
            errorMessage += "Не валиден адрес";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }

}

