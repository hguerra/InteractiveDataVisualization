package br.inpe.worldwind.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;

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
}
