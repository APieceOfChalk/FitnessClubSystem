package main.java.utils;

import main.java.controller.*;


public class RestApi {

    private static final String ServerURL = "http://localhost:8080/";


    public void createPlace(Places place) {
        HttpClass.PostRequest(ServerURL + "areas/", place.toJson());
    }

    public void updatePlace(Places place) {
        String id = place.getId();
        String jsonString = place.toJson();
        HttpClass.PutRequest(ServerURL + "areas/" + id, jsonString);
    }

    public boolean deletePlace(Places place) {
        String id = place.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "areas/" + id);
    }

    public void createClass(Classes class_) {
        HttpClass.PostRequest(ServerURL + "activities/", class_.toJson());
    }

    public void updateClass(Classes class_) {
        String id = class_.getId();
        String jsonString = class_.toJson();
        HttpClass.PutRequest(ServerURL + "activities/" + id, jsonString);
    }

    public boolean deleteClass(Classes class_) {
        String id = class_.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "activities/" + id);
    }

    public void createClient(Clients client) {
        HttpClass.PostRequest(ServerURL + "clients/", client.toJson());
    }

    public void updateClient(Clients client) {
        String id = client.getId();
        String jsonString = client.toJson();
        HttpClass.PutRequest(ServerURL + "clients/" + id, jsonString);
    }

    public boolean deleteClient(Clients client) {
        String id = client.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "clients/" + id);
    }

    public void createTrainer(Trainers trainer) {
        HttpClass.PostRequest(ServerURL + "trainers/", trainer.toJson());
    }

    public void updateTrainer(Trainers trainer) {
        String id = trainer.getId();
        String jsonString = trainer.toJson();
        HttpClass.PutRequest(ServerURL + "trainers/" + id, jsonString);
    }

    public boolean deleteTrainer(Trainers trainer) {
        String id = trainer.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "trainers/" + id);
    }

    public void createSubscription(Subscriptions subscription) {
        HttpClass.PostRequest(ServerURL + "subscriptions/", subscription.toJson());
    }

    public void updateSubscription(Subscriptions subscription) {
        String id = subscription.getId();
        String jsonString = subscription.toJson();
        HttpClass.PutRequest(ServerURL + "subscriptions/" + id, jsonString);
    }

    public boolean deleteSubscription(Subscriptions subscription) {
        String id = subscription.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "subscriptions/" + id);
    }
}
