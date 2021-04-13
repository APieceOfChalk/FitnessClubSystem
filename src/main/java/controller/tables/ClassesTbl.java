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
import main.java.controller.Classes;

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

    @FXML private TableColumn<Classes, String> id;
    @FXML private TableColumn<Classes, String> name;
    @FXML private TableColumn<Classes, String> trainerId;
    @FXML private TableColumn<Classes, String> areaId;

    public void initialize() throws IOException {
        initTable();
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
            String areaId = classesObject.get("area").getAsJsonObject().get("id").getAsString();
            String trainerId = classesObject.get("trainer").getAsJsonObject().get("id").getAsString();

            System.out.println(areaId);
            System.out.println(trainerId);

            Classes class_ = new Classes(id, name, areaId, trainerId);
            classes.add(class_);

        }

        ObservableList observableList = FXCollections.observableList(classes);
        System.out.println(observableList);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        areaId.setCellValueFactory(new PropertyValueFactory<>("areaId"));
        trainerId.setCellValueFactory(new PropertyValueFactory<>("trainerId"));

        classesTable.setItems(observableList);

    }

}
