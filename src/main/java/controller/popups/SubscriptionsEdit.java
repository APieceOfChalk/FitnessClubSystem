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
import main.java.controller.Clients;
import main.java.controller.Subscriptions;
import main.java.utils.RestApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для формы изменения абонемента.
 */
public class SubscriptionsEdit {

    /**
     * Выбор клиента.
     */
    @FXML
    private ComboBox<Clients> clientsComboBox;
    /**
     * Выбор занятия.
     */
    @FXML
    private ComboBox<Classes> activitiesComboBox;
    /**
     * Срок.
     */
    @FXML
    private TextField dateField;
    /**
     * Цена.
     */
    @FXML
    private TextField priceField;
    @FXML
    private Label title;
    @FXML
    private Label message;

    private RestApi myApiSession = new RestApi();
    private Subscriptions subscription;

    /**
     * Выход из окна.
     */
    @FXML
    private void handleCancel() {
        clientsComboBox.getScene().getWindow().hide();
    }

    /**
     * Открытие окна.
     */
    public static void showEditView(Subscriptions subscription) {
        try {

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(PlacesEdit.class.getResource("/views/popups/SubscriptionsEdit.fxml"));
            stage.setScene(new Scene(loader.load()));

            SubscriptionsEdit controller = loader.getController();
            controller.setSubscription(subscription);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Заполнение ComboBox клиентами и занятиями.
     * @throws IOException если нет подключения к серверу.
     */
    public void initialize() throws IOException {
        String cUrl = "http://localhost:8080/clients";
        URL url = new URL(cUrl);
        URLConnection request = url.openConnection();
        request.connect();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray ArrayOfClients = root.getAsJsonArray();


        List<Clients> clients = new ArrayList<>();
        for (JsonElement clientsElement : ArrayOfClients) {
            JsonObject clientsObject = clientsElement.getAsJsonObject();

            String id = clientsObject.get("id").getAsString();
            String name = clientsObject.get("name").getAsString();
            String passport = clientsObject.get("passport").getAsString();
            String phone = clientsObject.get("phone").getAsString();

            Clients client = new Clients(id, name, passport, phone);
            clients.add(client);

        }

        ObservableList observableClients = FXCollections.observableList(clients);
        clientsComboBox.setItems(observableClients);

        clientsComboBox.setConverter(new StringConverter<Clients>() {

            @Override
            public String toString(Clients object) {
                return object.getName();
            }

            @Override
            public Clients fromString(String string) {
                return clientsComboBox.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        String sUrl = "http://localhost:8080/activities";
        URL url1 = new URL(sUrl);
        URLConnection request1 = url1.openConnection();
        request1.connect();


        JsonParser jp1 = new JsonParser();
        JsonElement root1 = jp1.parse(new InputStreamReader((InputStream)request1.getContent()));
        JsonArray ArrayOfClasses = root1.getAsJsonArray();


        List<Classes> classes = new ArrayList<>();
        for (JsonElement classesElement : ArrayOfClasses) {
            JsonObject classesObject = classesElement.getAsJsonObject();

            String id = classesObject.get("id").getAsString();
            String name = classesObject.get("name").getAsString();
            String areaId = classesObject.get("area").getAsJsonObject().get("name").getAsString();
            String trainerId = classesObject.get("trainer").getAsJsonObject().get("name").getAsString();

            Classes class_ = new Classes(id, name, areaId, trainerId);
            classes.add(class_);

        }

        ObservableList observableClasses = FXCollections.observableList(classes);
        activitiesComboBox.setItems(observableClasses);

        activitiesComboBox.setConverter(new StringConverter<Classes>() {

            @Override
            public String toString(Classes object) {
                return object.getName();
            }

            @Override
            public Classes fromString(String string) {
                return activitiesComboBox.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

    }

    /**
     * Автозаполнение предыдущих занчений (кроме ComboBox).
     * @param subscription - предыдущий абонемент.
     */
    public void setSubscription(Subscriptions subscription) {
        this.subscription = subscription;
        title.setText("Редактировать абонемент");

        dateField.setText(subscription.getDate());
        priceField.setText(subscription.getPrice());
    }

    /**
     * Проверка на корректность введенных данных.
     * @return сообщение об ошибке.
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (dateField.getText() == null || dateField.getText().length() == 0) {
            errorMessage += "Не валиден срок действия";
        }
        else if (clientsComboBox.getValue() == null) {
            errorMessage += "Не выбран клиент";
        }
        else if (activitiesComboBox.getValue() == null) {
            errorMessage += "Не выбрано занятие";
        }
        else if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Не валидна цена";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            message.setText(errorMessage);
            return false;
        }
    }

    /**
     * Сохранение изменений на нажатие кнопки ОК.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            subscription.setPrice(priceField.getText());
            subscription.setDate(dateField.getText());

            Clients client = clientsComboBox.getValue();
            subscription.setClient(client.getId());

            Classes class_ = activitiesComboBox.getValue();
            subscription.setActivity(class_.getId());

            myApiSession.updateSubscription(subscription);
            clientsComboBox.getScene().getWindow().hide();

        }
    }


}
