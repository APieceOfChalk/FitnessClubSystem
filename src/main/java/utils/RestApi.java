package main.java.utils;

import main.java.controller.*;

/**
 * Класс с запросами.
 */
public class RestApi {

    /**
     * Url, к которой подключаемся
     */
    private static final String ServerURL = "http://localhost:8080/";


    /**
     * Создание зала
     * @param place - зал
     */
    public void createPlace(Places place) {
        HttpClass.PostRequest(ServerURL + "areas/", place.toJson());
    }

    /**
     * Изменение зала
     * @param place - зал
     */
    public void updatePlace(Places place) {
        String id = place.getId();
        String jsonString = place.toJson();
        HttpClass.PutRequest(ServerURL + "areas/" + id, jsonString);
    }

    /**
     * Удаление зала
     * @param place - зал
     * @return в зависимости есть id или нет удаляет зал
     */
    public boolean deletePlace(Places place) {
        String id = place.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "areas/" + id);
    }

    /**
     * Создание занятия
     * @param class_ - занятие
     */
    public void createClass(Classes class_) {
        HttpClass.PostRequest(ServerURL + "activities/", class_.toJson());
    }

    /**
     * Изменение занятия
     * @param class_ - занятие
     */
    public void updateClass(Classes class_) {
        String id = class_.getId();
        String jsonString = class_.toJson();
        HttpClass.PutRequest(ServerURL + "activities/" + id, jsonString);
    }

    /**
     * Удаление занятия
     * @param class_ - занятие
     * @return в зависимости есть id или нет удаляет занятие
     */
    public boolean deleteClass(Classes class_) {
        String id = class_.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "activities/" + id);
    }

    /**
     * Создание клиента
     * @param client - клиент
     */
    public void createClient(Clients client) {
        HttpClass.PostRequest(ServerURL + "clients/", client.toJson());
    }

    /**
     * Изменение клиента
     * @param client - клиент
     */
    public void updateClient(Clients client) {
        String id = client.getId();
        String jsonString = client.toJson();
        HttpClass.PutRequest(ServerURL + "clients/" + id, jsonString);
    }

    /**
     * Удаление клиента
     * @param client - клиент
     * @return в зависимости есть id или нет удаляет клиента
     */
    public boolean deleteClient(Clients client) {
        String id = client.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "clients/" + id);
    }

    /**
     * Создание тренера
     * @param trainer - тренер
     */
    public void createTrainer(Trainers trainer) {
        HttpClass.PostRequest(ServerURL + "trainers/", trainer.toJson());
    }

    /**
     * Изменение тренера
     * @param trainer - тренер
     */
    public void updateTrainer(Trainers trainer) {
        String id = trainer.getId();
        String jsonString = trainer.toJson();
        HttpClass.PutRequest(ServerURL + "trainers/" + id, jsonString);
    }

    /**
     * Удаление тренера
     * @param trainer - тренер
     * @return в зависимости есть id или нет удаляет тренера
     */
    public boolean deleteTrainer(Trainers trainer) {
        String id = trainer.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "trainers/" + id);
    }

    /**
     * Создаие абонемента
     * @param subscription - абонемент
     */
    public void createSubscription(Subscriptions subscription) {
        HttpClass.PostRequest(ServerURL + "subscriptions/", subscription.toJson());
    }

    /**
     * Изменение абонемента
     * @param subscription - абонемент
     */
    public void updateSubscription(Subscriptions subscription) {
        String id = subscription.getId();
        String jsonString = subscription.toJson();
        HttpClass.PutRequest(ServerURL + "subscriptions/" + id, jsonString);
    }

    /**
     * Удаление абонемента
     * @param subscription - абонемент
     * @return в зависимости есть id или нет удаляет абонемент
     */
    public boolean deleteSubscription(Subscriptions subscription) {
        String id = subscription.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "subscriptions/" + id);
    }
}
