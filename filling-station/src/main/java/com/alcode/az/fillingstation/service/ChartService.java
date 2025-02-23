package com.alcode.az.fillingstation.service;

import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ChartService {

    public static void configureChart(Chart chart, String title, double width, double height,
                                      boolean animated) {
        chart.setTitle(title);
        chart.setPrefSize(width, height);
        chart.setLegendSide(Side.RIGHT);
        if (chart instanceof XYChart) {
            ((XYChart<?, ?>) chart).setAnimated(animated);
        }
    }

    public static void setYAxisRange(NumberAxis yAxis, String yAxisTitle) {
        yAxis.setAutoRanging(false);
        yAxis.setLabel(yAxisTitle);
        yAxis.setAutoRanging(true);
    }

    public static void setXAxisData(CategoryAxis xAxis, ObservableList<String> categories) {
        xAxis.setCategories(categories);
    }

    public static ScatterChart<String, Number> createScatterChart(String title, String xAxisTitle,
                  String yAxisTitle, boolean animated, double width, double height,
                  ObservableList<XYChart.Series<String, Number>> data) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisTitle);
        NumberAxis yAxis = new NumberAxis();
        setYAxisRange(yAxis, yAxisTitle);

        ScatterChart<String, Number> chart = new ScatterChart<>(xAxis, yAxis);
        configureChart(chart, title, width, height, animated);

        for (XYChart.Series<String, Number> series : data) {
            chart.getData().add(series);
        }
        return chart;
    }

    public static LineChart<String, Number> createLineChart(String title, String xAxisTitle, String yAxisTitle,boolean animated,
                   double width, double height, ObservableList<XYChart.Series<String, Number>> data) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        setYAxisRange(yAxis, yAxisTitle);
        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        configureChart(chart, title, width, height, animated);
        chart.setPrefSize(width, height);
        for (XYChart.Series<String, Number> series : data) {
            chart.getData().add(series);
        }
        return chart;
    }

    public static PieChart createPieChart(String title, String xAxisTitle, String yAxisTitle,boolean animated,
                                          double width, double height, ObservableList<PieChart.Data> data) {
        PieChart chart = new PieChart(data);
        configureChart(chart, title, width, height, animated);
        return chart;
    }

    public static BarChart<String, Number> createBarChart(String title, String xAxisTitle, String yAxisTitle,
                        boolean animated, double width, double height,
                                      ObservableList<XYChart.Series<String, Number>> data) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisTitle);
        NumberAxis yAxis = new NumberAxis();
        setYAxisRange(yAxis, yAxisTitle);
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        configureChart(chart, title, width, height, animated);
        chart.setPrefSize(width, height);

        for (XYChart.Series<String, Number> series : data) {
            chart.getData().add(series);
        }
        return chart;
    }

    public static AreaChart<String, Number> createAreaChart(
                        String title, String xAxisTitle, String yAxisTitle,boolean animated, double width,
                        double height, ObservableList<XYChart.Series<String, Number>> data) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisTitle);
        NumberAxis yAxis = new NumberAxis();
        setYAxisRange(yAxis, yAxisTitle);
        AreaChart<String, Number> chart = new AreaChart<>(xAxis, yAxis);
        configureChart(chart, title, width, height, animated);
        for (XYChart.Series<String, Number> series : data) {
            chart.getData().add(series);
        }
        return chart;
    }

    public static BubbleChart<String, Number> createBubbleChart(
            String title, String xAxisTitle, String yAxisTitle,boolean animated, double width,
            double height, ObservableList<XYChart.Series<String, Number>> data) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisTitle);
        NumberAxis yAxis = new NumberAxis();
        setYAxisRange(yAxis, yAxisTitle);
        BubbleChart<String, Number> chart = new BubbleChart<>(xAxis, yAxis);
        configureChart(chart, title, width, height, animated);
        for (XYChart.Series<String, Number> series : data) {
            chart.getData().add(series);
        }
        return chart;
    }

    public static StackedAreaChart<String, Number> createStackedAreaChart(
            String title, String xAxisTitle, String yAxisTitle,boolean animated, double width,
            double height, ObservableList<XYChart.Series<String, Number>> data) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisTitle);
        NumberAxis yAxis = new NumberAxis();
        setYAxisRange(yAxis, yAxisTitle);
        StackedAreaChart<String, Number> chart = new StackedAreaChart<>(xAxis, yAxis);
        configureChart(chart, title, width, height, animated);
        for (XYChart.Series<String, Number> series : data) {
            chart.getData().add(series);
        }
        return chart;
    }

    public static StackedBarChart<String, Number> createStackedBarChart(
            String title, String xAxisTitle, String yAxisTitle,boolean animated, double width,
            double height, ObservableList<XYChart.Series<String, Number>> data) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisTitle);
        NumberAxis yAxis = new NumberAxis();
        setYAxisRange(yAxis, yAxisTitle);
        StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);
        configureChart(chart, title, width, height, animated);
        for (XYChart.Series<String, Number> series : data) {
            chart.getData().add(series);
        }
        return chart;
    }

    public static FlowPane createNoChart(
            String title, String xAxisTitle, String yAxisTitle,boolean animated, double width,
            double height, ObservableList<XYChart.Series<String, Number>> data) {
        Text text = new Text("No Chart Available");
        text.setFont(new javafx.scene.text.Font(25));

        FlowPane flowPane = new FlowPane();
        flowPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        flowPane.setPrefSize(width, height); // Set preferred size
        flowPane.setAlignment(javafx.geometry.Pos.CENTER); // Center align children
        flowPane.getChildren().add(text);
        return flowPane;
    }
}