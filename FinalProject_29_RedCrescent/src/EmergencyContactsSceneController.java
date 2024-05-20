
import ModelClasses.Beneficiary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EmergencyContactsSceneController implements Initializable {

    @FXML
    private TableView<String[]> emergencyContactsTableView;
    @FXML
    private TableColumn<String[], String> serviceNameTableColumn;
    @FXML
    private TableColumn<String[], String> cityTableColumn;
    @FXML
    private Button goBackBtnFxID;
    @FXML
    private TableColumn<String[], String> telephoneNoTableCOlumn;

    private static Beneficiary storedBeneficiary;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
        cityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
        telephoneNoTableCOlumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));

        populateTableView();
    }

    private void populateTableView() {
        File file = new File("EmergencyContacts.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Check if the contact's city matches the beneficiary's location
                if (data.length >= 3 && data[1].equalsIgnoreCase(storedBeneficiary.getLocation())) {
                    emergencyContactsTableView.getItems().add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void passBenefInstanceToECScene(Beneficiary b) {
        storedBeneficiary = b;
    }

    @FXML
    private void goBackBtnOnClick(ActionEvent event) {

        Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "BeneficiaryScene.fxml");

    }

}
