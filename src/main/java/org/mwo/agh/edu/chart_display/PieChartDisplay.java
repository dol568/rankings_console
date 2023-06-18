package org.mwo.agh.edu.chart_display;

import org.knowm.xchart.*;
import org.knowm.xchart.style.PieStyler;
import org.mwo.agh.edu.model.Person;
import org.mwo.agh.edu.ranking.EmployeesToHoursRanking;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class PieChartDisplay implements ExampleChart<PieChart> {

    private PieChart createChart(Map<Object, Object> map) {
        PieChartDisplay exampleChart = new PieChartDisplay();
        return exampleChart.getChart(map);
    }

    public void displayChart(Map<Object, Object> map) {
        PieChart pieChart = createChart(map);
        new SwingWrapper<>(pieChart).displayChart();
    }

    public void saveChartToPDF(Map<Object, Object> map) throws IOException {
        PieChart pieChart = createChart(map);
        new SwingWrapper<>(pieChart).displayChart();
        VectorGraphicsEncoder.saveVectorGraphic(pieChart, EmployeesToHoursRanking.SHORT_TITLE, VectorGraphicsEncoder.VectorGraphicsFormat.PDF);
    }

    @Override
    public PieChart getChart(Map<Object, Object> map) {

        PieChart chart =
                new PieChartBuilder().width(1600).height(1000).title(EmployeesToHoursRanking.FULL_TITLE).build();

        Color[] sliceColors =
                new Color[]{
                        new Color(224, 68, 14),
                        new Color(230, 105, 62),
                        new Color(236, 143, 110),
                        new Color(243, 180, 159),
                        new Color(246, 199, 182)
                };
        chart.getStyler().setSeriesColors(sliceColors);
        chart.getStyler().setLabelType(PieStyler.LabelType.Value);
        chart.getStyler().setDecimalPattern("#0.0h");
        chart.getStyler().setToolTipsEnabled(true);
//            chart.getStyler().setToolTipsAlwaysVisible(true);
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            chart.addSeries(String.valueOf((Person) key), (double) val);
        }
        return chart;
    }
}