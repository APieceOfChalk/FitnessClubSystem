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
import main.java.controller.Classes;
import main.java.utils.RestApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ClassesTbl extends AbstractController {

    @FXML
    private TableView<Classes> classesTable;
    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Classes, String> id;
    @FXML
    private TableColumn<Classes, String> name;
    @FXML
    private TableColumn<Classes, String> trainerId;
    @FXML
    private TableColumn<Classes, String> areaId;

    private RestApi myApiSession = new RestApi();

    public void initialize() throws IOException {
        initTable();
    }

    @FXML
    private void handleDeleteAction() throws IOException {
        int selectedIndex = classesTable.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            Classes currentClass = classesTable.getItems().get(selectedIndex);
            if (myApiSession.deleteClass(currentClass)) {
                initTable();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Занятия не выбраны");
            alert.setContentText("Пожалуйста выберите занятие");

            alert.showAndWait();
        }
    }

    private void initTable() throws IOException {

        String sUrl = "http://localhost:8080/activities";
        URL url = new URL(sUrl);
        URLConnection request = url.openConnection();
        request.connect();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray ArrayOfclasses = root.getAsJsonArray();


        List<Classes> classes = new ArrayList<>();
        for (JsonElement classesElement : ArrayOfclasses) {
            JsonObject classesObject = classesElement.getAsJsonObject();

            String id = classesObject.get("id").getAsString();
            String name = classesObject.get("name").getAsString();
            String areaId = classesObject.get("area").getAsJsonObject().get("name").getAsString();
            String trainerId = classesObject.get("trainer").getAsJsonObject().get("name").getAsString();

            Classes class_ = new Classes(id, name, areaId, trainerId);
            classes.add(class_);

        }

        ObservableList observableList = FXCollections.observableList(classes);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        areaId.setCellValueFactory(new PropertyValueFactory<>("areaId"));
        trainerId.setCellValueFactory(new PropertyValueFactory<>("trainerId"));


        FilteredList<Classes> filteredData = new FilteredList<>(observableList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                } else if (String.valueOf(myObject.getTrainerId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(myObject.getAreaId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(myObject.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }


                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Classes> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(classesTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        classesTable.setItems(sortedData);

    }
}
