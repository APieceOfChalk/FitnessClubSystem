package main.java.controller;


import javafx.beans.property.SimpleStringProperty;

public class Clients {

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
}
