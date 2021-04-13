package main.java.controller;


import javafx.beans.property.SimpleStringProperty;

public class Classes extends AbstractController {

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

}
