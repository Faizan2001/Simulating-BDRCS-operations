/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import ModelClasses.Beneficiary;
import ModelClasses.Events;
import ModelClasses.Volunteer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faiza
 */
public class ViewEventDetailsSceneController implements Initializable {

    @FXML
    private TextArea viewEventDetailsTextArea;

    private static Events eventStored;
    private static String sceneSource;
    @FXML
    private Button goBackBtnFxID;

    private static Volunteer currentVolunteer;

    private static Beneficiary currentBeneficiary;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewEventDetailsTextArea.setText(eventStored.getEventDescription());
    }

    public static void passEvent(String source, Events eve) {
        eventStored = eve;
        sceneSource = source;
    }

    public static void passCurrentVolunteer(Volunteer v) {
        currentVolunteer = v;
    }

    public static void passCurrentBeneficiary(Beneficiary b) {
        currentBeneficiary = b;
    }

    public void setUpBackBtnForNearbyEventsScene() {

    }

    @FXML
    private void goBackBtnOnClick(ActionEvent event) {

        switch (sceneSource) {
            case "VolunteerDashboard":
                {
                    Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
                    SceneSwitcherClass.switchToScene(stage, "VolunteerScene.fxml");
                    break;
                }
            case "BeneficiaryDashboard":
                {
                    Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
                    SceneSwitcherClass.switchToScene(stage, "BeneficiaryScene.fxml");
                    break;
                }
            case "NearbyEvents":
                try {
                    // Load the NearbyEventsScene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("NearbyEventsScene.fxml"));
                    Parent nearbyEventsParent = loader.load();
                    
                    // Access the controller and call the onShown method
                    NearbyEventsSceneController nearbyEventsController = loader.getController();
                    nearbyEventsController.passCurrentVolunteer(currentVolunteer);
                    nearbyEventsController.onShown();
                    
                    // Switch to NearbyEventsScene
                    Scene nearbyEventsScene = new Scene(nearbyEventsParent);
                    Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
                    stage.setScene(nearbyEventsScene);
                } catch (IOException e) {
                    e.printStackTrace();
                }   break;
            case "BeneficiaryNearbyEvents":
                try {
                    // Load the BeneficiaryNearbyEventsScene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("BeneficiaryNearbyEvents.fxml"));
                    Parent bNearbyEventsParent = loader.load();
                    
                    // Access the controller and call the onShown method
                    BeneficiaryNearbyEventsController bNearbyEventsController = loader.getController();
                    BeneficiaryNearbyEventsController.passBeneficiary(currentBeneficiary);
                    bNearbyEventsController.onShown();
                    
                    // Switch to BeneficiaryNearbyEventsScene
                    Scene bNearbyEventsScene = new Scene(bNearbyEventsParent);
                    Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
                    stage.setScene(bNearbyEventsScene);
                } catch (IOException e) {
                    e.printStackTrace();
                }   break;
            default:
                break;
        }
    }

}
