package main.java.controller;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import main.java.models.ApiModel;

import java.util.HashMap;
import java.util.Map;

public class Subscriptions implements ApiModel {

    SimpleStringProperty id;
    SimpleStringProperty client;
    SimpleStringProperty activity;
    SimpleStringProperty date;
    SimpleStringProperty price;

    public Subscriptions(String id, String client, String activity, String date, String price) {
        this.id = new SimpleStringProperty(id);
        this.client = new SimpleStringProperty(client);
        this.activity = new SimpleStringProperty (activity);
        this.date = new SimpleStringProperty(date);
        this.price = new SimpleStringProperty(price);
    }

    public String getId() { return id.get(); }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getClient() {
        return client.get();
    }

    public void setClient(String client) {
        this.client = new SimpleStringProperty(client);
    }

    public String getActivity() {
        return activity.get();
    }

    public void setActivity(String activity) {
        this.activity = new SimpleStringProperty (activity);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        new SimpleStringProperty(date);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        new SimpleStringProperty(price);
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("client", client.get());
        map.put("activity", activity.get());
        map.put("date", date.get());
        map.put("price", price.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }

}
