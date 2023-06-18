package org.mwo.agh.edu.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.mwo.agh.edu.model.Activity;
import org.mwo.agh.edu.model.Person;
import org.mwo.agh.edu.model.Project;
import org.mwo.agh.edu.model.Spreadsheet;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class XLSReader {

    private final Serializer serializer;

    public XLSReader(Serializer serializer) {
        this.serializer = serializer;
    }

    public void readWorkbook(String dir) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet();

        for (File file : listFiles(dir)) {
            Workbook wb = new HSSFWorkbook(new FileInputStream(file));
            String fileName = file.getName();
            String surname = fileName.substring(0, fileName.indexOf("_"));
            String name = fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("."));
            Person person = null;

            for (Person person1 : spreadsheet.getPersons()) {
                if (person1.getName().equals(name) && person1.getSurname().equals(surname)) {
                    person = person1;
                    break;
                }
            }
            if (person == null) {
                person = new Person(name, surname);
                spreadsheet.addPerson(person);
            }

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                String projectName = sheet.getSheetName();
                Project project = null;
                for (Project p : person.getProjects()) {
                    if (p.getName().equals(projectName)) {
                        project = p;
                        break;
                    }
                }

                if (project == null) {
                    project = new Project(projectName);
                    person.addProject(project);
                }

                Iterator<Row> rows = sheet.iterator();

                if (rows.hasNext()) rows.next();

                while (rows.hasNext()) {
                    Row row = rows.next();
                    Cell dateCell = row.getCell(0);
                    Cell descriptionCell = row.getCell(1);
                    Cell durationCell = row.getCell(2);

                    if (dateCell == null || descriptionCell == null || durationCell == null)
                        continue;

                    LocalDate date;
                    if (dateCell.getCellType() == CellType.NUMERIC)
                        date = dateCell.getLocalDateTimeCellValue().toLocalDate();
                    else
                        continue;

                    String description;
                    if (descriptionCell.getCellType() == CellType.STRING)
                        description = descriptionCell.getStringCellValue();
                    else
                        continue;

                    double duration;
                    if (durationCell.getCellType() == CellType.NUMERIC)
                        duration = durationCell.getNumericCellValue();
                    else
                        continue;

                    project.addActivity(new Activity(date, description, duration));
                }
            }
        }
        this.serializer.serialize(spreadsheet);
    }

    private List<File> listFiles(String directory) throws IOException {
        if (directory == null)
            return Collections.emptyList();

        List<File> fileList = new ArrayList<>();
        File[] files = new File(directory).listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory())
                    fileList.addAll(listFiles(f.getPath()));
                else if (f.isFile() && f.getName().matches("[a-zA-Z]+_[a-zA-Z]+\\.xls"))
                    fileList.add(f);
            }
        }
        return fileList;
    }
}
