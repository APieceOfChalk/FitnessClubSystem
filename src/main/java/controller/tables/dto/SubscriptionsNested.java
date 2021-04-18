package main.java.controller.tables.dto;

public class SubscriptionsNested {
    public activity activity;
    public client client;
    public String date;
    public String price;

    public SubscriptionsNested(main.java.controller.tables.dto.client client, main.java.controller.tables.dto.activity activity,
                               String date, String price) {
        this.activity = activity;
        this.client = client;
        this.date = date;
        this.price = price;
    }
}
