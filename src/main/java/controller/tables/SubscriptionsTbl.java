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
import main.java.controller.Subscriptions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsTbl extends AbstractController {

    @FXML
    private TableView<Subscriptions> subscriptionsTable;

    @FXML private TableColumn<Subscriptions, String> id;
    @FXML private TableColumn<Subscriptions, String> client;
    @FXML private TableColumn<Subscriptions, String> activity;
    @FXML private TableColumn<Subscriptions, String> date;
    @FXML private TableColumn<Subscriptions, String> price;


    public void initialize() throws IOException {
        initTable();
    }


    private void initTable() throws IOException {

        String sUrl = "http://localhost:8080/subscriptions";
        URL url = new URL(sUrl);
        URLConnection request = url.openConnection();
        request.connect();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray ArrayOfSubscriptions = root.getAsJsonArray();


        List<Subscriptions> subscriptions = new ArrayList<>();
        for (JsonElement subscriptionsElement : ArrayOfSubscriptions) {
            JsonObject subscriptionsObject = subscriptionsElement.getAsJsonObject();

            String id = subscriptionsObject.get("id").getAsString();
            String client = subscriptionsObject.get("client").getAsJsonObject().get("name").getAsString();
            String activity = subscriptionsObject.get("activity").getAsJsonObject().get("name").getAsString();
            String date = subscriptionsObject.get("date").getAsString();
            String price = subscriptionsObject.get("price").getAsString();

            Subscriptions subscription = new Subscriptions(id, client, activity, date, price);
            subscriptions.add(subscription);

        }

        ObservableList observableList = FXCollections.observableList(subscriptions);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        client.setCellValueFactory(new PropertyValueFactory<>("client"));
        activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        subscriptionsTable.setItems(observableList);

    }
}
