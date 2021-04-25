package main.java.controller;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import main.java.models.ApiModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Модель залов.
 */
public class Places implements ApiModel {

    /**
     * Id.
     */
    SimpleStringProperty id;
    /**
     * Название зала.
     */
    SimpleStringProperty name;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param id - id
     * @param name - название
     */
    public Places(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Places() {
    }

    /**
     * И далее идут геттеры и сеттеры, не отчающиеся от тех, которые присутствуют в сервере.
     */

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

    /**
     * Переводит все переданные параметры в map и переводит map в json.
     * @return map в Json
     */
    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("name", name.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
