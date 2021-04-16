package main.java.controller;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import main.java.models.ApiModel;

import java.util.HashMap;
import java.util.Map;

public class Clients implements ApiModel {

    SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty passport;
    SimpleStringProperty phone;

    public Clients(String id, String name, String passport, String phone) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.passport = new SimpleStringProperty (passport);
        this.phone = new SimpleStringProperty(phone);
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

    public String getPassport() {
        return passport.get();
    }

    public void setPassport(String passport) {
        this.passport = new SimpleStringProperty (passport);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        new SimpleStringProperty(phone);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name=" + name +
                ", passport=" + passport +
                ", phone=" + phone +
                '}';
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name.get());
        map.put("passport", passport.get());
        map.put("phone", phone.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
