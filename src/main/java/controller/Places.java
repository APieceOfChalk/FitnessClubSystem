package main.java.controller;


import javafx.beans.property.SimpleStringProperty;

public class Places {

    SimpleStringProperty id;
    SimpleStringProperty name;

    public Places(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public String toString() {
        return "Places{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
