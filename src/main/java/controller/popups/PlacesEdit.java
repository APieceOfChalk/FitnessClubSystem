package main.java.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PlacesEdit {

    @FXML
    private TextField name;
    @FXML
    private Label title;
    @FXML
    private Label message;


    @FXML
    private void handleCancel() {
        name.getScene().getWindow().hide();
    }

    public static void showView() {
        try {

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(PlacesEdit.class.getResource("/views/popups/PlacesEdit.fxml"));
            stage.setScene(new Scene(loader.load()));

            PlacesEdit edit = loader.getController();

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
