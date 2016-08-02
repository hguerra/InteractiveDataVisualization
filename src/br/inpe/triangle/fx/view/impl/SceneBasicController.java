package br.inpe.triangle.fx.view.impl;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataAnalysis;
import br.inpe.triangle.data.DataFrequencyTask;
import br.inpe.triangle.data.SimpleDataAnalysis;
import br.inpe.triangle.fx.view.BarChartBuilder;
import br.inpe.triangle.fx.view.PieChartBuilder;
import br.inpe.triangle.fx.view.SceneView;

import com.google.common.base.Splitter;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.apache.commons.math3.stat.Frequency;

import javax.swing.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class SceneBasicController extends ApplicationSceneController {
    private final static ManagerSetupController SETUP_CONTROLLER = ManagerSetupController.getInstance();
    private static final double PIE_CHART_SIZE_WIDTH = 350;
    private static final double PIE_CHART_SIZE_HEIGHT = 350;
    private static final double BAR_CHART_SIZE_WIDTH = 340;
    private static final double BAR_CHART_SIZE_HEIGHT = 340;
    private static final double IMAGE_SIZE_WIDTH = 600;
    private static final double IMAGE_SIZE_HEIGHT = 600;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneView;

    @FXML
    private Button btnAnalyse;

    @FXML
    private Button btnSaveAsImage;

    private Chart chart;

    private DataAnalysis dataAnalysis;

    private Map<Data, Frequency> dataFrequencyMap;

    @Override
    protected void initPaneSetup() {
        this.dataAnalysis = new SimpleDataAnalysis();
        this.dataFrequencyMap = new HashMap<>();
    }

    @Override
    protected SceneView getSceneView() {
        return SceneView.BASIC_VIEW;
    }

    @Override
    protected Pane getPaneView() {
        return paneView;
    }

    @Override
    public void initPaneSetupEvents() {
        btnAnalyse.setOnAction(event -> {
            List<Data> dataset = SETUP_CONTROLLER.getDatasetFromBasicController();

            if (dataset == null || dataset.isEmpty())
                return;

            DataFrequencyTask task = getDataFrequencyTask(dataset);
            new Thread(task).start();

            this.dataFrequencyMap = task.getDataFrequencyMap();
        });

        btnSaveAsImage.setOnAction(event -> {
            if (chart == null) {
                JOptionPane.showMessageDialog(null, "Create a chart first", "Chart warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);

            if (file == null) {
                JOptionPane.showMessageDialog(null, "Select the file path", "Chart warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Chart saveChart;
            if (dataFrequencyMap.size() == 1) {
                PieChartBuilder pieChartBuilder = new PieChartBuilder();
                pieChartBuilder.clone((PieChart) chart);
                pieChartBuilder.withPrefSize(IMAGE_SIZE_WIDTH, IMAGE_SIZE_HEIGHT);
                saveChart = pieChartBuilder.getChart();
            } else {
                BarChartBuilder barChartBuilder = new BarChartBuilder();
                barChartBuilder.clone((BarChart<String, Number>) chart);
                barChartBuilder.withPrefSize(IMAGE_SIZE_WIDTH, IMAGE_SIZE_HEIGHT);
                saveChart = barChartBuilder.getChart();
            }
            SETUP_CONTROLLER.saveNodeAsImage(saveChart, file);
        });

    }

    private DataFrequencyTask getDataFrequencyTask(List<Data> dataset) {
        ProgressIndicator progress = createProgressIndicator();
        DataFrequencyTask task = new DataFrequencyTask(dataset);

        progress.progressProperty().bind(task.progressProperty());
        task.setOnScheduled(scheduled -> paneView.getChildren().add(progress));
        task.setOnSucceeded(succeeded -> {
            if (paneView.getChildren().contains(chart)) {
                paneView.getChildren().remove(chart);
            }
            this.chart = createChart();
            if (chart != null) {
                this.paneView.getChildren().add(chart);
            } else
                JOptionPane.showMessageDialog(null, "Error to create chart", "ERROR IN CHART", JOptionPane.ERROR_MESSAGE);
            this.paneView.getChildren().remove(progress);
        });
        return task;
    }


    private ProgressIndicator createProgressIndicator() {
        ProgressIndicator progress = new ProgressIndicator(0.0);
        progress.setPrefSize(100, 100);
        centralizeComponent(progress);
        return progress;
    }

    private void centralizeComponent(Control control) {
        control.setLayoutX((paneView.getWidth() - control.getWidth()) / 2.8);
        control.setLayoutY((paneView.getHeight() - control.getHeight()) / 2);
    }

    private Chart createChart() {
        Stream<Map.Entry<Data, Frequency>> dataFrequencyStream = dataFrequencyMap.entrySet().parallelStream();
        if (dataFrequencyMap.size() == 1) {
            Optional<Map.Entry<Data, Frequency>> firstFrequency = dataFrequencyStream.findAny();

            if (!firstFrequency.isPresent())
                return null;

            Data data = firstFrequency.get().getKey();
            return createPieChart(data);
        } else {
            return createBarChart();
        }
    }

    private Chart createBarChart() {
        Task<BarChartBuilder> barChartBuilderTask = new Task<BarChartBuilder>() {
            @Override
            protected BarChartBuilder call() throws Exception {
                BarChartBuilder barChartBuilder = new BarChartBuilder();

                dataFrequencyMap.forEach((data, frequency) -> {
                    List<String> datasetGroupIterator = Splitter.on("_")
                            .trimResults()
                            .omitEmptyStrings()
                            .splitToList(data.getTitle());

                    Frequency dataFrequency = dataFrequencyMap.get(data);

                    dataFrequency.entrySetIterator().forEachRemaining(entry -> {
                        String title = datasetGroupIterator.size() >= 2 ? datasetGroupIterator.get(1) : data.getTitle();
                        barChartBuilder.withData(title,
                                new XYChart.Data<>(entry.getKey().toString(),
                                        dataFrequency.getPct(entry.getKey()) * 100));
                    });

                });
                barChartBuilder.withTitle("Temporal Chart");
                barChartBuilder.withLabel("Attribute", "Percentage");
                barChartBuilder.withLayout(0, 120);
                barChartBuilder.withPrefSize(BAR_CHART_SIZE_WIDTH, BAR_CHART_SIZE_HEIGHT);
                return barChartBuilder;
            }
        };
        new Thread(barChartBuilderTask).start();
        try {
            return barChartBuilderTask.get().getChart();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Chart createPieChart(Data data) {
        Task<PieChartBuilder> pieChartBuilderTask = new Task<PieChartBuilder>() {
            @Override
            protected PieChartBuilder call() throws Exception {
                PieChartBuilder pieChartBuilder = new PieChartBuilder();
                Frequency dataFrequency = dataFrequencyMap.get(data);

                dataFrequency.entrySetIterator().forEachRemaining(data -> pieChartBuilder
                        .withData(new PieChart.Data(data.getKey().toString(), dataFrequency.getPct(data.getKey()) * 100)));
                pieChartBuilder.withTitle(data.getTitle());
                pieChartBuilder.withLayout(10, 110);
                pieChartBuilder.withPrefSize(PIE_CHART_SIZE_WIDTH, PIE_CHART_SIZE_HEIGHT);

                return pieChartBuilder;
            }
        };
        new Thread(pieChartBuilderTask).start();

        try {
            return pieChartBuilderTask.get().getChart();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Object object) {

    }
}
