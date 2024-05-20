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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DonorcardController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField address;
    @FXML
    private TextField contact;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton male;
    @FXML
    private ComboBox combobox;
    @FXML
    private TextArea textarea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         combobox.getItems().addAll("Mirpur","Uttara","Banani","Agargaon","ECB");
         combobox.setValue("Choose");
        // TODO
    }    

    @FXML
    private void homepagebutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Blood_doonr_page.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void submitbutton(ActionEvent event) {
        
        
    }
    
}
 