package main.java.controller;


import javafx.beans.property.SimpleStringProperty;

public class Trainers {

    SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty passport;
    SimpleStringProperty phone;
    SimpleStringProperty address;

    public Trainers(String id, String name, String passport, String phone, String address) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.passport = new SimpleStringProperty (passport);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
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

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        new SimpleStringProperty(address);
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
}
