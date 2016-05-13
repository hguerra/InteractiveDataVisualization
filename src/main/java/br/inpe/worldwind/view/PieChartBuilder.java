package br.inpe.worldwind.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Heitor
 * @since 09/05/2016
 */
public class PieChartBuilder implements ChartBuilder {
    private String title;
    private double prefWidth = 400;
    private double prefHeight = 400;
    private double layoutX = 10;
    private double layoutY = 100;
    private String[] colorSequence;
    private ObservableList<PieChart.Data> pieChartData;

    public PieChartBuilder() {
        this.pieChartData = FXCollections.observableArrayList();
    }

    public PieChartBuilder withData(PieChart.Data... data) {
        pieChartData.addAll(data);
        return this;
    }

    public PieChartBuilder withColorSequence(String... pieColors) {
        this.colorSequence = pieColors;
        return this;
    }

    public PieChartBuilder withPrefSize(double prefWidth, double prefHeight) {
        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;
        return this;
    }

    public PieChartBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PieChartBuilder withLayout(double layoutX, double layoutY) {
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        return this;
    }

    @Override
    public Chart getChart() {
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle(title);
        chart.setPrefSize(prefWidth, prefHeight);
        chart.setLayoutX(layoutX);
        chart.setLayoutY(layoutY);
        if (colorSequence != null)
            applyCustomColorSequence(pieChartData, colorSequence);
        return chart;
    }

    public Chart clone(PieChart chart) {
        ObservableList<PieChart.Data> clonePieData = chart.getData()
                .parallelStream()
                .map(data -> new PieChart.Data(data.getName(), data.getPieValue()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableArrayList));

        PieChart cloneChart = new PieChart(clonePieData);
        cloneChart.setTitle(chart.getTitle());
        cloneChart.setPrefSize(chart.getPrefWidth(), chart.getPrefHeight());
        cloneChart.setLayoutX(chart.getLayoutX());
        cloneChart.setLayoutY(chart.getLayoutY());
        if (colorSequence != null)
            applyCustomColorSequence(clonePieData, colorSequence);
        return cloneChart;
    }

}
