package br.inpe.worldwind.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataAnalysis;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.data.SimpleDataAnalysis;
import br.inpe.triangle.defaultproperties.DefaultDataSource;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class DataAnalysisTest {
	private static DataAnalysis dataAnalysis;
	private double[] inputArray = { 1, 1, 1, 2, 3, 4 }; // sum equals 12

	@BeforeClass
	public static void runBeforeClass() {
		dataAnalysis = new SimpleDataAnalysis();
	}

	@AfterClass
	public static void runAfterClass() {
		dataAnalysis = null;
	}

	@Test
	public void apacheCommonsDescriptiveStatisticsTest() throws Exception {
		// Get a DescriptiveStatistics instance
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (double anInputArray : inputArray) {
			stats.addValue(anInputArray);
		}
		// Compute some statistics
		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
		double median = stats.getPercentile(50);

		assertThat(mean, is(2.0)); // 12 / 6
		assertThat(std, is(1.2649110640673518));
		assertThat(median, is(1.5));// 1,2,3,4 / 6 = 1.5, remove equals elements
	}

	@Test
	public void apacheCommonsSummaryStatisticsTest() throws Exception {
		// Get a SummaryStatistics instance
		SummaryStatistics stats = new SummaryStatistics();
		for (double anInputArray : inputArray) {
			stats.addValue(anInputArray);
		}
		// Compute the statistics
		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
		assertThat(mean, is(2.0)); // 12 / 6
		assertThat(std, is(1.2649110640673518));
	}

	@Test
	public void apacheCommonsStatsUtilTest() throws Exception {
		// Compute statistics directly from the array
		// assume inputArray is a double[] array
		double mean = StatUtils.mean(inputArray);
		double std = FastMath.sqrt(StatUtils.variance(inputArray));
		double median = StatUtils.percentile(inputArray, 50);
		assertThat(mean, is(2.0)); // 12 / 6
		assertThat(StatUtils.mean(inputArray, 0, 3), is(1.0)); // Compute the
																// mean of the
																// first three
																// inputArray in
																// the array
		assertThat(std, is(1.2649110640673518));
		assertThat(median, is(1.5));// 1,2,3,4 / 6 = 1.5, remove equals elements
	}

	@Test
	public void computeFrequencyDistributionIntegerValuesTest() throws Exception {
		Frequency f = new Frequency();
		// add 1 at 3 times
		f.addValue(1);
		f.addValue(new Integer(1));
		f.addValue(new Long(1));
		// add 2 at 1 time
		f.addValue(2);
		// add -1 at 1 time
		f.addValue(new Integer(-1));
		/**
		 * Tests
		 */
		// check numbers of 1
		assertThat(f.getCount(1), is(3L));
		assertThat(f.getCumPct(0), is(0.2));
		assertThat(f.getPct(new Integer(1)), is(0.6));
		assertThat(f.getCumPct(-2), is(0.0));
		assertThat(f.getCumPct(10), is(1.0));
	}

	@Test
	public void computeFrequencyDistributionStringTest() throws Exception {
		Frequency f = new Frequency();
		f.addValue("one");
		f.addValue("One");
		f.addValue("oNe");
		f.addValue("Z");
		assertThat(f.getCount("one"), is(1L)); // displays 1
		assertThat(f.getCumPct("Z"), is(0.5)); // displays 0.5
		assertThat(f.getCumPct("Ot"), is(0.25)); // displays 0.25
	}

	@Test
	public void computeFrequencyDistributionStringNoSensitiveTest() throws Exception {
		Frequency f = new Frequency(String.CASE_INSENSITIVE_ORDER);
		f.addValue("one");
		f.addValue("One");
		f.addValue("oNe");
		f.addValue("Z");
		assertThat(f.getCount("one"), is(3L)); // displays 3
		assertThat(f.getCumPct("z"), is(1.0)); // displays 1
		assertThat(f.getPct("one"), is(0.75)); // 75%
	}

	@Test
	public void getPercentageOfEachAttributeInShapefile() throws Exception {
		DataSource dataSource = DefaultDataSource.getInstance().createDefaultDataSource();
		Data data = dataSource.getDataSet().get("vegtype_2000.shp");

		if (data == null)
			throw new RuntimeException("data is null!");

		Frequency dataFrequency = dataAnalysis.createFrequency(data);

		assertThat(dataFrequency.getPct(-127.0), is(0.010238907849829351));
		assertThat(dataFrequency.getPct(1.0), is(0.4426621160409556));
		assertThat(dataFrequency.getPct(2.0), is(0.002389078498293515));
		assertThat(dataFrequency.getPct(3.0), is(0.019795221843003412));
		assertThat(dataFrequency.getPct(9.0), is(0.2501706484641638));
		assertThat(dataFrequency.getPct(10.0), is(0.2651877133105802));
		assertThat(dataFrequency.getPct(12.0), is(0.00955631399317406));

		assertThat(dataFrequency.getUniqueCount(), is(7));
		assertThat(dataFrequency.getSumFreq(), is(2930L)); // number estimated
															// of rows

		List<Double> values = new ArrayList<>();
		dataFrequency.entrySetIterator().forEachRemaining(e -> values.add(dataFrequency.getPct(e.getKey())));
		Double sum = values.parallelStream().mapToDouble(value -> value).sum();

		assertEquals(sum, 1, 0.000000001);
	}

}
