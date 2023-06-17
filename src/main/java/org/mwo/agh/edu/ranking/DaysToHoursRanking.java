package org.mwo.agh.edu.ranking;

import org.mwo.agh.edu.model.Activity;
import org.mwo.agh.edu.model.Person;
import org.mwo.agh.edu.model.Project;
import org.mwo.agh.edu.model.Spreadsheet;

import java.util.HashMap;
import java.util.Map;

public class DaysToHoursRanking implements Report {

    public static final String FULL_TITLE = "RANKING DNI WG PRZEPRACOWANYCH GODZIN WE WSZYSTKICH PROJEKTACH PRZEZ WSZYSTKICH PRACOWNIKÓW";
    public static final String SHORT_TITLE = "RANKING DNI";
    public static final String COL1_NAME = "DZIEN W ROKU";
    public static final String COL2_NAME = "CZAS[h]";

    private final NumberToStringConverter numberToStringConverter = new NumberToStringConverter();

    @Override
    public Map<Object, Object> getReport(Spreadsheet spreadsheet) {
        Map<Object, Object> daysToHours = new HashMap<>();
        for (Person s : spreadsheet.getPersons()) {
            for (Project project : s.getProjects()) {
                for (Activity activity : project.getActivities()) {
                    String day = String.valueOf(activity.date().getDayOfMonth());
                    String month = String.valueOf(activity.date().getMonthValue());
                    String year = String.valueOf(activity.date().getYear());
                    String date = day + " " + this.numberToStringConverter.intToStringMonth(month) + " " + year;
                    double duration = activity.duration();
                    if (daysToHours.containsKey(date)) {
                        double add = (double) daysToHours.get(date) + duration;
                        daysToHours.put(date, add);
                    } else daysToHours.put(date, activity.duration());
                }
            }
        }
        return daysToHours;
    }
}
