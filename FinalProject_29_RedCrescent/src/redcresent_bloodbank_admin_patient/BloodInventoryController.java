/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package redcresent_bloodbank_admin_patient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class BloodInventoryController implements Initializable {

    @FXML
    private TableView<?> BloodInventoryTable;
    @FXML
    private TableColumn<?, ?> Quantity;
    @FXML
    private Button DiscurdUnits;
    @FXML
    private TextField Status;
    @FXML
    private TableColumn<?, ?> BloodGroup;
    @FXML
    private Button AddUnits;
    @FXML
    private ComboBox<?> SelectBloodGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BloodInventoryTableOnSort() {
    }


    @FXML
    private void DiscurdUnitsOnAction(ActionEvent event) {
    }

    @FXML
    private void StatusOnAction(ActionEvent event) {
    }

    @FXML
    private void AddUnitsOnAction(ActionEvent event) {
    }
    
}
