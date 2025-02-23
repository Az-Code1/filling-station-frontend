package com.alcode.az.fillingstation.controller;

import com.alcode.az.fillingstation.service.ChartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class BranchDashboardController implements Initializable {

    @FXML
    private HBox pricesChartHBox;
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
    private static ObservableList<PieChart.Data> PIE_CHART_DATA;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stockChartHBox.getChildren().addAll(createStockChart(), createReceivedStockChart());
        marqueeStack.getChildren().add(marquee(new Label("Welcome to the Branch Dashboard")));
        pricesChartHBox.getChildren().removeAll();
        pricesChartHBox.getChildren().addAll(createPriceChart(), createAverageSalesChart());
    }

    private Node createPriceChart() {
        String jsonData = "{\n" +
                "  \"PMS\": {\n" +
                "    \"This week\": 970,\n" +
                "    \"Last week\": 970,\n" +
                "    \"2 weeks ago\": 960,\n" +
                "    \"3 weeks ago\": 969,\n" +
                "    \"4 weeks ago\": 980,\n" +
                "    \"5 weeks ago\": 949,\n" +
                "    \"6 weeks ago\": 950\n" +
                "  },\n" +
                "  \"AGO\": {\n" +
                "    \"This week\": 1130,\n" +
                "    \"Last week\": 1130,\n" +
                "    \"2 weeks ago\": 1140,\n" +
                "    \"3 weeks ago\": 1140,\n" +
                "    \"4 weeks ago\": 1130,\n" +
                "    \"5 weeks ago\": 1100,\n" +
                "    \"6 weeks ago\": 1100\n" +
                "  },\n" +
                "  \"DPK\": {\n" +
                "    \"This week\": 1170,\n" +
                "    \"Last week\": 1170,\n" +
                "    \"2 weeks ago\": 1170,\n" +
                "    \"3 weeks ago\": 1200,\n" +
                "    \"4 weeks ago\": 1200,\n" +
                "    \"5 weeks ago\": 1180,\n" +
                "    \"6 weeks ago\": 1180\n" +
                "  }\n" +
                "}";

        return createChart("Price Trend Chart", ChartType.LINE_CHART,
                "Weeks", "Prices in Naira",
                getMappedGraphData(jsonData));
    }

    private Map<String, Object> getMappedGraphData(String jSonData){
        // Convert JSON to Map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> dataMap = objectMapper.readValue(jSonData, Map.class);
            return dataMap;
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        return null;
    }

    private Node createReceivedStockChart(){
        String jsonData = "{\n" +
                "  \"PMS\": {\n" +
                "    \"JAN\": 50000,\n" +
                "    \"FEB\": 49000,\n" +
                "    \"MAR\": 40000,\n" +
                "    \"APRIL\": 35000,\n" +
                "    \"MAY\": 30000,\n" +
                "    \"JUNE\": 45000,\n" +
                "    \"JULY\": 25000\n" +
                "  },\n" +
                "  \"AGO\": {\n" +
                "    \"JAN\": 40000,\n" +
                "    \"FEB\": 30000,\n" +
                "    \"MAR\": 37500,\n" +
                "    \"APRIL\": 28000,\n" +
                "    \"MAY\": 25000,\n" +
                "    \"JUNE\": 30000,\n" +
                "    \"JULY\": 32500\n" +
                "  },\n" +
                "  \"DPK\": {\n" +
                "    \"JAN\": 7500,\n" +
                "    \"FEB\": 2000,\n" +
                "    \"MAR\": 15000,\n" +
                "    \"APRIL\": 0,\n" +
                "    \"MAY\": 8500,\n" +
                "    \"JUNE\": 0,\n" +
                "    \"JULY\": 15000\n" +
                "  }\n" +
                "}";

        // Convert JSON to Map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> dataMap = objectMapper.readValue(jsonData, Map.class);

            // Call createChart method
            return createChart("Received Stock Chart", ChartType.AREA_CHART,
                    "Products", "Quantity in lts",
                    dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    private Node createAverageSalesChart(){
        String jsonData = "{\n" +
                "  \"PMS\": {\n" +
                "    \"MON\": 2010,\n" +
                "    \"TUE\": 1987,\n" +
                "    \"WED\": 1780,\n" +
                "    \"THUR\": 1934,\n" +
                "    \"FRI\": 1524,\n" +
                "    \"SAT\": 1610,\n" +
                "    \"SUN\": 1200\n" +
                "  },\n" +
                "  \"AGO\": {\n" +
                "    \"MON\": 1312,\n" +
                "    \"TUE\": 1100,\n" +
                "    \"WED\": 2560,\n" +
                "    \"THUR\": 1120,\n" +
                "    \"FRI\": 1420,\n" +
                "    \"SAT\": 1020,\n" +
                "    \"SUN\": 920\n" +
                "  },\n" +
                "  \"DPK\": {\n" +
                "    \"MON\": 220,\n" +
                "    \"TUE\": 120,\n" +
                "    \"WED\": 139,\n" +
                "    \"THUR\": 100,\n" +
                "    \"FRI\": 230,\n" +
                "    \"SAT\": 150,\n" +
                "    \"SUN\": 130\n" +
                "  }\n" +
                "}";

        // Convert JSON to Map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> dataMap = objectMapper.readValue(jsonData, Map.class);

            // Call createChart method
            return createChart("Average Sales Chart", ChartType.BAR_CHART,
                    "Products", "Quantity in lts",
                    dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Node createStockChart(){
        String jsonData = "{\n" +
                "  \"PMS\": {\n" +
                "    \"PMS\": 20\n" +
                "  },\n" +
                "  \"AGO\": {\n" +
                "    \"AGO\": 35\n" +
                "  },\n" +
                "  \"DPK\": {\n" +
                "    \"DPK\": 23\n" +
                "  }\n" +
                "}";

        // Convert JSON to Map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> dataMap = objectMapper.readValue(jsonData, Map.class);

            // Call createChart method
            return createChart("Stock Chart", ChartType.STACKED_BAR_CHART, "Products", "Quantity (in litres)",
                    dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    private Node createChart(String chartTitle, ChartType chartType, String xAxisTitle, String yAxisTitle, Map<String, Object> data) {
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();

        if (isSimpleData(data)) {
            // Handle simple data structure: { "PMS": 20, "AGO": 35, "DPK": 23 }
            chartData.add(createSeriesFromSimpleData(data));
        } else {
            // Handle nested data structure: { "JAN": { "PMS": 20, "AGO": 35, "DPK": 23 }, ... }
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String seriesName = entry.getKey();
                Map<String, Number> seriesData = (Map<String, Number>) entry.getValue();
                chartData.add(createSeries(seriesName, seriesData));
            }
        }

        return selectChartType(chartTitle, chartType, xAxisTitle, yAxisTitle, chartData);
    }
    private boolean isSimpleData(Map<String, Object> data) {
        for (Object value : data.values()) {
            if (value instanceof Map) {
                return false; // Nested structure found
            }
        }
        return true; // Simple structure
    }

    private XYChart.Series<String, Number> createSeriesFromSimpleData(Map<String, Object> data) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data");

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String category = entry.getKey();
            Number value = (Number) entry.getValue();
            series.getData().add(new XYChart.Data<>(category, value));
        }

        return series;
    }

    private XYChart.Series<String, Number> createSeries(String seriesName, Map<String, Number> seriesData) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        for (Map.Entry<String, Number> entry : seriesData.entrySet()) {
            String category = entry.getKey();
            Number value = entry.getValue();
            series.getData().add(new XYChart.Data<>(category, value));
        }

        return series;
    }

    private Node selectChartType(String chartTitle, ChartType chartType,String xAxisTitle, String yAxisTitle, ObservableList<XYChart.Series<String, Number>> data) {
        return switch (chartType) {
            case SCATTER_CHART -> ChartService.createScatterChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, data);
            case BAR_CHART -> ChartService.createBarChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, data);
            case LINE_CHART -> ChartService.createLineChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, data);
            case BUBBLE_CHART -> ChartService.createBubbleChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, data);
            case AREA_CHART -> ChartService.createAreaChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, data);
            case PIE_CHART -> ChartService.createPieChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, getPieChartData());
            case STACKED_BAR_CHART -> ChartService.createStackedBarChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, data);
            case STACKED_AREA_CHART -> ChartService.createStackedAreaChart(chartTitle, xAxisTitle, yAxisTitle,
                    true, 450, 300, data);
            case NO_CHART -> ChartService.createNoChart(chartTitle, "", "", true,
                    450, 300, null);
            default -> ChartService.createNoChart("Chart Not Available", "", "",
                    true, 450, 300, null);

        };

    }
    static {
        PIE_CHART_DATA = FXCollections.observableArrayList(
                new PieChart.Data("Category A", 30),
                new PieChart.Data("Category B", 25),
                new PieChart.Data("Category C", 45)
        );
    }

    public static ObservableList<PieChart.Data> getPieChartData() {
        return PIE_CHART_DATA;
    }

    public static void setPieChartData(ObservableList<PieChart.Data> pieChartData) {
        PIE_CHART_DATA = pieChartData;
    }

    public Label marquee(Label marqueeLabel) {
        this.marqueeLabel = marqueeLabel;
        // Use Platform.runLater to ensure the layout pass is complete
        javafx.application.Platform.runLater(() -> {
            // Set up the TranslateTransition for the marquee effect
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(
                    (marqueeLabel.getWidth()/20)), marqueeLabel);
            translateTransition.setFromX(marqueeStack.getWidth()); // Start from the right side of the screen
            translateTransition.setToX(-marqueeLabel.getWidth()); // Move to the left side of the screen
            translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Loop indefinitely
            translateTransition.setAutoReverse(false); // Do not reverse the animation
            translateTransition.play();
        });
        return this.marqueeLabel;
    }

    public enum ChartType {
        SCATTER_CHART,
        BAR_CHART,
        LINE_CHART,
        BUBBLE_CHART,
        AREA_CHART,
        PIE_CHART,
        STACKED_AREA_CHART,
        STACKED_BAR_CHART,
        NO_CHART
    }
}
