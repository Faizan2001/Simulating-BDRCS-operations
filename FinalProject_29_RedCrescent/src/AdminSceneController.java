/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import ModelClasses.Admin;
import ModelClasses.Events;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faiza
 */
public class AdminSceneController implements Initializable {

    @FXML
    private TextField eventIdTextField;
    @FXML
    private TextField eventTypeTextField;
    @FXML
    private TextField eventTimeTextField;
    @FXML
    private TextField eventDescriptionTextField;
    @FXML
    private DatePicker eventDatePicker;
    @FXML
    private ComboBox<String> eventLocationComboBox;

    private ArrayList<Events> allEventsArrayList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        eventLocationComboBox.getItems().addAll("Barishal", "Chattogram", "Dhaka", "Khulna", "Rajshahi", "Rangpur", "Mymensingh", "Sylhet");
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
    private void addNewEventOnClick(ActionEvent event) throws IOException {

        try {

            allEventsArrayList = new ArrayList<>();
            boolean eventIdExists = false;
            Integer newEventId = Integer.valueOf(eventIdTextField.getText());

            // Verify if Event exists
            
            if (checkFileExists("Events.bin") && checkFileHasObjects("Events.bin")) {
                allEventsArrayList = Admin.readAndReturnEventsArrayList();
                eventIdExists = allEventsArrayList.stream()
                        .anyMatch(existingEvent -> existingEvent.getEventId().equals(newEventId));

                if (eventIdExists) {
                    // Show alert that the event ID already exists
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("An event with the same ID already exists.");
                    alert.showAndWait();

                }
            }

            if (!eventIdExists) {

                if (Admin.makeEvent(Integer.valueOf(eventIdTextField.getText()), eventTypeTextField.getText(), eventTimeTextField.getText(), eventDescriptionTextField.getText(), eventDatePicker.getValue(), eventLocationComboBox.getValue())) {

                    // Show successful alert and otherwise.
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText(null);
                    alert.setContentText("Created new event Successfully.");

                    alert.showAndWait();

                } else {

                    // Show failed alert.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed!");
                    alert.setHeaderText(null);
                    alert.setContentText("Event Creation Failed.");

                    alert.showAndWait();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signOutBtnOnClick(ActionEvent event) throws IOException {
        Parent loginScene = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));

        Scene scene = new Scene(loginScene);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.show();

        // Close the current stage (i.e., the stage associated with the button)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}
