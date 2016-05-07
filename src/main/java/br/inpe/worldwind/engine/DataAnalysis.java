package br.inpe.worldwind.engine;

import br.inpe.triangle.conf.Data;
import org.apache.commons.math3.stat.Frequency;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public interface DataAnalysis {
    Frequency createFrequency(Data data);
}
