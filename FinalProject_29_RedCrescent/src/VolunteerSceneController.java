/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faiza
 */
public class VolunteerSceneController implements Initializable {

    @FXML
    private TableView<Events> MyEventsTableView;
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
    private Button viewVolunteersBtnFxID;
    @FXML
    private Button cancelParticipationBtnFxID;
    @FXML
    private Button nearbyEventsBtnFxID;

    private static Volunteer storedVolunteer;
    @FXML
    private Button myVolBtnFxID;
    @FXML
    private Button viewEventDetailsFxID;
    @FXML
    private AnchorPane volunteerAnchorPane;
    @FXML
    private Button markCompleteBtnFxId;
    @FXML
    private Button forumBtnFxID;
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

        // From our current Volunteer instance, we get the arraylist of Events stored in the field. 
        //   dummyEvents = storedVolunteer.getParticipatedEvents();
        System.out.println("Received Volunteer in VolunteerScene:" + storedVolunteer.toString());

        MyEventsTableView.setItems(FXCollections.observableArrayList(storedVolunteer.getParticipatedEvents()));
        System.out.println("Stored Volunteer ArrayList currently:" + storedVolunteer.getParticipatedEvents());

        // Bind the disable property of the button to the TableView's selectedItemProperty
        cancelParticipationBtnFxID.disableProperty().bind(MyEventsTableView.getSelectionModel().selectedItemProperty().isNull());

        // Bind the disable property of the View Event Details button to the TableView's selectedItemProperty
        viewEventDetailsFxID.disableProperty().bind(MyEventsTableView.getSelectionModel().selectedItemProperty().isNull());

        // Bind the disable property of the View Event Details button to the TableView's selectedItemProperty
        markCompleteBtnFxId.disableProperty().bind(MyEventsTableView.getSelectionModel().selectedItemProperty().isNull());
        
        populateEmergencyNoticesTextArea();

    }

    public static Volunteer passedToVolunteerScene(Volunteer v) {
        storedVolunteer = v;
        return storedVolunteer;
    }

    @FXML
    private void viewEventDetailsOnClick(ActionEvent event) {
        Events selectedEvent = MyEventsTableView.getSelectionModel().getSelectedItem();
        ViewEventDetailsSceneController.passEvent("VolunteerDashboard", selectedEvent);

        Stage stage = (Stage) viewVolunteersBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "ViewEventDetailsScene.fxml");

    }

    @FXML
    private void cancelParticipationOnClick(ActionEvent event) {
        Events selectedEvent = MyEventsTableView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            storedVolunteer.getParticipatedEvents().remove(selectedEvent); // Remove event from volunteer's instance
            selectedEvent.getParticipatingVolunteers().remove(storedVolunteer); // Remove volunteer from event's list

            // Update the event's volunteer list count
            selectedEvent.setVolunteerList(selectedEvent.getVolunteerList() - 1);

            try {
                // Update changes to Volunteer.bin and Events.bin
                Volunteer.updateVolunteerInFile(storedVolunteer);
                selectedEvent.updateEventsFile();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error while cancelling participation");
            }

            MyEventsTableView.getItems().remove(selectedEvent);
        }

    }

    @FXML
    private void lookForNearbyEventsOnClick(ActionEvent event) {
        try {
            // Load the NearbyEventsScene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NearbyEventsScene.fxml"));
            Parent nearbyEventsParent = loader.load();

            // Access the controller and call the onShown method
            NearbyEventsSceneController nearbyEventsController = loader.getController();
            nearbyEventsController.passCurrentVolunteer(storedVolunteer);
            nearbyEventsController.onShown();

            // Switch to NearbyEventsScene
            Scene nearbyEventsScene = new Scene(nearbyEventsParent);
            Stage stage = (Stage) nearbyEventsBtnFxID.getScene().getWindow();
            stage.setScene(nearbyEventsScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    private void viewVolunteersOnClick(ActionEvent event) {
        Stage stage = (Stage) viewVolunteersBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "ViewVolunteersScene.fxml");

    }

    @FXML
    private void myVolunteerDataOnClick(ActionEvent event) {

        MyVolunteerDataController.passVolunteerInstance(storedVolunteer);

        Stage stage = (Stage) myVolBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "MyVolunteerData.fxml");

    }

    @FXML
    private void markCompleteOnClick(ActionEvent event) {
        Events selectedEvent = MyEventsTableView.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            // Check if the event is already marked as completed by this volunteer
            if (storedVolunteer.getCompletedEvents().contains(selectedEvent)) {
                // Show an alert indicating that the event is already completed
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Event Already Completed");
                alert.setHeaderText(null);
                alert.setContentText("You have already completed this event.");
                alert.showAndWait();
            } else {
                // Mark the event as completed by adding it to the completedEvents list
                storedVolunteer.addCompletedEvent(selectedEvent);
                // Remove the event from the participated events list as we move it to Completed Events list
                storedVolunteer.getParticipatedEvents().remove(selectedEvent);
                selectedEvent.setVolunteerList(selectedEvent.getVolunteerList() - 1);

                // Update the changes to the Volunteer.bin file
                try {
                    Volunteer.updateVolunteerInFile(storedVolunteer);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Error while marking event as completed");
                }

                // Show a confirmation alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Event Marked as Completed");
                alert.setHeaderText(null);
                alert.setContentText("Event Completed! The event has been marked in your profile data");
                alert.showAndWait();
                System.out.println("MARKING 1" + storedVolunteer.getCompletedEvents().toString());

                // Refresh the TableView to reflect the changes
                MyEventsTableView.getItems().remove(selectedEvent);
            }
        }
    }

    @FXML
    private void forumBtnOnClick(ActionEvent event) {
        ForumSceneController.passObject(storedVolunteer);
        Stage stage = (Stage) forumBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "ForumScene.fxml");
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
