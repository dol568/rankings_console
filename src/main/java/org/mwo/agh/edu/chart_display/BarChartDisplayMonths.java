package org.mwo.agh.edu.chart_display;

import org.knowm.xchart.*;
import org.mwo.agh.edu.ranking.MonthsToHoursRanking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.knowm.xchart.style.Styler.LegendPosition.InsideNW;

public class BarChartDisplayMonths implements ExampleChart<CategoryChart> {

    @Override
    public CategoryChart getChart(Map<Object, Object> map) {

        CategoryChart chart =
                new CategoryChartBuilder()
                        .width(1600)
                        .height(1000)
                        .title(MonthsToHoursRanking.FULL_TITLE)
                        .xAxisTitle("Miesiac w roku")
                        .yAxisTitle("Liczba godzin")
                        .build();

        chart.getStyler().setLegendPosition(InsideNW);
        chart.getStyler().setLabelsVisible(false);
        chart.getStyler().setPlotGridLinesVisible(false);

        List<String> keys = new ArrayList<>();
        List<Double> vals = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();

            keys.add((String) key);
            vals.add((Double) val);
        }
        chart.addSeries(MonthsToHoursRanking.SHORT_TITLE, keys, vals);

        return chart;
    }

    public void displayChart(Map<Object, Object> map) {
        CategoryChart categoryChart = createChart(map);
        new SwingWrapper<>(categoryChart).displayChart();
    }

    public void saveChartToPDF(Map<Object, Object> map) throws IOException {
        CategoryChart categoryChart = createChart(map);
        new SwingWrapper<>(categoryChart).displayChart();
        VectorGraphicsEncoder.saveVectorGraphic(categoryChart, MonthsToHoursRanking.SHORT_TITLE, VectorGraphicsEncoder.VectorGraphicsFormat.PDF);
    }

    private CategoryChart createChart(Map<Object, Object> map) {
        ExampleChart<CategoryChart> exampleChart = new BarChartDisplayMonths();
        return exampleChart.getChart(map);
    }

}