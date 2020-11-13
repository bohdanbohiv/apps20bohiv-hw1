package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int MIN_TEMP = -273;
    private static final double DELTA = 0.00001;
    private double[] tempArr;
    private int counter;

    public TemperatureSeriesAnalysis() {
        tempArr = new double[]{};
        counter = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this();
        counter = addTemps(temperatureSeries);
    }

    public double average() {
        dontAllowEmptyArr();
        double total = tempArr[0];
        for (int i = 1; i < counter; i++) {
            total += tempArr[i];
        }
        return total / counter;
    }

    public double deviation() {
        double sum = 0;
        double mean = average();
        for (int i = 0; i < counter; i++) {
            sum += Math.pow(tempArr[i] - mean, 2);
        }
        return Math.sqrt(sum / counter);
    }

    public double min() {
        dontAllowEmptyArr();
        double minTemp = tempArr[0];
        for (int i = 1; i < counter; i++) {
            if (minTemp > tempArr[i]) {
                minTemp = tempArr[i];
            }
        }
        return minTemp;
    }

    public double max() {
        dontAllowEmptyArr();
        double maxTemp = tempArr[0];
        for (int i = 1; i < counter; i++) {
            if (maxTemp < tempArr[i]) {
                maxTemp = tempArr[i];
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        dontAllowEmptyArr();
        double closestTemp = tempArr[0];
        for (int i = 0; i < counter; i++) {
            if (Math.abs(closestTemp) > Math.abs(tempArr[i]) || (Math.abs(
                    Math.abs(closestTemp) - Math.abs(tempArr[i])) < DELTA
                    && tempArr[i] > closestTemp)) {
                closestTemp = tempArr[i];
            }
        }
        return closestTemp;
    }

    public double findTempClosestToValue(double tempValue) {
        dontAllowEmptyArr();
        double closestTo = tempArr[0];
        for (int i = 1; i < counter; i++) {
            if (Math.abs(closestTo - tempValue) > Math.abs(tempArr[i]
                    - tempValue) || (Math.abs(Math.abs(closestTo - tempValue)
                    - Math.abs(tempArr[i] - tempValue)) < DELTA && tempArr[i]
                    > closestTo)) {
                closestTo = tempArr[i];
            }
        }
        return closestTo;
    }

    public double[] findTempsLessThan(double tempValue) {
        double[] array = Arrays.copyOfRange(tempArr, 0, counter);
        return Arrays.stream(array).filter(x -> x < tempValue).toArray();
    }

    public double[] findTempsGreaterThan(double tempValue) {
        double[] array = Arrays.copyOfRange(tempArr, 0, counter);
        return Arrays.stream(array).filter(x -> x >= tempValue).toArray();
    }

    public TempSummaryStatistics summaryStatistics() {
        dontAllowEmptyArr();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < MIN_TEMP) {
                throw new InputMismatchException();
            }
        }
        if (tempArr.length == 0) {
            tempArr = temps.clone();
            counter = tempArr.length;
            return counter;
        }
        for (double temp : temps) {
            if (counter == tempArr.length) {
                double[] buffer = tempArr.clone();
                tempArr = new double[2 * counter];
                System.arraycopy(buffer, 0, tempArr, 0, buffer.length);
            }
            tempArr[counter++] = temp;
        }
        return counter;
    }

    public double[] getTempArr() {
        return Arrays.copyOfRange(tempArr, 0, counter);
    }

    protected void dontAllowEmptyArr() {
        if (counter == 0) {
            throw new IllegalArgumentException();
        }
    }
}
