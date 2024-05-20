/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import ModelClasses.Beneficiary;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faiza
 */
public class BloodBankServicesSceneController implements Initializable {

    @FXML
    private PieChart bloodInventoryPieChart;
    @FXML
    private ComboBox<String> bloodGroupComboBox;
    @FXML
    private ComboBox<Integer> amountNeededComboBox;

    private static Beneficiary storedBeneficiary;
    @FXML
    private Button goBackBtnFxID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloodGroupComboBox.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        amountNeededComboBox.getItems().addAll(1, 2, 3, 4, 5);
        populateBloodInventoryPieChart();

    }

    @FXML
    private void bloodRequestOnClick(ActionEvent event) {
        String bloodType = bloodGroupComboBox.getValue();
        Integer amount = amountNeededComboBox.getValue();

        if (bloodType == null || amount == null) {
            // Show an error message indicating that both fields are required
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select both Blood Type and Amount as needed.");
            alert.showAndWait();
        } else if (amount <= 0) {
            // Show an error message indicating that amount should be greater than 0
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Amount should be greater than 0.");
            alert.showAndWait();
        } else {
            String request = storedBeneficiary.getName() + " requests Blood type: " + bloodType + ", Amount: " + amount + " Unit(s)\n";
            writeBloodRequestToFile(request);
        }
    }

    private void writeBloodRequestToFile(String request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BloodRequestsByBeneficiary.txt", true))) {
            writer.write(request);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Submitted request to file - BloodRequestsbyBeneficiary");

            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed!");
            alert.setHeaderText(null);
            alert.setContentText("Failed to submit request");
        }
    }

    private void populateBloodInventoryPieChart() {
        try (BufferedReader reader = new BufferedReader(new FileReader("BloodBankInventoryDataForPieChart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String bloodType = parts[0];
                Integer count = Integer.valueOf(parts[1]);
                PieChart.Data data = new PieChart.Data(bloodType, count);
                bloodInventoryPieChart.getData().add(data);

                // Set the data label for the pie chart data
                data.setName(bloodType + " : " + count);
                
             
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void passBeneficiary(Beneficiary beneficiary) {
        storedBeneficiary = beneficiary;
    }

    @FXML
    private void goBackBtnOnClick(ActionEvent event) {

        Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "BeneficiaryScene.fxml");
    }

}
