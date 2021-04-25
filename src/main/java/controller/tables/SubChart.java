package main.java.controller.tables;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubChart {

    /**
     * Диаграмма.
     */
    @FXML
    private BarChart<String, Integer> SChart;
    @FXML
    private Label act;
    @FXML
    private Label val;


    /**
     * Открытие окна с диаграммой.
     */
    public static void showChart() {
        try {

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(SubChart.class.getResource("/views/Chart.fxml"));
            stage.setScene(new Scene(loader.load()));

            stage.show();


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Выход из окна.
     */
    @FXML
    private void handleExit() {
        SChart.getScene().getWindow().hide();
    }

    /**
     * Заполнение диаграммы и максимального значения.
     * @throws IOException если нет подключения к серверу.
     */
    public void initialize() throws IOException {
        String sUrl = "http://localhost:8080/subscriptions";
        URL url = new URL(sUrl);
        URLConnection request = url.openConnection();
        request.connect();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray ArrayOfSubscriptions = root.getAsJsonArray();

        List<String> activities = new ArrayList<>();
        for (JsonElement subscriptionsElement : ArrayOfSubscriptions) {
            JsonObject subscriptionsObject = subscriptionsElement.getAsJsonObject();

            String activity = subscriptionsObject.get("activity").getAsJsonObject().get("name").getAsString();

            activities.add(activity);
        }

        Map<String, Long> map = activities.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        XYChart.Series series = new XYChart.Series<>();

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            String tmpString = entry.getKey();
            Long tmpValue = entry.getValue();
            XYChart.Data<String, Number> d = new XYChart.Data<>(tmpString, tmpValue);
            series.getData().add(d);
        }
        String maxKey = Collections.max(map.keySet()) + ":  ";
        Long maxValue = Collections.max(map.values());
        act.setText(maxKey);
        val.setText(maxValue.toString());

        SChart.getData().addAll(series);
        SChart.setLegendVisible(false);

    }

}
