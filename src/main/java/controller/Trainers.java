package main.java.controller;


public class Trainers extends AbstractController {

    private Integer id;
    private String name;
    private String passport;
    private String phone;
    private String address;


    public Trainers() {}

    public Trainers(Integer id, String name, String passport, String phone, String address, Classes classes) {
        this.id = id;
        this.name = name;
        this.passport = passport;
        this.phone = phone;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
