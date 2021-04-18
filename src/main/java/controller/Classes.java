package main.java.controller;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import main.java.controller.tables.dto.ActivitiesNested;
import main.java.controller.tables.dto.area;
import main.java.controller.tables.dto.trainer;
import main.java.models.ApiModel;


public class Classes implements ApiModel {

    SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty areaId;
    SimpleStringProperty trainerId;

    public Classes(String id, String name, String areaId, String trainerId) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.areaId = new SimpleStringProperty(areaId);
        this.trainerId = new SimpleStringProperty(trainerId);
    }

    public Classes() {
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
        this.areaId = new SimpleStringProperty(areaId);
    }

    public String getTrainerId() {
        return trainerId.get();
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = new SimpleStringProperty(trainerId);
    }


    @Override
    public String toJson() {
        area area = new area(areaId.get());
        trainer trainer = new trainer(trainerId.get());
        ActivitiesNested nested = new ActivitiesNested(name.get(), area, trainer);
        Gson gson = new Gson();

        return gson.toJson(nested);
    }
}
