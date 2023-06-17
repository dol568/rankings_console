package org.mwo.agh.edu.report_printer;

import org.mwo.agh.edu.ranking.DaysToHoursRanking;

import java.util.Map;

public class DaysToHoursRankingPrinter extends RankingPrinter {

    @Override
    protected void printHeader(Map<Object, Object> map) {
        String line = "+-----+---------------------------+------------+";
        System.out.println(line);
        System.out.printf("| %-44s |%n", DaysToHoursRanking.SHORT_TITLE);
        System.out.println(line);
        System.out.printf("| %-3s | %-25s | %10s |%n","#", DaysToHoursRanking.COL1_NAME, DaysToHoursRanking.COL2_NAME);
        System.out.println(line);
    }
}
