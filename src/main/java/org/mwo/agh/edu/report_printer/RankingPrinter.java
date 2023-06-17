package org.mwo.agh.edu.report_printer;

import java.util.Map;

public abstract class RankingPrinter {

    private static int nextNumber = 1;

    public final void printReport(Map<Object, Object> map) {
        printHeader(map);
        printBody(map);
    }

    protected abstract void printHeader(Map<Object, Object> map);

    protected void printBody(Map<Object, Object> map) {
        String line = "+-----+---------------------------+------------+";
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            System.out.printf("| %-3s | %-25s | %10s |%n", nextNumber++, entry.getKey(), entry.getValue());
            System.out.println(line);
        }
    }
}
