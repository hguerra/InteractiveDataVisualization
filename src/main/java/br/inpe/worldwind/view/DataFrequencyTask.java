package br.inpe.worldwind.view;

import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.engine.DataAnalysis;
import br.inpe.worldwind.engine.SimpleDataAnalysis;
import javafx.concurrent.Task;
import org.apache.commons.math3.stat.Frequency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heitor
 * @since 09/05/2016
 */
public class DataFrequencyTask extends Task<Void> {
    private DataAnalysis dataAnalysis;
    private List<Data> dataset;
    private Map<Data, Frequency> dataFrequencyMap;

    public DataFrequencyTask(List<Data> dataset) {
        this.dataset = dataset;
        this.dataAnalysis = new SimpleDataAnalysis();
        this.dataFrequencyMap = new HashMap<>();
    }

    public Map<Data, Frequency> getDataFrequencyMap() {
        return dataFrequencyMap;
    }

    @Override
    protected Void call() throws Exception {
        createDataFrequency();
        return null;
    }

    private void createDataFrequency() {
        int progressActual = 0;
        final int progressMax = dataset.size();
        for (Data data : dataset) {
            Frequency dataFrequency = dataAnalysis.createFrequency(data);
            dataFrequencyMap.put(data, dataFrequency);
            //update progress
            updateProgress(progressActual, progressMax);
            progressActual++;
        }
    }
}
