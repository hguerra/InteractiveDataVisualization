package br.inpe.worldwind.view.controllers.impl;

import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.engine.DataAnalysis;
import br.inpe.worldwind.engine.SimpleDataAnalysis;
import br.inpe.worldwind.view.DataFrequencyTask;
import br.inpe.worldwind.view.PieChartBuilder;
import br.inpe.worldwind.view.controllers.ApplicationSceneController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.SceneView;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.math3.stat.Frequency;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class SceneBasicController extends ApplicationSceneController {
    private final static ManagerSetupController SETUP_CONTROLLER = ManagerSetupController.getInstance();
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneView;

    @FXML
    private Button btnAnalyse;

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

    }

    private DataFrequencyTask getDataFrequencyTask(List<Data> dataset) {
        ProgressIndicator progress = createProgressIndicator();
        DataFrequencyTask task = new DataFrequencyTask(dataset);

        progress.progressProperty().bind(task.progressProperty());
        task.setOnScheduled(scheduled -> paneView.getChildren().add(progress));
        task.setOnSucceeded(succeeded -> {
            this.chart = createChart();
            if (chart != null)
                this.paneView.getChildren().add(chart);
            else
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
        if (dataFrequencyMap.size() == 1) {
            Optional<Map.Entry<Data, Frequency>> firstFrequency = dataFrequencyMap.entrySet().parallelStream().findAny();

            if (!firstFrequency.isPresent())
                return null;

            Data data = firstFrequency.get().getKey();
            return createPieChart(data);
        } else {
            return createBarChart();
        }
    }

    private Chart createBarChart() {
        return null;
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

                pieChartBuilder.withPrefSize(350, 350);

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
