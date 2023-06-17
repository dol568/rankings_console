package org.mwo.agh.edu.report_printer;

import org.mwo.agh.edu.ranking.MonthsToHoursRanking;

import java.util.Map;

public class MonthsToHoursRankingPrinter extends RankingPrinter {

    @Override
    protected void printHeader(Map<Object, Object> map) {
        String line = "+-----+---------------------------+------------+";
        System.out.println(line);
        System.out.printf("| %-44s |%n", MonthsToHoursRanking.SHORT_TITLE);
        System.out.println(line);
        System.out.printf("| %-3s | %-25s | %10s |%n", "#", MonthsToHoursRanking.COL1_NAME, MonthsToHoursRanking.COL2_NAME);
        System.out.println(line);
    }
}
