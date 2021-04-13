package main.java.controller.tables;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.controller.AbstractController;
import main.java.controller.Trainers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class TrainersTbl extends AbstractController {

    @FXML
    private TableView<Trainers> trainersTable;

    @FXML private TableColumn<Trainers, String> id;
    @FXML private TableColumn<Trainers, String> name;
    @FXML private TableColumn<Trainers, String> passport;
    @FXML private TableColumn<Trainers, String> phone;
    @FXML private TableColumn<Trainers, String> address;


    public void initialize() throws IOException {
        initTable();
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
        System.out.println(observableList);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        trainersTable.setItems(observableList);

    }

}

