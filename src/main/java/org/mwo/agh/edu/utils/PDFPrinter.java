package org.mwo.agh.edu.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class PDFPrinter {

    private static int nextNumber = 1;
    private final String columnName1;
    private final String columnName2;
    private final String titleColumnName;

    public PDFPrinter(String columnName1, String columnName2, String titleColumnName) {
        this.columnName1 = columnName1;
        this.columnName2 = columnName2;
        this.titleColumnName = titleColumnName;
    }

    public void print(Map<Object, Object> map, String filename) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));

        document.open();

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table, map);

        document.add(createTitle());
        document.add(table);
        document.close();
    }

    private Paragraph createTitle() {
        Paragraph title = new Paragraph(titleColumnName, new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10f);
        return title;
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell(createHeaderCell("#"));
        table.addCell(createHeaderCell(columnName1));
        table.addCell(createHeaderCell(columnName2));
    }

    private PdfPCell createHeaderCell(String headerText) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(headerText));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return header;
    }

    private void addRows(PdfPTable table, Map<Object, Object> map) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            table.addCell(createCell(String.valueOf(nextNumber++)));
            table.addCell(createCell(key.toString()));
            table.addCell(createCell(value.toString()));
        }
    }

    private PdfPCell createCell(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}