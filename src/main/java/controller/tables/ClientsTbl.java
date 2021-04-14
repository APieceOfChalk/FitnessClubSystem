package main.java.controller.tables;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.controller.AbstractController;
import main.java.controller.Clients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ClientsTbl extends AbstractController {

    @FXML
    private TableView<Clients> clientsTable;

    @FXML private TableColumn<Clients, String> id;
    @FXML private TableColumn<Clients, String> name;
    @FXML private TableColumn<Clients, String> passport;
    @FXML private TableColumn<Clients, String> phone;

    public void initialize() throws IOException {
        initTable();
    }


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
        clientsTable.setItems(observableList);

    }

}
