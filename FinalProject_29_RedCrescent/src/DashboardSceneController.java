/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import ModelClasses.Beneficiary;
import ModelClasses.Volunteer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import momo2010537.Blood_doonr_pageController;
import momo2010537.BloodCollectorController;
import redcresent_bloodbank_admin_patient.Admin_DashboardController;
import redcresent_bloodbank_admin_patient.Patient_DashboardController;

/**
 * FXML Controller class
 *
 * @author faiza
 */
public class DashboardSceneController implements Initializable {

    @FXML
    private BorderPane DashboardBorderPaneFx;

    private static String passedData;

    private static Volunteer passedVolunteer;

    private static Beneficiary passedBeneficiary;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (passedData != null && passedData.equals("Volunteer")) {
            Parent root;
            try {

                // Pass current Volunteer instance
                VolunteerSceneController.passedToVolunteerScene(passedVolunteer);
                System.out.println("Passing this Volunteer to Volunteer Scene:" + passedVolunteer.toString());

                root = FXMLLoader.load(getClass().getResource("VolunteerScene.fxml"));
                DashboardBorderPaneFx.setCenter(root);

            } catch (IOException ex) {
                Logger.getLogger(DashboardSceneController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error Switching to Volunteer Scene.");
            }

        } else if (passedData != null && passedData.equals("Beneficiary")) {

            Parent root;
            try {
                //Passing Beneficiary Instance
                BeneficiarySceneController.passBeneficiaryToBeneficiaryDashboard(passedBeneficiary);

                root = FXMLLoader.load(getClass().getResource("BeneficiaryScene.fxml"));
                DashboardBorderPaneFx.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(DashboardSceneController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error Switching to Beneficiary Scene.");
            }

        } else if (passedData != null && passedData.equals("Admin (Event Creation)")) {

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("AdminScene.fxml"));
                DashboardBorderPaneFx.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(DashboardSceneController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error Switching to Admin (Event Creation) Scene.");
            }

        } else if ((passedData != null && passedData.equals("Blood Donor"))) {

            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/momo2010537/Blood_doonr_page.fxml")); // Adjust the path
                root = loader.load();

                System.out.println("Switched to Blood Donor Scene");

                Blood_doonr_pageController controller = loader.getController(); // Get the controller instance if needed

                DashboardBorderPaneFx.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(DashboardSceneController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error Switching to Blood Donor Scene.");
            }
        } else if ((passedData != null && passedData.equals("Blood Collector"))) {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/momo2010537/BloodCollector.fxml")); // Adjust the path
                root = loader.load();

                System.out.println("Switched to Blood Collector Scene");

                BloodCollectorController controller = loader.getController(); // Get the controller instance if needed

                DashboardBorderPaneFx.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(DashboardSceneController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error Switching to Blood Collector Scene.");
            }
        } else if ((passedData != null && passedData.equals("Admin"))) {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/redcresent_bloodbank_admin_patient/Admin_Dashboard.fxml")); // Adjust the path
                root = loader.load();

                System.out.println("Switched to Admin Scene");

                Admin_DashboardController controller = loader.getController(); // Get the controller instance if needed

                DashboardBorderPaneFx.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(DashboardSceneController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error Switching to Admin Scene.");
            }
        } else if ((passedData != null && passedData.equals("Patient"))) {
                
               Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/redcresent_bloodbank_admin_patient/Patient_Dashboard.fxml")); // Adjust the path
                root = loader.load();

                System.out.println("Switched to Patient Scene");

                 Patient_DashboardController controller = loader.getController(); // Get the controller instance if needed

                DashboardBorderPaneFx.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(DashboardSceneController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error Switching to Patient Scene.");
            }    
        }
    }

    public static void passDataToDashboard(String data) {
        passedData = data;
    }

    public static void passVolunteerInstance(Volunteer volunteer) {
        passedVolunteer = volunteer;

    }

    public static void passBeneficiaryInstance(Beneficiary beneficiary) {
        passedBeneficiary = beneficiary;

    }

}
