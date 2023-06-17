package org.mwo.agh.edu.chart_display;

import org.knowm.xchart.*;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat;
import org.mwo.agh.edu.ranking.DaysToHoursRanking;
import org.mwo.agh.edu.ranking.EmployeesToHoursRanking;

import java.io.IOException;
import java.util.*;

import static org.knowm.xchart.style.Styler.LegendPosition.*;

public class BarChartDisplayDays implements ExampleChart<CategoryChart> {

        public void displayChart(Map<Object, Object> map) {
            CategoryChart categoryChart = createChart(map);
            new SwingWrapper<>(categoryChart).displayChart();
        }

        public void saveChartToPDF(Map<Object, Object> map) throws IOException {
            CategoryChart categoryChart = createChart(map);
            new SwingWrapper<>(categoryChart).displayChart();
            VectorGraphicsEncoder.saveVectorGraphic(categoryChart, EmployeesToHoursRanking.SHORT_TITLE, VectorGraphicsEncoder.VectorGraphicsFormat.PDF);
        }

        private CategoryChart createChart(Map<Object, Object> map) {
            ExampleChart<CategoryChart> exampleChart = new BarChartDisplayDays();
            return exampleChart.getChart(map);
        }

    @Override
    public CategoryChart getChart(Map<Object, Object> map) {

        CategoryChart chart =
                new CategoryChartBuilder()
                        .width(1300)
                        .height(800)
                        .title(DaysToHoursRanking.FULL_TITLE)
                        .xAxisTitle("Dzien w roku")
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
        chart.addSeries(DaysToHoursRanking.SHORT_TITLE,keys, vals);

        return chart;
    }

}