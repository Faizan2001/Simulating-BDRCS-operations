/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import ModelClasses.Beneficiary;
import ModelClasses.Volunteer;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author faiza
 */
public class LoginSceneController implements Initializable {

    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private BorderPane borderPanefxId;

    private String selectedRole;
    @FXML
    private Button loginBtnFxID;
    @FXML
    private TextField IdTextField;

    private Event storeEvent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userTypeComboBox.getItems().addAll("Volunteer", "Admin (Event Creation)", "Beneficiary", "Blood Donor", "Blood Collector", "Admin", "Patient"); //"Admin", "Patient", "Blood Donor", "Blood Collector", "General Public", "Partner Organization"

    }

    private static boolean checkFileExists(String fileName) {
        return new java.io.File(fileName).exists();
    }

    private static boolean checkFileHasObjects(String fileName) {
        boolean hasObjects = false;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            // Attempt to read an object from the file
            Object obj = inputStream.readObject();
            hasObjects = true;
        } catch (EOFException e) {
            // The end of file has been reached (no more objects)
        } catch (ClassNotFoundException | IOException e) {
            // Error reading the object or file
            e.printStackTrace();
        }

        return hasObjects;
    }

    @FXML
    private void loginBtnOnClick(ActionEvent event) throws IOException {

        if (selectedRole != null && selectedRole.equals("Volunteer")) {

            if (!(IdTextField.getText().isEmpty() && passwordTextField.getText().isEmpty())) {

                if (checkFileExists("Volunteer.bin") && checkFileHasObjects("Volunteer.bin")) {

                    // Check credentials for Volunteer
                    try {

                        Volunteer loggedInVolunteer = searchVolunteer(Integer.valueOf(IdTextField.getText()), passwordTextField.getText());

                        if (loggedInVolunteer != null) {
                            //Pass data, which switches to Volunteer Screen
                            DashboardSceneController.passDataToDashboard("Volunteer");
                            DashboardSceneController.passVolunteerInstance(loggedInVolunteer);
                            // Proceed to load the dashboard scene and pass the volunteer instance

                            switchToDashboardStage(event);

                        } else {

                            //Wrong credentials
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setHeaderText(null);
                            alert.setContentText("Volunteer verification failed.");

                            alert.showAndWait();

                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace(); // Handle this appropriately
                    }

                } else {
                    // No Beneficiary File found / File has no Objects in it
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No Volunteers detected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Register as a Volunteer to login");

                    alert.showAndWait();
                }

            } else {
                // Empty credentials fields
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No input detected");
                alert.setHeaderText(null);
                alert.setContentText("Please fill out all fields to Login");

                alert.showAndWait();

            }

        } else if (selectedRole != null && selectedRole.equals("Beneficiary")) {

            //Pass data, which switches to Beneficiary Screen
            // Check credentials for Volunteer
            if (!(IdTextField.getText().isEmpty() && passwordTextField.getText().isEmpty())) {

                if (checkFileExists("Beneficiary.bin") && checkFileHasObjects("Beneficiary.bin")) {

                    try {
                        Beneficiary loggedInBeneficiary = searchBeneficiary(Integer.valueOf(IdTextField.getText()), passwordTextField.getText());

                        if (loggedInBeneficiary != null) {
                            //Pass data, which switches to Volunteer Screen
                            DashboardSceneController.passDataToDashboard("Beneficiary");
                            DashboardSceneController.passBeneficiaryInstance(loggedInBeneficiary);
                            // Proceed to load the dashboard scene and pass the volunteer instance

                            switchToDashboardStage(event);

                        } else {

                            //Wrong credentials
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setHeaderText(null);
                            alert.setContentText("Beneficiary verification failed.");

                            alert.showAndWait();

                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace(); // Handle this appropriately
                    }

                } else {
                    // No Beneficiary File found/ File has no Objects in it
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No Beneficiaries detected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Register as a Beneficiary to login");

                    alert.showAndWait();
                }

            } else {
                // Empty credentials fields
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No input detected");
                alert.setHeaderText(null);
                alert.setContentText("Please fill out all fields to Login");

                alert.showAndWait();

            }

        } else if (selectedRole != null && selectedRole.equals("Admin (Event Creation)")) {

            //Pass data, which switches to Admin Screen
            DashboardSceneController.passDataToDashboard("Admin (Event Creation)");
            switchToDashboardStage(event);

        } else if (selectedRole != null && selectedRole.equals("Blood Donor")) {

            //Pass data, which switches to Blood Donor Screen
            DashboardSceneController.passDataToDashboard("Blood Donor");
            switchToDashboardStage(event);

        } else if (selectedRole != null && selectedRole.equals("Blood Collector")) {

            //Pass data, which switches to Blood Donor Screen
            DashboardSceneController.passDataToDashboard("Blood Collector");
            switchToDashboardStage(event);
        } else if (selectedRole != null && selectedRole.equals("Admin")) {
            //Pass data, which switches to Blood Donor Screen
            DashboardSceneController.passDataToDashboard("Admin");
            switchToDashboardStage(event);

        } else if (selectedRole != null && selectedRole.equals("Patient")) {
            //Pass data, which switches to Blood Donor Screen
            DashboardSceneController.passDataToDashboard("Patient");
            switchToDashboardStage(event);

        }

    }

    public void switchToDashboardStage(Event storeEvent) throws IOException {

        //Dashboard in a different Stage
        Parent volScene = FXMLLoader.load(getClass().getResource("DashboardScene.fxml"));

        Scene scene = new Scene(volScene);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.show();

        // Close the current stage (i.e., the stage associated with the button)
        Stage currentStage = (Stage) ((Node) storeEvent.getSource()).getScene().getWindow();
        currentStage.close();

    }

    @FXML
    private void registerBtnOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registrationScene.fxml"));
        Parent root = loader.load();

        RegistrationSceneController registrationSceneController = loader.getController();
        registrationSceneController.setLoginSceneBorderPane(borderPanefxId);

        borderPanefxId.setCenter(root);

    }

    @FXML
    private void userTypeComboBoxOnClick(ActionEvent event) {
        selectedRole = userTypeComboBox.getSelectionModel().getSelectedItem();
        if (selectedRole != null && selectedRole.equals("Beneficiary") || selectedRole.equals("Volunteer") || selectedRole.equals("Admin (Event Creation)") || selectedRole.equals("Blood Donor") || selectedRole.equals("Blood Collector") || selectedRole.equals("Admin") || selectedRole.equals("Patient")) {
            loginBtnFxID.setDisable(false);
        }
    }

    private Volunteer searchVolunteer(Integer id, String password) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Volunteer.bin"))) {
            Volunteer volunteer;
            while (true) {
                try {
                    volunteer = (Volunteer) ois.readObject();
                    if (volunteer == null) {
                        break; // Reached end of file
                    }
                    if (Objects.equals(volunteer.getId(), id) && volunteer.getPassword().equals(password)) {
                        System.out.println(volunteer.toString());
                        return volunteer; // Found the volunteer
                    }
                } catch (EOFException e) {
                    break; // Reached end of file
                }
            }
        }
        return null; // Volunteer not found
    }

    private Beneficiary searchBeneficiary(Integer id, String password) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Beneficiary.bin"))) {
            Beneficiary beneficiary;
            while (true) {
                try {
                    beneficiary = (Beneficiary) ois.readObject();
                    if (beneficiary == null) {
                        break; // Reached end of file
                    }
                    if (Objects.equals(beneficiary.getId(), id) && beneficiary.getPassword().equals(password)) {
                        System.out.println("Logged in Beneficiary " + beneficiary.toString());
                        return beneficiary; // Found the beneficiary
                    }
                } catch (EOFException e) {
                    break; // Reached end of file
                }
            }
        }
        return null; // Beneficiary not found
    }

}
