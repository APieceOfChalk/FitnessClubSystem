package main.java.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.controller.Places;
import main.java.utils.RestApi;



public class PlacesEdit {

    @FXML
    private TextField name;
    @FXML
    private Label title;
    @FXML
    private Label message;

    private RestApi myApiSession = new RestApi();
    private Places place;



    @FXML
    private void handleCancel() {
        name.getScene().getWindow().hide();
    }

    public static void showEditView(Places place) {
        try {

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            FXMLLoader loader = new FXMLLoader(PlacesEdit.class.getResource("/views/popups/PlacesEdit.fxml"));
            stage.setScene(new Scene(loader.load()));

            PlacesEdit controller = loader.getController();
            controller.setPlace(place);


            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            place.setName(name.getText());
            myApiSession.updatePlace(place);
            name.getScene().getWindow().hide();
        }
    }

    public void setPlace(Places place) {
        this.place = place;
        title.setText("Редактировать зал");

        name.setText(place.getName());
    }


    private boolean isInputValid() {
        String errorMessage = "";
        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "Не валидно название зала";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            message.setText(errorMessage);
            return false;
        }
    }

}
