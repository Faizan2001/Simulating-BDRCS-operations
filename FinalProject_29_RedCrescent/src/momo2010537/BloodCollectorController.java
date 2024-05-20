/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package momo2010537;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BloodCollectorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void schedulebutton(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("schedulecollector.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void informationbutton(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("information.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void donorsafetybutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Donorsafety.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void eligibilitybutton(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("eigibility.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void supplyanddemandbutton(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("SupplyDemands.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void madicalbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Medicalfacilities.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void maintenancebutton(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Maintenance.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void TrainingandDevelopmentbutton(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("TrainingDevelopment.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void logoutbutton(ActionEvent event) throws IOException {
         Parent loginScene = FXMLLoader.load(getClass().getResource("/LoginScene.fxml"));

        Scene scene = new Scene(loginScene);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.show();

        // Close the current stage (i.e., the stage associated with the button)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
    
}
