
import ModelClasses.Events;
import ModelClasses.Volunteer;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MyVolunteerDataController implements Initializable {

    @FXML
    private Button goBackBtnFxID;
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private static Volunteer volunteerInstance;
    @FXML
    private TableView<Events> completedEventsTableView;
    @FXML
    private TableColumn<Events, String> eventNameColumn;
    
     private ArrayList<Events> completedEventsArrayList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Table View
        
        completedEventsArrayList = volunteerInstance.getCompletedEvents();
        
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        completedEventsTableView.setItems(FXCollections.observableArrayList(completedEventsArrayList));

        // Barchart
        
        
        xAxis.setLabel("Months");
        xAxis.setTickLabelFill(javafx.scene.paint.Color.WHITE); // Set tick label color to white
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 12)); // Set tick label font

        yAxis.setLabel("Completed Events");
        yAxis.setTickLabelFill(javafx.scene.paint.Color.WHITE); // Set tick label color to white
        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 12)); // Set tick label font

        if (volunteerInstance != null) {
            System.out.println("VOLUNTEER INSTANCE PASSED: " + volunteerInstance);
            System.out.println("Completed Events: " + volunteerInstance.getCompletedEvents());

            createBarChart();
        } else {
            System.out.println("volunteerInstance is null");
        }
    }

    private void createBarChart() {
        System.out.println("Line 1");
        xAxis.setLabel("Months");
        yAxis.setLabel("Completed Events");

        System.out.println("Line 2");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Completed Events");

        System.out.println("Line 3");
        List<Integer> monthlyEventCounts = calculateMonthlyEventCounts();
        System.out.println("Number of Completed Events: " + volunteerInstance.getCompletedEvents().size());

        System.out.println("Line 4");
        for (Month month : Month.values()) {
            series.getData().add(new XYChart.Data<>(month.toString(), monthlyEventCounts.get(month.getValue() - 1)));
        }

        System.out.println("barChart: " + barChart);
        System.out.println("series: " + series);

        barChart.getData().add(series);
    }

    private List<Integer> calculateMonthlyEventCounts() {
        List<Integer> counts = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            counts.add(0);
        }

        for (Events event : volunteerInstance.getCompletedEvents()) {
            LocalDate eventDate = event.getEventDate();
            int monthIndex = eventDate.getMonthValue() - 1;
            counts.set(monthIndex, counts.get(monthIndex) + 1);
        }

        return counts;
    }

    @FXML
    private void goBackBtnOnClick(ActionEvent event) {
        Stage stage = (Stage) goBackBtnFxID.getScene().getWindow();
        SceneSwitcherClass.switchToScene(stage, "VolunteerScene.fxml");
    }

    public static void passVolunteerInstance(Volunteer v) {
        volunteerInstance = v;
    }
}
