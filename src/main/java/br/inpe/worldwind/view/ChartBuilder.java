package br.inpe.worldwind.view;

import javafx.collections.ObservableList;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;

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
}
