package br.inpe.triangle.data;

import org.apache.commons.math3.stat.Frequency;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public interface DataAnalysis {
    Frequency createFrequency(Data data);
}
