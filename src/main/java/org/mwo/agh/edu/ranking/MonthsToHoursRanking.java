package org.mwo.agh.edu.ranking;

import org.mwo.agh.edu.model.Activity;
import org.mwo.agh.edu.model.Person;
import org.mwo.agh.edu.model.Project;
import org.mwo.agh.edu.model.Spreadsheet;

import java.util.HashMap;
import java.util.Map;

public class MonthsToHoursRanking implements Report {

    public static final String FULL_TITLE = "RANKING MIESIECY WG PRZEPRACOWANYCH GODZIN WE WSZYSTKICH PROJEKTACH PRZEZ WSZYSTKICH PRACOWNIKÓW";
    public static final String SHORT_TITLE = "RANKING MIESIECY";
    public static final String COL1_NAME = "MIESIAC W ROKU";
    public static final String COL2_NAME = "CZAS[h]";

    private final NumberToStringConverter numberToStringConverter = new NumberToStringConverter();

    @Override
    public Map<Object, Object> getReport(Spreadsheet spreadsheet) {
        Map<Object, Object> monthsToHours = new HashMap<>();
        for (Person s : spreadsheet.getPersons()) {
            for (Project project : s.getProjects()) {
                for (Activity activity : project.getActivities()) {
                    String month = String.valueOf(activity.date().getMonthValue());
                    String year = String.valueOf(activity.date().getYear());
                    String date = this.numberToStringConverter.intToStringMonth(month) + " " + year;
                    double duration = activity.duration();
                    if (monthsToHours.containsKey(date)) {
                        double add = (double) monthsToHours.get(date) + duration;
                        monthsToHours.put(date, add);
                    } else monthsToHours.put(date, activity.duration());
                }
            }
        }
        return monthsToHours;
    }
}
