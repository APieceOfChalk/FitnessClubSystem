package main.java.controller.tables;

import com.google.gson.*;
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
import main.java.controller.Trainers;
import main.java.controller.popups.ClientsAdd;
import main.java.controller.popups.TrainersAdd;
import main.java.controller.popups.TrainersEdit;
import main.java.utils.RestApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class TrainersTbl extends AbstractController {

    private RestApi myApiSession = new RestApi();

    @FXML
    private TableView<Trainers> trainersTable;
    @FXML
    private TextField searchField;

    @FXML private TableColumn<Trainers, String> id;
    @FXML private TableColumn<Trainers, String> name;
    @FXML private TableColumn<Trainers, String> passport;
    @FXML private TableColumn<Trainers, String> phone;
    @FXML private TableColumn<Trainers, String> address;


    public void initialize() throws IOException {
        initTable();
    }

    @FXML
    private void handleNewTrainer() {
        TrainersAdd.showAddView();
    }

    @FXML
    private void handleDeleteAction() throws IOException {
        int selectedIndex = trainersTable.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        if (selectedIndex >= 0) {
            Trainers currentTrainer = trainersTable.getItems().get(selectedIndex);
            if (myApiSession.deleteTrainer(currentTrainer)) {
                initTable();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Тренер не выбран");
            alert.setContentText("Пожалуйста выберите тренера");

            alert.showAndWait();
        }
    }

    @FXML
    private void editTrainersData() {
        int selectedIndex = trainersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Trainers buf = trainersTable.getItems().get(selectedIndex);
            TrainersEdit.showEditView(buf);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Тренеры не выбраны");
            alert.setContentText("Пожалуйста выберите тренера");

            alert.showAndWait();
        }
    }

    private void initTable() throws IOException {

        String sUrl = "http://localhost:8080/trainers";
        URL url = new URL(sUrl);
        URLConnection request = url.openConnection();
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

        ObservableList observableList = FXCollections.observableList(trainers);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        FilteredList<Trainers> filteredData = new FilteredList<>(observableList, p -> true);

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

        SortedList<Trainers> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(trainersTable.comparatorProperty());
        trainersTable.setItems(sortedData);

    }

    @FXML
    public void updateTable() throws IOException {
        initTable();
    }

}

