package main.java.controller;


import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import main.java.models.ApiModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Модель тренера.
 */
public class Trainers implements ApiModel {

    /**
     * Id.
     */
    SimpleStringProperty id;
    /**
     * ФИО тренера.
     */
    SimpleStringProperty name;
    /**
     * Паспорт тренера.
     */
    SimpleStringProperty passport;
    /**
     * Телефон тренера.
     */
    SimpleStringProperty phone;
    /**
     * Адрес тренера.
     */
    SimpleStringProperty address;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param id - id
     * @param name - ФИО
     * @param passport - паспорт
     * @param phone - телефон
     * @param address - адрес
     */
    public Trainers(String id, String name, String passport, String phone, String address) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.passport = new SimpleStringProperty (passport);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
    }

    public Trainers() {
    }

    /**
     * И далее идут геттеры и сеттеры, не отчающиеся от тех, которые присутствуют в сервере.
     */

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
        this.phone = new SimpleStringProperty(phone);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    @Override
    public String toString() {
        return "Trainers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport='" + passport + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
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
        map.put("passport", passport.get());
        map.put("phone", phone.get());
        map.put("address", address.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
