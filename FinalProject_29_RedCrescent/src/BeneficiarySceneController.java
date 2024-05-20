/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import ModelClasses.Beneficiary;
import ModelClasses.Events;
import ModelClasses.Volunteer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faiza
 */
public class BeneficiarySceneController implements Initializable {

    @FXML
    private Button forumBtnFxID;
    @FXML
    private Button emergencyContactsBtnFxID;
    @FXML
    private Button bloodBankServicesBtnFxID;
    @FXML
    private TableColumn<Events, Integer> eventIdColumn;
    @FXML
    private TableColumn<Events, String> eventTypeColumn;
    @FXML
    private TableColumn<Events, String> eventTimeColumn;
    @FXML
    private TableColumn<Events, LocalDate> eventDateColumn;
    @FXML
    private TableColumn<Events, Integer> volunteersListColumn;
    @FXML
    private Button eventsNearMeBtnFxID;
    @FXML
    private Button viewDetailsBtnFxID;
    @FXML
    private Button signOutBtnFxID;
    @FXML
    private TableView<Events> MyEventsTableView;

    private static Beneficiary storedBeneficiary; // Static variable stores the beneficiary instance, since it is static, it maintains its value unless explicity changed or
                                                                          // Application gets closed.
    @FXML
    private Button deleteEventBtnFxID;
    @FXML
    private TextArea emergencyNoticesTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //   ArrayList<Events> dummyEvents = new ArrayList<>();
        eventIdColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        eventTypeColumn.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        eventTimeColumn.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
        volunteersListColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerList"));

        MyEventsTableView.setItems(FXCollections.observableArrayList(storedBeneficiary.getParticipatedEvents()));
        System.out.println("Stored Beneficiary ArrayList currently:" + storedBeneficiary.getParticipatedEvents());

        // Bind the disable property of the button to the TableView's selectedItemProperty
        deleteEventBtnFxID.disableProperty().bind(MyEventsTableView.getSelectionModel().selectedItemProperty().isNull());

        // Bind the disable property of the Nearby Event Details button to the TableView's selectedItemProperty
        viewDetailsBtnFxID.disableProperty().bind(MyEventsTableView.getSelectionModel().selectedItemProperty().isNull());

        // It's what it says it is (Text File read as Admin is supposed to supply data)
        populateEmergencyNoticesTextArea();

    }

    @FXML
    private void forumBtnOnClick(ActionEvent event) {
        ForumSceneController.passObject(storedBeneficiary);
        Stage stage = (Stage) forumBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "ForumScene.fxml");
    }

    @FXML
    private void emergencyContactsBtnOnClick(ActionEvent event) {
        EmergencyContactsSceneController.passBenefInstanceToECScene(storedBeneficiary);

        Stage stage = (Stage) emergencyContactsBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "emergencyContactsScene.fxml");
    }

    @FXML
    private void bloodBankServicesBtnOnClick(ActionEvent event) {
        
        BloodBankServicesSceneController.passBeneficiary(storedBeneficiary);
        
        Stage stage = (Stage) emergencyContactsBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "BloodBankServicesScene.fxml");
    }

    @FXML
    private void eventsNearMeBtnOnClick(ActionEvent event) {
        try {
            // Load the BeneficiaryNearbyEventsScene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BeneficiaryNearbyEvents.fxml"));
            Parent benefNearbyEventsParent = loader.load();

            // Access the controller and call the onShown method
            BeneficiaryNearbyEventsController benefNearbyEventsController = loader.getController();
            BeneficiaryNearbyEventsController.passBeneficiary(storedBeneficiary);
            benefNearbyEventsController.onShown();

            // Switch to NearbyEventsScene
            Scene benefNearbyEventsScene = new Scene(benefNearbyEventsParent);
            Stage stage = (Stage) eventsNearMeBtnFxID.getScene().getWindow();
            stage.setScene(benefNearbyEventsScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewDetailsBtnOnClick(ActionEvent event) {
        Events selectedEvent = MyEventsTableView.getSelectionModel().getSelectedItem();
        ViewEventDetailsSceneController.passEvent("BeneficiaryDashboard", selectedEvent);

        Stage stage = (Stage) viewDetailsBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "ViewEventDetailsScene.fxml");
    }

    @FXML
    private void signOutOnClick(ActionEvent event) throws IOException {
        Parent loginScene = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));

        Scene scene = new Scene(loginScene);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.show();

        // Close the current stage (i.e., the stage associated with the button)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public static void passBeneficiaryToBeneficiaryDashboard(Beneficiary b) {
        storedBeneficiary = b;
    }

    @FXML
    private void deleteEventBtnOnClick(ActionEvent event) {

        Events selectedEvent = MyEventsTableView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            storedBeneficiary.getParticipatedEvents().remove(selectedEvent); // Remove event from beneficiary's instance

            try {
                // Update changes to Volunteer.bin and Events.bin
                Beneficiary.updateBeneficiaryInFile(storedBeneficiary);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error while cancelling participation");
            }

            MyEventsTableView.getItems().remove(selectedEvent);
        }
    }

    public void populateEmergencyNoticesTextArea() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("EmergencyNotices.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        emergencyNoticesTextArea.setText(content.toString()); // Set text because appendText makes the text area stay at the bottom - last data.
    }

}
