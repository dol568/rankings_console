package org.mwo.agh.edu.ranking;

import org.mwo.agh.edu.model.Spreadsheet;

import java.util.Map;

public interface Report {

    Map<Object, Object> getReport(Spreadsheet spreadsheet);
}
