
import ModelClasses.Beneficiary;
import ModelClasses.Volunteer;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ForumSceneController implements Initializable {

    @FXML
    private Button goBackBtnFxID;
    @FXML
    private TextArea forumPostsTextArea;
    @FXML
    private TextArea postMessageTextArea;

    private static Object objectPassed;

    private static final String FORUM_FILE_PATH = "ForumMessages.txt";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadForumMessages();
    }

    @FXML
    private void goBackBtnOnCLick(ActionEvent event) {
        if (objectPassed instanceof Beneficiary) {
            Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
            SceneSwitcherClass.switchToScene(stage, "BeneficiaryScene.fxml");
        } else if (objectPassed instanceof Volunteer) {
            Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
            SceneSwitcherClass.switchToScene(stage, "VolunteerScene.fxml");
        }
    }

    @FXML
    private void postMessageBtnOnClick(ActionEvent event) {

        String message = postMessageTextArea.getText().trim();
        if (objectPassed instanceof Beneficiary && !message.isEmpty()) {

            Beneficiary passedBeneficiary = (Beneficiary) objectPassed;

            String formattedMessage = String.format("%s (%s) says: %s\n",
                    passedBeneficiary.getName(), "Beneficiary", message);
            forumPostsTextArea.appendText(formattedMessage);
            saveForumMessage(formattedMessage);
            postMessageTextArea.clear();

        } else if (objectPassed instanceof Volunteer && !message.isEmpty()) {
            Volunteer volunteerPassed = (Volunteer) objectPassed;

            String formattedMessage = String.format("%s (%s) says: %s\n",
                    volunteerPassed.getName(), "Volunteer", message);
            forumPostsTextArea.appendText(formattedMessage);
            saveForumMessage(formattedMessage);
            postMessageTextArea.clear();

        } else if (message.isEmpty()) {
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Post");
                alert.setHeaderText(null);
                alert.setContentText("Empty text cannot be posted");
                alert.showAndWait();
        }
    }

    private void saveForumMessage(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FORUM_FILE_PATH, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadForumMessages() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FORUM_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                forumPostsTextArea.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void passObject(Object object) {
        objectPassed = object;
    }
}
