package main.java.controller.tables;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.controller.AbstractController;
import main.java.controller.Clients;
import main.java.controller.popups.ClientsAdd;
import main.java.controller.popups.ClientsEdit;
import main.java.utils.RestApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для таблицы клиентов.
 */
public class ClientsTbl extends AbstractController {

    /**
     * Таблица клиентов.
     */
    @FXML
    private TableView<Clients> clientsTable;
    /**
     * Поиск.
     */
    @FXML
    private TextField searchField;

    /**
     * Колонки:
     */
    @FXML private TableColumn<Clients, String> id;
    @FXML private TableColumn<Clients, String> name;
    @FXML private TableColumn<Clients, String> passport;
    @FXML private TableColumn<Clients, String> phone;

    private RestApi myApiSession = new RestApi();

    public void initialize() throws IOException {
        initTable();
    }

    /**
     * Метод, который открывает форму для добавления клиента.
     */
    @FXML
    private void handleNewClient() {
        ClientsAdd.showAddView();
    }

    /**
     * Удаляет выбранную строку.
     * @throws IOException если нет подключения к серверу.
     */
    @FXML
    private void handleDeleteAction() {
        int selectedIndex = clientsTable.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            Clients currentClient = clientsTable.getItems().get(selectedIndex);
            if (myApiSession.deleteClient(currentClient)) {
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Клиенты не выбраны");
            alert.setContentText("Пожалуйста выберите клиента");

            alert.showAndWait();
        }
    }

    /**
     * Метод, который окрывает форму изменения клиента, если строка выделена.
     */
    @FXML
    private void editClientsData() {
        int selectedIndex = clientsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Clients buf = clientsTable.getItems().get(selectedIndex);
            ClientsEdit.showEditView(buf);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Клиенты не выбраны");
            alert.setContentText("Пожалуйста выберите клиента");

            alert.showAndWait();
        }
    }

    /**
     * Парсер json в таблицу.
     * @throws IOException если нет подключения к серверу.
     */
    private void initTable() throws IOException {

        String sUrl = "http://localhost:8080/clients";
        URL url = new URL(sUrl);
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

        ObservableList observableList = FXCollections.observableList(clients);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        FilteredList<Clients> filteredData = new FilteredList<>(observableList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }


                return false;
            });
        });

        SortedList<Clients> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(clientsTable.comparatorProperty());
        clientsTable.setItems(sortedData);

    }

    /**
     * Обновление таблицы.
     * @throws IOException если нет подключения к серверу.
     */
    @FXML
    public void updateTable() throws IOException {
        initTable();
    }

}
