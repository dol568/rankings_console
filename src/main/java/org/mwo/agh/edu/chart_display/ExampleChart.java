package org.mwo.agh.edu.chart_display;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.internal.chartpart.Chart;

import java.io.IOException;
import java.util.Map;

public interface ExampleChart<C extends Chart<?, ?>> {

    C getChart(Map<Object, Object> map);

    void displayChart(Map<Object, Object> map) throws IOException;

    void saveChartToPDF(Map<Object, Object> map) throws IOException;
}