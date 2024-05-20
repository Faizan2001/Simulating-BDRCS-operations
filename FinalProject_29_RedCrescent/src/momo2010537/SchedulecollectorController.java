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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SchedulecollectorController implements Initializable {

    @FXML
    private TextField donorname;
    @FXML
    private TextField donorid;
    @FXML
    private TextField donormail;
    @FXML
    private TextField bloodblood;
    @FXML
    private RadioButton radiofemale;
    @FXML
    private RadioButton radiomale;
    @FXML
    private TextField donorcontact;
    @FXML
    private TextField donoraddress;
    @FXML
    private TextArea textarea;
    @FXML
    private ComboBox<?> selectarea;
    @FXML
    private DatePicker datepick;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void homepagebutton(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("BloodCollector.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Client Page");
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void submitbutton(ActionEvent event) {
    }

    @FXML
    private void enterbutton(ActionEvent event) {
    }
    
}
