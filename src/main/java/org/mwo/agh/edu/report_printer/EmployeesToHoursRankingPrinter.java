package org.mwo.agh.edu.report_printer;

import org.mwo.agh.edu.ranking.EmployeesToHoursRanking;

import java.util.Map;

public class EmployeesToHoursRankingPrinter extends RankingPrinter {

    @Override
    protected void printHeader(Map<Object, Object> map) {
        String line = "+-----+---------------------------+------------+";
        System.out.println(line);
        System.out.printf("| %-44s |%n", EmployeesToHoursRanking.SHORT_TITLE);
        System.out.println(line);
        System.out.printf("| %-3s | %-25s | %10s |%n", "#", EmployeesToHoursRanking.COL1_NAME, EmployeesToHoursRanking.COL2_NAME);
        System.out.println(line);
    }
}
