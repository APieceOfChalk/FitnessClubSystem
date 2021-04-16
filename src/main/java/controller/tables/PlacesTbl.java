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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.controller.AbstractController;
import main.java.controller.Places;
import main.java.controller.popups.PlacesEdit;
import main.java.utils.RestApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class PlacesTbl extends AbstractController {

    private RestApi myApiSession = new RestApi();

    @FXML
    private TableView<Places> placesTable;
    @FXML
    private TextField searchField;


    @FXML private TableColumn<Places, String> id;
    @FXML private TableColumn<Places, String> name;


    public void initialize() throws IOException {
        initTable();
    }

    @FXML
    public void addNew() {
        PlacesEdit.showView();
    }


    @FXML
    private void handleDeleteAction() throws IOException {
        int selectedIndex = placesTable.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            Places currentPlace = placesTable.getItems().get(selectedIndex);
            if (myApiSession.deletePlace(currentPlace)) {
                initTable();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Залы не выбраны");
            alert.setContentText("Пожалуйста выберите зал");

            alert.showAndWait();
        }
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


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        FilteredList<Places> filteredData = new FilteredList<>(observableList, p -> true);

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

        SortedList<Places> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(placesTable.comparatorProperty());
        placesTable.setItems(sortedData);

    }



}