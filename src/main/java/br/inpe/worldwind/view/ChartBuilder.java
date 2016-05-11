package br.inpe.worldwind.view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * @author Heitor
 * @since 09/05/2016
 */
public interface ChartBuilder {
    /**
     * @return javafx.scene.chart.Chart
     */
    Chart getChart();

    /**
     * Usage:
     * <p>
     * applyCustomColorSequence(
     * pieChartData,
     * "aqua",
     * "bisque",
     * "chocolate",
     * "coral",
     * "crimson"
     * );
     * </p>
     *
     * @param pieChartData: ObservableList<PieChart.Data>
     * @param pieColors:    String[]
     */
    default void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

    /**
     * Change color of bar if value of i is <5 then red, if >5 then green if i>8 then blue
     */
    default void setNodeStyle(XYChart.Data<String, Number> data) {
        Node node = data.getNode();
        if (data.getYValue().intValue() > 8) {
            node.setStyle("-fx-bar-fill: -fx-exceeded;");
        } else if (data.getYValue().intValue() > 5) {
            node.setStyle("-fx-bar-fill: -fx-achieved;");
        } else {
            node.setStyle("-fx-bar-fill: -fx-not-achieved;");
        }
    }
}
