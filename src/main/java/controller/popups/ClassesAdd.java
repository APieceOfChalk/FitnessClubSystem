package main.java.controller.popups;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import main.java.controller.Classes;
import main.java.controller.Places;
import main.java.controller.Trainers;
import main.java.utils.RestApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для формы добавления занятия.
 */
public class ClassesAdd {

    /**
     * Поле для названия занятия.
     */
    @FXML
    private TextField name;
    /**
     * Выбор тренера.
     */
    @FXML
    private ComboBox<Trainers> trainersComboBox;
    /**
     * Выбор зала.
     */
    @FXML
    private ComboBox<Places> placesComboBox;
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
     * Заполнение ComboBox тренерами и залами.
     * @throws IOException если нет подключения к серверу.
     */
    public void initialize() throws IOException {
        String tUrl = "http://localhost:8080/trainers";
        URL trurl = new URL(tUrl);
        URLConnection request = trurl.openConnection();
        request.connect();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray ArrayOfTrainers = root.getAsJsonArray();


        List<Trainers> trainers = new ArrayList<>();
        for (JsonElement trainersElement : ArrayOfTrainers) {
            JsonObject trainersObject = trainersElement.getAsJsonObject();

            String id = trainersObject.get("id").getAsString();
            String name = trainersObject.get("name").getAsString();
            String passport = trainersObject.get("passport").getAsString();
            String phone = trainersObject.get("phone").getAsString();
            String address = trainersObject.get("address").getAsString();

            Trainers trainer = new Trainers(id, name, passport, phone, address);
            trainers.add(trainer);


        }

        ObservableList observableTrainers = FXCollections.observableList(trainers);
        trainersComboBox.setItems(observableTrainers);

        trainersComboBox.setConverter(new StringConverter<Trainers>() {

            @Override
            public String toString(Trainers object) {
                return object.getName();
            }

            @Override
            public Trainers fromString(String string) {
                return trainersComboBox.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        String sUrl = "http://localhost:8080/areas";
        URL url = new URL(sUrl);
        URLConnection request2 = url.openConnection();
        request2.connect();


        JsonParser jp2 = new JsonParser();
        JsonElement root2 = jp2.parse(new InputStreamReader((InputStream) request2.getContent()));
        JsonArray ArrayOfPlaces = root2.getAsJsonArray();


        List<Places> places = new ArrayList<>();
        for (JsonElement placesElement : ArrayOfPlaces) {
            JsonObject placesObject = placesElement.getAsJsonObject();

            String id = placesObject.get("id").getAsString();
            String name = placesObject.get("name").getAsString();

            Places place = new Places(id, name);
            places.add(place);

        }
        ObservableList observablePlaces = FXCollections.observableList(places);
        placesComboBox.setItems(observablePlaces);

        placesComboBox.setConverter(new StringConverter<Places>() {

            @Override
            public String toString(Places object) {
                return object.getName();
            }

            @Override
            public Places fromString(String string) {
                return placesComboBox.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

    }


    /**
     * Открытие окна.
     */
    public static void showAddView() {
        try {

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(ClientsEdit.class.getResource("/views/popups/ClassesAdd.fxml"));
            stage.setScene(new Scene(loader.load()));

            ClassesAdd controller = loader.getController();
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
            errorMessage += "Не валидно название занятия";
        }
        else if (trainersComboBox.getValue() == null) {
            errorMessage += "Не выбран тренер";
        }
        else if (placesComboBox.getValue() == null) {
            errorMessage += "Не выбран зал";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            message.setText(errorMessage);
            return false;
        }
    }


    /**
     * Создание нового занятия на нажатие кнопки ОК.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Classes class_ = new Classes();
            class_.setName(name.getText());

            Places place = placesComboBox.getValue();
            class_.setAreaId(place.getId());

            Trainers trainer = trainersComboBox.getValue();
            class_.setTrainerId(trainer.getId());

            myApiSession.createClass(class_);
            name.getScene().getWindow().hide();
        }
    }

    /**
     * Название формы.
     */
    public void setTitle() {
        title.setText("Создать новое занятие");
    }
}
