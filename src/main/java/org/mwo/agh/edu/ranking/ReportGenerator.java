package org.mwo.agh.edu.ranking;

import org.mwo.agh.edu.utils.Serializer;
import org.mwo.agh.edu.model.Spreadsheet;

import java.io.FileNotFoundException;
import java.util.*;

public class ReportGenerator {

    private final Report strategy;
    private final Serializer serializer;

    public ReportGenerator(Report strategy, Serializer serializer) {
        this.strategy = strategy;
        this.serializer = serializer;
    }

    public Map<Object, Object> generateReport(String direction, String limit) throws FileNotFoundException {
        Spreadsheet spreadsheet;
        try{
            spreadsheet = this.serializer.unserialize();
        } catch (Exception e) {
            throw new FileNotFoundException("Brak plikow XLS");
        }
        Map<Object, Object> notSorted = this.strategy.getReport(spreadsheet);
        return sortMap(notSorted, direction, limit);
    }

    private Map<Object, Object> sortMap(Map<Object, Object> map, String direction, String limit) {
        List<Map.Entry<Object, Object>> sorted = new ArrayList<>(map.entrySet());
        sorted.sort(Comparator.comparingDouble(d -> (Double) d.getValue()));
        if (direction.equalsIgnoreCase("DESC")) {
            Collections.reverse(sorted);
        }

        int limitParsed = Integer.parseInt(limit);

        if (limitParsed <= 0) limitParsed = 0;

        return limitMap(sorted, limitParsed);
    }

    private Map<Object, Object> limitMap(List<Map.Entry<Object, Object>> entries, int limit) {
        Map<Object, Object> limitedMap = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<Object, Object> entry : entries) {
            if (count >= limit) {
                break;
            }
            limitedMap.put(entry.getKey(), entry.getValue());
            count++;
        }
        return limitedMap;
    }
}
