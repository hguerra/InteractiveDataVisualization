package br.inpe.triangle.fx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Heitor
 * @since 09/05/2016
 */
public class BarChartBuilder implements ChartBuilder {
    private String title;
    private double prefWidth = 400;
    private double prefHeight = 400;
    private double layoutX = 10;
    private double layoutY = 100;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private Map<String, ObservableList<XYChart.Data<String, Number>>> dataset;


    public BarChartBuilder() {
        this.dataset = new HashMap<>();
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();
    }

    @SafeVarargs
    public final BarChartBuilder withData(String title, XYChart.Data<String, Number>... data) {
        if (dataset.containsKey(title))
            dataset.get(title).addAll(data);
        else
            dataset.put(title, FXCollections.observableArrayList(data));
        return this;
    }

    public BarChartBuilder withPrefSize(double prefWidth, double prefHeight) {
        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;
        return this;
    }

    public BarChartBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BarChartBuilder withLayout(double layoutX, double layoutY) {
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        return this;
    }

    public BarChartBuilder withLabel(String x, String y) {
        this.xAxis.setLabel(x);
        this.yAxis.setLabel(y);
        return this;
    }

    @Override
    public Chart getChart() {
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(title);
        barChart.setLayoutX(layoutX);
        barChart.setLayoutY(layoutY);
        barChart.setPrefSize(prefWidth, prefHeight);
        dataset.forEach((title, data) -> {
            XYChart.Series<String, Number> serie = new XYChart.Series<>(data);
            serie.setName(title);
            barChart.getData().add(serie);
        });
        return barChart;
    }

    public Chart clone(BarChart<String, Number> barChart) {
        withLabel(barChart.getXAxis().getLabel(), barChart.getYAxis().getLabel());
        withTitle(barChart.getTitle());
        withLayout(barChart.getLayoutX(), barChart.getLayoutY());
        withPrefSize(barChart.getPrefWidth(), barChart.getPrefHeight());

        barChart.getData().forEach(series -> {
            series.getData().forEach(xyChart -> {
                withData(series.getName(), new XYChart.Data<>(xyChart.getXValue(), (double) xyChart.getYValue() * 100));
            });
        });
        return getChart();
    }
}
