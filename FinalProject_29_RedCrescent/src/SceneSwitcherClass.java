/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author faiza
 */
public class SceneSwitcherClass {

    public static void switchToScene(Stage stage, String resource) {
        try {
            Parent root = FXMLLoader.load(SceneSwitcherClass.class.getResource(resource));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("Error switching to scene: " + resource);
            ex.printStackTrace();
        }
    }

}
