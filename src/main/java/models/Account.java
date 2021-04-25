package main.java.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Модель аккаунта
 */
public class Account {

    /**
     * Логин.
     */
    private final StringProperty login;
    /**
     * Пароль.
     */
    private final StringProperty password;

    public Account() {
        this(null, null);
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param login - логин
     * @param password - пароль
     */
    public Account(String login, String password) {
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    /**
     * И далее идут геттеры и сеттеры, не отчающиеся от тех, которые присутствуют в сервере.
     */

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(DigestUtils.shaHex(password));
    }
}
