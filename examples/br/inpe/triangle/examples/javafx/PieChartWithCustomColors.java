package br.inpe.triangle.examples.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartWithCustomColors extends Application {
    @Override
    public void start(Stage stage) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30)
        );

        final PieChart chart = new PieChart(pieChartData);
        chart.setLegendVisible(false);

        applyCustomColorSequence(
                pieChartData,
                "aqua",
                "bisque",
                "chocolate",
                "coral",
                "crimson"
        );

        stage.setScene(new Scene(chart));
        stage.show();

    }

    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}