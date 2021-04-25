package main.java.utils;

/**
 * Класс перечисления страниц.
 */
public enum Menu {
    Home("Главная страница"),
    Subscriptions("Абонементы"),
    Clients("Клианты клуба"),
    Trainers("Тренеры клуба"),
    Classes("Занятия клуба"),
    Places("Залы клуба"),
    Author("Об авторе");

    private String title;

    /**
     * Конструктор
     * @param title - название страницы
     */
    Menu(String title) {
        this.title = title;
    }

    /**
     * Функция получения значения поля {@link Menu#title}
     * @return название зтраницы
     */
    public String getTitle() {
        return title;
    }

    /**
     * Функция получения страницы fxml
     * @return страница fxml
     */
    public String getFxml() {
        return String.format("%s.fxml", name());
    }
}
