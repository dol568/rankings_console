package org.mwo.agh.edu.utils;

import com.itextpdf.text.DocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.mwo.agh.edu.model.Person;
import org.mwo.agh.edu.ranking.NumberToStringConverter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class XLSWriter {

    private static int nextNumber = 1;
    private final String columnName1;
    private final String columnName2;
    private final String titleColumnName;
    NumberToStringConverter converter = new NumberToStringConverter();

    public XLSWriter(String columnName1, String columnName2, String titleColumnName) {
        this.columnName1 = columnName1;
        this.columnName2 = columnName2;
        this.titleColumnName = titleColumnName;
    }

    public void writer(Map<Object, Object> map, String filename) throws IOException, DocumentException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        String date;
        int rowNum = 0;

        Row titleRow = sheet.createRow(rowNum++);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(titleColumnName);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

        Row firstRow = sheet.createRow(rowNum++);
        Cell idHeaderCell = firstRow.createCell(0);
        Cell keyHeaderCell = firstRow.createCell(1);
        Cell valueHeaderCell = firstRow.createCell(2);
        idHeaderCell.setCellValue("#");
        keyHeaderCell.setCellValue(columnName1);
        valueHeaderCell.setCellValue(columnName2);

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Row row = sheet.createRow(rowNum++);

            Object key = entry.getKey();
            Object value = entry.getValue();

            Cell idCell = row.createCell(0);
            idCell.setCellValue(nextNumber++);

            Cell keyCell = row.createCell(1);
            if (key instanceof String) {
                date = converter.stringToDate((String) key);
                keyCell.setCellValue(date);
            } else if (key instanceof Person)
                keyCell.setCellValue(key.toString());

            Cell valueCell = row.createCell(2);
            if (value instanceof Double)
                valueCell.setCellValue((Double) value);
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, columnWidth + 1000); // Adjust width by 1000 units
        }

        FileOutputStream outputStream = new FileOutputStream(filename + ".xls");
        workbook.write(outputStream);
        workbook.close();
    }
}

