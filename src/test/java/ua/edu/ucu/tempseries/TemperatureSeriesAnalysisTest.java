package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private final double delta = 0.00001;

    // test average
    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, delta);
    }

    // test deviation
    @Test
    public void testDeviationWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {9, 2, 5, 4, 12, 7, 8, 11, 9, 3, 7, 4, 12, 5, 4, 10, 9, 6, 9, 4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = Math.sqrt(8.9);

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, delta);
    }

    // test min
    @Test
    public void testMinWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.min();
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, delta);
    }

    // test max
    @Test
    public void testMaxWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.max();
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, delta);
    }

    // test findTempClosestToZero
    @Test
    public void testFindTempClosestToZeroWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, delta);
    }

    @Test
    public void testFindTempClosestToZeroWithEquallyCloseElementsArray() {
        double[] temperatureSeries1 = {3.0, -5.0, 1.0, 5.0, -0.2, 0.2};
        TemperatureSeriesAnalysis seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries1);
        double expResult = 0.2;

        double actualResult1 = seriesAnalysis1.findTempClosestToZero();

        assertEquals(expResult, actualResult1, delta);

        double[] temperatureSeries2 = {3.0, -5.0, 1.0, 5.0, 0.2, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);

        double actualResult2 = seriesAnalysis2.findTempClosestToZero();

        assertEquals(expResult, actualResult2, delta);
    }

    // test findTempClosestToValue
    @Test
    public void testFindTempClosestToValueWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double tempValue = 3.6;
        double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);

        assertEquals(expResult, actualResult, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double tempValue = 3.6;
        seriesAnalysis.findTempClosestToValue(tempValue);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.0;

        double tempValue = 3.6;
        double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);

        assertEquals(expResult, actualResult, delta);
    }

    @Test
    public void testFindTempClosestToValueWithEquallyCloseElementsArray() {
        double[] temperatureSeries1 = {3.0, -5.0, 1.0, 4.2, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries1);
        double expResult = 4.2;

        double tempValue = 3.6;
        double actualResult1 = seriesAnalysis1.findTempClosestToValue(tempValue);

        assertEquals(expResult, actualResult1, delta);

        double[] temperatureSeries2 = {4.2, -5.0, 1.0, 3.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);

        double actualResult2 = seriesAnalysis2.findTempClosestToValue(tempValue);

        assertEquals(expResult, actualResult2, delta);
    }

    // test findTempsLessThan
    @Test
    public void testFindTempsLessThanWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {};

        double tempValue = 3.6;
        double[] actualResult = seriesAnalysis.findTempsLessThan(tempValue);

        assertArrayEquals(expResult, actualResult, delta);
    }

    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 3.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, -5.0, 1.0};

        double tempValue = 3.6;
        double[] actualResult = seriesAnalysis.findTempsLessThan(tempValue);

        assertArrayEquals(expResult, actualResult, delta);
    }

    // test findTempsGreaterThan
    @Test
    public void testFindTempsGreaterThanWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {};

        double tempValue = 3.6;
        double[] actualResult = seriesAnalysis.findTempsGreaterThan(tempValue);

        assertArrayEquals(expResult, actualResult, delta);
    }

    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 3.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {5.0, 3.6};

        double tempValue = 3.6;
        double[] actualResult = seriesAnalysis.findTempsGreaterThan(tempValue);

        assertArrayEquals(expResult, actualResult, delta);
    }

    // test summaryStatistics
    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptySet() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.summaryStatistics();
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics summaryStatistics = seriesAnalysis.summaryStatistics();

        double expAvg = seriesAnalysis.average();
        double actualAvg = summaryStatistics.getAvgTemp();
        assertEquals(expAvg, actualAvg, delta);

        double expDev = seriesAnalysis.deviation();
        double actualDev = summaryStatistics.getDevTemp();
        assertEquals(expDev, actualDev, delta);

        double expMin = seriesAnalysis.min();
        double actualMin = summaryStatistics.getMinTemp();
        assertEquals(expMin, actualMin, delta);

        double expMax = seriesAnalysis.max();
        double actualMax = summaryStatistics.getMaxTemp();
        assertEquals(expMax, actualMax, delta);
    }

    //test addTemps
    @Test
    public void testAddTempsToEmpty() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertEquals(temperatureSeries.length, seriesAnalysis.addTemps(temperatureSeries));

        double[] actualArr = seriesAnalysis.getTemp_arr();
        assertArrayEquals(temperatureSeries, actualArr, delta);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(temperatureSeries.length + 2, seriesAnalysis.addTemps(0.2, -0.2));

        double[] expArr = {3.0, -5.0, 1.0, 5.0, 0.2, -0.2};
        double[] actualArr = seriesAnalysis.getTemp_arr();
        assertArrayEquals(expArr, actualArr, delta);
    }

    // test constructors
    @Test(expected = IllegalArgumentException.class)
    public void testDefaultConstructor() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        seriesAnalysis.average();
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructor() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, -274};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] expArr = {};
        double[] actualArr = seriesAnalysis.getTemp_arr();
        assertArrayEquals(expArr, actualArr, delta);
    }
}
