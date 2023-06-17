package org.mwo.agh.edu.ranking;

import org.mwo.agh.edu.model.Activity;
import org.mwo.agh.edu.model.Person;
import org.mwo.agh.edu.model.Project;
import org.mwo.agh.edu.model.Spreadsheet;

import java.util.HashMap;
import java.util.Map;

public class EmployeesToHoursRanking implements Report {

    public static final String FULL_TITLE = "RANKING PRACOWNIKOW WG PRZEPRACOWANYCH GODZIN WE WSZYSTKICH PROJEKTACH";
    public static final String SHORT_TITLE = "RANKING PRACOWNIKOW";
    public static final String COL1_NAME = "IMIE I NAZWISKO";
    public static final String COL2_NAME = "CZAS[h]";

    @Override
    public Map<Object, Object> getReport(Spreadsheet spreadsheet) {
        Map<Object, Object> personToHours = new HashMap<>();
        for (Person s : spreadsheet.getPersons()) {
            double sum = 0.0d;
            for (Project p : s.getProjects())
                for (Activity a : p.getActivities())
                    sum += (double) a.duration();

            personToHours.put(s, sum);
        }
        return personToHours;
    }
}
