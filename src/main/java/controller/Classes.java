package main.java.controller;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import main.java.models.ApiModel;

import java.util.HashMap;
import java.util.Map;

public class Classes implements ApiModel {

    SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty areaId;
    SimpleStringProperty trainerId;

    public Classes(String id, String name, String areaId, String trainerId) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.areaId = new SimpleStringProperty (areaId);
        this.trainerId = new SimpleStringProperty(trainerId);
    }

    public String getId() { return id.get(); }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getAreaId() {
        return areaId.get();
    }

    public void setAreaId(String areaId) {
        this.areaId = new SimpleStringProperty (areaId);
    }

    public String getTrainerId() {
        return trainerId.get();
    }

    public void setTrainerId(String trainerId) {
        new SimpleStringProperty(trainerId);
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name.get());
        map.put("area", areaId.get());
        map.put("trainer", trainerId.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
