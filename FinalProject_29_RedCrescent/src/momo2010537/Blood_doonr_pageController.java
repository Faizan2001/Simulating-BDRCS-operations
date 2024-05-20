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
public class Blood_doonr_pageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void profileButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("my_profile.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void donorcardbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("donorcard.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void schedulebutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Schedule.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void donationhistorbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DonationHistory.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void donationhistorybutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Donationreq.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void overviewbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("processoverview.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void assessmentbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("heaithassesment.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void rulebutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rulesregulation.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void signOutBtnOnClick(ActionEvent event) throws IOException {

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
