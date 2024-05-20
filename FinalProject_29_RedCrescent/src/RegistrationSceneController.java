/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import ModelClasses.Beneficiary;
import ModelClasses.Volunteer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class // Volunteer and Events Many to Many Relationship.
 *
 * @author faiza
 */
public class RegistrationSceneController implements Initializable {

    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private ComboBox<String> daysAvailableComboBox;
    @FXML
    private ComboBox<String> userTypeRegistrationComboBox;
    @FXML
    private ComboBox<String> locationComboBox;
    @FXML
    private Label daysAvailableLabel;
    @FXML
    private Label locationLabel;

    private String selectedRole;

    private BorderPane loginSceneBorderPane;
    @FXML
    private Button registerBtnFxID;

    public void setLoginSceneBorderPane(BorderPane borderPane) {
        loginSceneBorderPane = borderPane;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daysAvailableComboBox.getItems().addAll("Sun, Tue", "Mon, Wed", "Thu, Fri");
        userTypeRegistrationComboBox.getItems().addAll("Volunteer", "Beneficiary"); //"Admin", "Patient", "Blood Donor", "Blood Collector", "General Public", "Partner Organization"
        locationComboBox.getItems().addAll("Barishal", "Chattogram", "Dhaka", "Khulna", "Rajshahi", "Rangpur", "Mymensingh", "Sylhet");

    }

    private void switchToLoginScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        loginSceneBorderPane.setCenter(root);
    }
    
      private boolean isString(String input) {
        return input.matches("[a-zA-Z]+"); // Only letters allowed
    }

    @FXML
    private void registerBtnOnClick(ActionEvent event) throws IOException {
        selectedRole = userTypeRegistrationComboBox.getSelectionModel().getSelectedItem();

        try {

            if (selectedRole != null && selectedRole.equals("Volunteer")) {

                if (!(idTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || !isString(nameTextField.getText()) || emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || ageTextField.getText().isEmpty() || daysAvailableComboBox.getValue().isEmpty() || locationComboBox.getValue().isEmpty())) {
                    // Static method Volunteer.registerToFIle call
                    if (Volunteer.registerToFile(Integer.valueOf(idTextField.getText()), nameTextField.getText(), emailTextField.getText(), passwordTextField.getText(), Integer.valueOf(ageTextField.getText()), daysAvailableComboBox.getValue(), locationComboBox.getValue(), null, null)) {

                        // Switch back to Login Scene
                        switchToLoginScene(event);

                        // Show successful alert and otherwise.
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText(null);
                        alert.setContentText("Volunteer registration successful! Please enter your new credentials.");

                        alert.showAndWait();

                    } else {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText(null);
                        alert.setContentText("Volunteer registration failed.");

                        alert.showAndWait();

                    }

                } // if block for validation check
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Please make sure all information is valid and filled out");

                    alert.showAndWait();
                }
            } else if (selectedRole != null && selectedRole.equals("Beneficiary")) {
                // NOT (NOT String) = String, true.
                if (!(idTextField.getText().isEmpty() || !isString(nameTextField.getText()) || nameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || ageTextField.getText().isEmpty() || locationComboBox.getValue().isEmpty())) {

                    if (Beneficiary.registerBeneficiaryToFile(Integer.valueOf(idTextField.getText()), nameTextField.getText(), emailTextField.getText(), Integer.valueOf(ageTextField.getText()), passwordTextField.getText(), locationComboBox.getValue(), null)) {

                        // Switch back to Login Scene
                        switchToLoginScene(event);

                        // Show successful alert and otherwise.
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText(null);
                        alert.setContentText("Beneficiary registration successful! Please enter your new credentials.");

                        alert.showAndWait();

                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Please make sure all information is valid and filled out");
                    alert.showAndWait();

                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure all information is valid and filled out");

            alert.showAndWait();
        }

    }

    @FXML
    private void selectUserTypeOnClick(ActionEvent event) {
        selectedRole = userTypeRegistrationComboBox.getSelectionModel().getSelectedItem();
        if (selectedRole != null && selectedRole.equals("Volunteer")) {

            daysAvailableComboBox.setDisable(false);
            daysAvailableLabel.setOpacity(1);
            registerBtnFxID.setDisable(false);

        } else if (selectedRole != null && selectedRole.equals("Beneficiary")) {

            daysAvailableComboBox.setDisable(true);
            daysAvailableLabel.setOpacity(0.3);
            registerBtnFxID.setDisable(false);

        } else {

            registerBtnFxID.setDisable(true);
        }
    }

}
