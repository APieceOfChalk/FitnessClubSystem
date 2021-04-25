package main.java.controller.common;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Диалоговое окно о выходе из приложения.
 */
public class Dialog {

    @FXML
    private Label title;
    @FXML
    private Label message;

    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;

    private Stage stage;

    private ActionListener actionListener;

    /**
     * Выход из окна.
     */
    @FXML
    private void cancel() {
        okBtn.getScene().getWindow().hide();
    }

    /**
     * Открытие окна.
     */
    public void show() {
        stage.show();
    }

    /**
     * Нажатие кнопок.
     */
    private void attachEvents() {
        cancelBtn.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                if(cancelBtn.isFocused()) {
                    cancel();
                }

                if(okBtn.isFocused()) {
                    okAction();
                }
            }
        });
    }

    /**
     * Выход из приложения.
     */
    @FXML
    private void okAction() {
        if(null != actionListener) {
            cancel();
            actionListener.doAction();
        }
    }

    /**
     * Создание диалога.
     */
    public static class DialogBuilder {

        private String title;
        private String message;

        private ActionListener okActionListener;

        private DialogBuilder() {
        }

        public DialogBuilder okActionListener(ActionListener okActionListener) {
            this.okActionListener = okActionListener;
            return this;
        }

        public DialogBuilder message(String message) {
            this.message = message;
            return this;
        }

        public DialogBuilder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * Показ окна.
         */
        public Dialog build() {
            try {

                Stage stage = new Stage(StageStyle.UNDECORATED);
                FXMLLoader loader = new FXMLLoader(Dialog.class.getResource("/views/common/Dialog.fxml"));
                Parent view = loader.load();
                stage.setScene(new Scene(view));

                Dialog controller = loader.getController();
                controller.stage = stage;

                controller.title.setText(this.title);
                controller.message.setText(this.message);
                controller.actionListener = okActionListener;

                if (null == okActionListener) {
                    controller.okBtn.setVisible(false);
                    controller.cancelBtn.setText("ОТМЕНА");
                }
                controller.attachEvents();

                return controller;

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        public static DialogBuilder builder() {
            return new DialogBuilder();
        }
    }

    public static interface ActionListener {
        void doAction();
    }
}
