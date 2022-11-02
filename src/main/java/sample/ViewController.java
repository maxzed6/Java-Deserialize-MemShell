package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.exploit.PayloadCreate;
import sample.utils.ShowUtils;

public class ViewController {
    @FXML
    private ChoiceBox<String> gadgetBox;

    @FXML
    private Button exploitButton;

    @FXML
    private ChoiceBox<String> encodeBox;

    @FXML
    private ChoiceBox<String> memShellBox;

    @FXML
    private TextArea showContentArea;

    @FXML
    private TextArea showExpArea;

    @FXML
    private TextArea showMemShellContentArea;

    @FXML
    private Label gadgetLabel;

    @FXML
    private Label encodeLabel;

    @FXML
    private Label memShellLabel;

    @FXML
    void exploit(MouseEvent event) {
        if (gadgetBox.getSelectionModel().getSelectedItem() == null || memShellBox.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Choice");
            alert.setContentText("You chose fart!");
            alert.showAndWait();
            return;
        }
        String payload = PayloadCreate.expCreate(gadgetBox.getSelectionModel().getSelectedItem(), encodeBox.getSelectionModel().getSelectedItem(), memShellBox.getSelectionModel().getSelectedItem());
        showExpArea.setText(payload);
    }
    @FXML
    void initialize() {
        final String[] gadgetItems = {"cc4", "cc7", "cc2", "cc5", "cc6", "rome1.0", "rome1.7", "commons-beanutils"};
        gadgetBox.getItems().addAll(FXCollections.observableArrayList(gadgetItems));

        final String[] encodeItems = {"base64", "URLEncode", "hex"};
        encodeBox.getItems().addAll(FXCollections.observableArrayList(encodeItems));
        encodeBox.getSelectionModel().selectFirst();

        final String[] memShellItems = {"Tomcat-Filter", "Spring-Controller", "Interceptor", "Listener", "Tomcat-Servlet", "Upgrade", "Valve"};
        memShellBox.getItems().addAll(FXCollections.observableArrayList(memShellItems));

        gadgetBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                showContentArea.setText(ShowUtils.showContent(gadgetItems[newValue.intValue()]));
            }
        });

        memShellBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                showMemShellContentArea.setText(ShowUtils.showMemShellUse(memShellItems[newValue.intValue()]));
            }
        });
//        assert gadgetBox != null : "fx:id=\"gadgetBox\" was not injected: check your FXML file 'sample.fxml'.";
    }
}
