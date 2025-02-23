package com.alcode.az.fillingstation.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static com.alcode.az.fillingstation.service.ChartService.*;

public class CentralDashboardController implements Initializable {

    @FXML
    private HBox stockChartHBox;
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private LineChart<String, Number> lineChart; // Injected from FXML
    @FXML
    private StackPane marqueeStack;
    @FXML
    private Label marqueeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Before createChart");
        stockChartHBox.getChildren().add(populateChart());
        System.out.println("After createChart");
        marquee();
    }

    private void plotBarChart(String barChartTitle, String xAxisTitle, String yAxisTitle,
                              String lineName, XYChart.Data<String, Number>... data) {
        barChart.getXAxis().setLabel(xAxisTitle);
        barChart.getYAxis().setLabel(yAxisTitle);

        // Step 2: Create Series and add data
        XYChart.Series<String, Number> pms = new XYChart.Series<>();
        pms.setName(lineName);
        pms.getData().addAll(data);
        XYChart.Series<String, Number> ago = new XYChart.Series<>();
        ago.setName("AGO");
        ago.getData().add(new XYChart.Data<>("Jan", 15));
        ago.getData().add(new XYChart.Data<>("Feb", 50));
        ago.getData().add(new XYChart.Data<>("Mar", 22));

        XYChart.Series<String, Number> dpk = new XYChart.Series<>();
        dpk.setName("DPK");
        dpk.getData().add(new XYChart.Data<>("Jan", 30));
        dpk.getData().add(new XYChart.Data<>("Feb", 12));
        dpk.getData().add(new XYChart.Data<>("Mar", 64));

        barChart.setAccessibleHelp("tdgdg hjjd");
        barChart.setTitle(barChartTitle);
        barChart.setLegendSide(Side.RIGHT);

        // Step 3: Add Series to the LineChart
        barChart.getData().addAll(pms, ago, dpk);
    }

    Chart populateChart(){
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");

        // Step 3: Add data points to the first series
        series1.getData().add(new XYChart.Data<>("Category A", 10));
        series1.getData().add(new XYChart.Data<>("Category B", 20));
        series1.getData().add(new XYChart.Data<>("Category C", 30));

        // Step 4: Create the second series
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Series 2");

        // Step 5: Add data points to the second series
        series2.getData().add(new XYChart.Data<>("Category A", 15));
        series2.getData().add(new XYChart.Data<>("Category D", 25));
        series2.getData().add(new XYChart.Data<>("Category C", 35));

        // Step 4: Create the second series
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Series 3");

        series3.getData().add(new XYChart.Data<>("Category A", 65));
        series3.getData().add(new XYChart.Data<>("Category G", 44));
        series3.getData().add(new XYChart.Data<>("Category C", 70));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Series 4");

        series4.getData().add(new XYChart.Data<>("Category A", 85));
        series4.getData().add(new XYChart.Data<>("Category B", 5));
        series4.getData().add(new XYChart.Data<>("Category E", 65));

        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("Series 5");

        series5.getData().add(new XYChart.Data<>("Category A", 105));
        series5.getData().add(new XYChart.Data<>("Category B", 95));
        series5.getData().add(new XYChart.Data<>("Category F", 90));

        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        series6.setName("Series 6");

        series6.getData().add(new XYChart.Data<>("Category A", 28));
        series6.getData().add(new XYChart.Data<>("Category B", 72));
        series6.getData().add(new XYChart.Data<>("Category F", 44));

        data.addAll(series1, series2, series3, series4, series5, series6);

        // Step 7: Call the createScatterChart method
        return createBarChart("Stock", "Product", "Quantity", true,
                400, 300, data);
    }
    public void marquee() {
        // Use Platform.runLater to ensure the layout pass is complete
        javafx.application.Platform.runLater(() -> {
            // Set up the TranslateTransition for the marquee effect
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(
                    (marqueeLabel.getWidth()/20)), marqueeLabel);
            translateTransition.setFromX(marqueeStack.getWidth()); // Start from the right side of the screen
            translateTransition.setToX(-marqueeLabel.getWidth()); // Move to the left side of the screen
            translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Loop indefinitely
            translateTransition.setAutoReverse(false); // Do not reverse the animation
            translateTransition.play(); // Start the animation
        });
    }
}