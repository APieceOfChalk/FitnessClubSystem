package main.java.controller;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import main.java.controller.tables.dto.*;
import main.java.models.ApiModel;


/**
 * Модель абонементов.
 */
public class Subscriptions implements ApiModel {

    /**
     * Id.
     */
    SimpleStringProperty id;
    /**
     * Клиент.
     */
    SimpleStringProperty client;
    /**
     * Занятие.
     */
    SimpleStringProperty activity;
    /**
     * Срок.
     */
    SimpleStringProperty date;
    /**
     * Цена.
     */
    SimpleStringProperty price;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param id - id
     * @param client - клиент
     * @param activity - занятие
     * @param date - срок
     * @param price - цена
     */
    public Subscriptions(String id, String client, String activity, String date, String price) {
        this.id = new SimpleStringProperty(id);
        this.client = new SimpleStringProperty(client);
        this.activity = new SimpleStringProperty (activity);
        this.date = new SimpleStringProperty(date);
        this.price = new SimpleStringProperty(price);
    }

    public Subscriptions() {

    }

    /**
     * И далее идут геттеры и сеттеры, не отчающиеся от тех, которые присутствуют в сервере.
     */

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
       this.date = new SimpleStringProperty(date);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price = new SimpleStringProperty(price);
    }


    /**
     * Переводит все переданные параметры в json.
     * @return объект в Json
     */
    @Override
    public String toJson() {
        main.java.controller.tables.dto.client client1 = new client(client.get());
        main.java.controller.tables.dto.activity activity1 = new activity(activity.get());
        SubscriptionsNested nested = new SubscriptionsNested(client1, activity1, date.get(), price.get());
        Gson gson = new Gson();

        return gson.toJson(nested);
    }

}
