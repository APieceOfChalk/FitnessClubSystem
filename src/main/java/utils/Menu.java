package main.java.utils;

public enum Menu {
    Home("Главная страница"),
    Subscriptions("Абонементы"),
    Clients("Клианты клуба"),
    Trainers("Тренеры клуба"),
    Classes("Занятия клуба"),
    Places("Залы клуба"),
    Author("Об авторе");

    private String title;

    Menu(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFxml() {
        return String.format("%s.fxml", name());
    }
}
