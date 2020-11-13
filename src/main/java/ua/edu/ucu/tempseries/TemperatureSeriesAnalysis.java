package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    protected double[] temp_arr;
    protected int counter;

    public TemperatureSeriesAnalysis() {
        temp_arr = new double[]{};
        counter = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this();
        counter = addTemps(temperatureSeries);
    }

    public double average() {
        dontAllowEmptyArr();
        double total = temp_arr[0];
        for (int i = 1; i < counter; i++)
            total += temp_arr[i];
        return total / counter;
    }

    public double deviation() {
        double sum = 0;
        double mean = average();
        for (int i = 0; i < counter; i++)
            sum += Math.pow(temp_arr[i] - mean, 2);
        return Math.sqrt(sum / counter);
    }

    public double min() {
        dontAllowEmptyArr();
        double min_temp = temp_arr[0];
        for (int i = 1; i < counter; i++)
            if (min_temp > temp_arr[i])
                min_temp = temp_arr[i];
        return min_temp;
    }

    public double max() {
        dontAllowEmptyArr();
        double max_temp = temp_arr[0];
        for (int i = 1; i < counter; i++)
            if (max_temp < temp_arr[i])
                max_temp = temp_arr[i];
        return max_temp;
    }

    public double findTempClosestToZero() {
        dontAllowEmptyArr();
        double closest_temp = temp_arr[0];
        for (int i = 0; i < counter; i++)
            if (Math.abs(closest_temp) > Math.abs(temp_arr[i]) || (Math.abs(closest_temp) == Math.abs(temp_arr[i]) &&
                    temp_arr[i] > closest_temp))
                closest_temp = temp_arr[i];
        return closest_temp;
    }

    public double findTempClosestToValue(double tempValue) {
        dontAllowEmptyArr();
        double closest_to = temp_arr[0];
        for (int i = 1; i < counter; i++)
            if (Math.abs(closest_to - tempValue) > Math.abs(temp_arr[i] - tempValue) || (Math.abs(closest_to -
                    tempValue) == Math.abs(temp_arr[i] - tempValue) && temp_arr[i] > closest_to))
                closest_to = temp_arr[i];
        return closest_to;
    }

    public double[] findTempsLessThan(double tempValue) {
        double[] array = Arrays.copyOfRange(temp_arr, 0, counter);
        return Arrays.stream(array).filter(x -> x < tempValue).toArray();
    }

    public double[] findTempsGreaterThan(double tempValue) {
        double[] array = Arrays.copyOfRange(temp_arr, 0, counter);
        return Arrays.stream(array).filter(x -> x >= tempValue).toArray();
    }

    public TempSummaryStatistics summaryStatistics() {
        dontAllowEmptyArr();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp : temps)
            if (temp < -273)
                throw new InputMismatchException();
        if (temp_arr.length == 0) {
            temp_arr = temps.clone();
            return counter = temp_arr.length;
        }
        for (double temp : temps) {
            if (counter == temp_arr.length) {
                double[] buffer = temp_arr.clone();
                temp_arr = new double[2 * counter];
                System.arraycopy(buffer, 0, temp_arr, 0, buffer.length);
            }
            temp_arr[counter++] = temp;
        }
        return counter;
    }

    public double[] getTemp_arr() {
        return Arrays.copyOfRange(temp_arr, 0, counter);
    }

    protected void dontAllowEmptyArr() {
        if (counter == 0)
            throw new IllegalArgumentException();
    }
}
