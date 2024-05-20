
import ModelClasses.Admin;
import ModelClasses.Events;
import ModelClasses.Volunteer;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class NearbyEventsSceneController implements Initializable {

    @FXML
    private TableView<Events> nearbyEventsTableView;
    @FXML
    private TableColumn<Events, Integer> nearbyEventsID;
    @FXML
    private TableColumn<Events, String> nearbyEventsType;
    @FXML
    private TableColumn<Events, String> nearbyEventTime;
    @FXML
    private TableColumn<Events, Integer> nearbyVolunteersJoined;
    @FXML
    private TableColumn<Events, LocalDate> nearbyEventsDate;
    @FXML
    private TableColumn<Events, String> eventLocationColumn;

    private ArrayList<Events> nearbyEventsArrayList;

    private ArrayList<Events> filteredEvents = new ArrayList<>();

    @FXML
    private Button goBackBtnFxID;

    private Events selectedEvent;

    private static Volunteer currentVolunteer;
    @FXML
    private Button viewMoreDetailsBtnFxID;
    @FXML
    private Button participateInEventBtnFxID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nearbyEventsArrayList = Admin.readAndReturnEventsArrayList();

            nearbyEventsID.setCellValueFactory(new PropertyValueFactory<>("eventId"));
            nearbyEventsType.setCellValueFactory(new PropertyValueFactory<>("eventType"));
            nearbyEventsDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
            nearbyEventTime.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
            eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("eventLocation"));
            nearbyVolunteersJoined.setCellValueFactory(new PropertyValueFactory<>("volunteerList"));

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(NearbyEventsSceneController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error Reading/Writing from Events File");
        }

        // Bind the disable property of the View Event Details button to the TableView's selectedItemProperty
        viewMoreDetailsBtnFxID.disableProperty().bind(nearbyEventsTableView.getSelectionModel().selectedItemProperty().isNull());
        
          // Bind the disable property of the Participate button to the TableView's selectedItemProperty
        participateInEventBtnFxID.disableProperty().bind(nearbyEventsTableView.getSelectionModel().selectedItemProperty().isNull());
        
        
    }

    public void passCurrentVolunteer(Volunteer v) {
        currentVolunteer = v;
    }

    @FXML
    private void participateInNearbyEventsOnClick(ActionEvent event) throws ClassNotFoundException {
        selectedEvent = nearbyEventsTableView.getSelectionModel().getSelectedItem();

        boolean hasParticipated = currentVolunteer.getParticipatedEvents().stream()
                .anyMatch(myEvent -> Objects.equals(myEvent.getEventId(), selectedEvent.getEventId()));

        boolean hasCompleted = currentVolunteer.getCompletedEvents().stream()
                .anyMatch(myEvent -> Objects.equals(myEvent.getEventId(), selectedEvent.getEventId()));

        // Validate if Event is already completed, or has already been participated in. If neither: Let user participate.
        if (hasParticipated) {
            // Show alert for already participated event
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Already Participated");
            alert.setHeaderText(null);
            alert.setContentText("You have already participated in this event.");
            alert.showAndWait();
        } else if (hasCompleted) {
            // Show an alert indicating that the event is already completed
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Event Already Completed");
            alert.setHeaderText(null);
            alert.setContentText("You have already completed this event.");
            alert.showAndWait();
        } else {
            // Participate in the event
            currentVolunteer.addEvent(selectedEvent);
            selectedEvent.addVolunteerToList(currentVolunteer);
            filteredEvents.remove(selectedEvent);
            nearbyEventsTableView.getItems().remove(selectedEvent);
            try {
                System.out.println("Successfully Added Event");
                Volunteer.updateVolunteerInFile(currentVolunteer);
                selectedEvent.updateEventsFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void viewMoreDetailsOnClick(ActionEvent event) {
        selectedEvent = nearbyEventsTableView.getSelectionModel().getSelectedItem();
        ViewEventDetailsSceneController.passCurrentVolunteer(currentVolunteer);
        ViewEventDetailsSceneController.passEvent("NearbyEvents", selectedEvent);

        Stage stage = (Stage) viewMoreDetailsBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "ViewEventDetailsScene.fxml");

    }

    @FXML
    private void goBackBtnOnClick(ActionEvent event) {
        Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "VolunteerScene.fxml");
    }

    public void onShown() {
        updateFilteredEventsList();
    }

   // Load Events into TableView which have not been participated in and which have not been completed.
    private void updateFilteredEventsList() {
        filteredEvents.clear();

        for (Events event : nearbyEventsArrayList) {
            boolean participated = currentVolunteer.getParticipatedEvents().stream()
                    .anyMatch(participatedEvent -> participatedEvent.getEventId().equals(event.getEventId()));
            boolean completed = currentVolunteer.getCompletedEvents().stream()
                    .anyMatch(completedEvent -> completedEvent.getEventId().equals(event.getEventId()));

            if (!participated && !completed) {
                filteredEvents.add(event);
            }
        }

        nearbyEventsTableView.setItems(FXCollections.observableArrayList(filteredEvents));
    }
}
