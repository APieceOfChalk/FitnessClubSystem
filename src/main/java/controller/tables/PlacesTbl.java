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
import main.java.controller.Places;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class PlacesTbl extends AbstractController {

    @FXML
    private TableView<Places> placesTable;

    @FXML private TableColumn<Places, String> id;
    @FXML private TableColumn<Places, String> name;

    public void initialize() throws IOException {
        initTable();
    }


    private void initTable() throws IOException {

        String sUrl = "http://localhost:8080/areas";
        URL url = new URL(sUrl);
        URLConnection request = url.openConnection();
        request.connect();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray ArrayOfPlaces = root.getAsJsonArray();


        List<Places> places = new ArrayList<>();
        for (JsonElement placesElement : ArrayOfPlaces) {
            JsonObject placesObject = placesElement.getAsJsonObject();

            String id = placesObject.get("id").getAsString();
            String name = placesObject.get("name").getAsString();

            Places place = new Places(id, name);
            places.add(place);

        }

        ObservableList observableList = FXCollections.observableList(places);
        System.out.println(observableList);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        placesTable.setItems(observableList);

    }


}
