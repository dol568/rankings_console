package org.mwo.agh.edu;

import org.apache.commons.cli.*;
import org.mwo.agh.edu.chart_display.BarChartDisplayDays;
import org.mwo.agh.edu.chart_display.BarChartDisplayMonths;
import org.mwo.agh.edu.chart_display.ExampleChart;
import org.mwo.agh.edu.chart_display.PieChartDisplay;
import org.mwo.agh.edu.ranking.DaysToHoursRanking;
import org.mwo.agh.edu.ranking.MonthsToHoursRanking;
import org.mwo.agh.edu.ranking.EmployeesToHoursRanking;
import org.mwo.agh.edu.ranking.ReportGenerator;
import org.mwo.agh.edu.report_printer.DaysToHoursRankingPrinter;
import org.mwo.agh.edu.report_printer.EmployeesToHoursRankingPrinter;
import org.mwo.agh.edu.report_printer.MonthsToHoursRankingPrinter;
import org.mwo.agh.edu.report_printer.RankingPrinter;
import org.mwo.agh.edu.utils.PDFPrinter;
import org.mwo.agh.edu.utils.Serializer;
import org.mwo.agh.edu.utils.XLSReader;
import org.mwo.agh.edu.utils.XLSWriter;

import java.io.IOException;
import java.util.Map;

public class Demo {

    private static final Option FILES = new Option("f", "files", true, "Choose directory with files");
    private static final Option EMPLOYEES = new Option("e", "employeesRanking", true, "Get employees to hours ranking");
    private static final Option MONTHS = new Option("m", "monthsRanking", true, "Get months to hours ranking");
    private static final Option DAYS = new Option("d", "daysRanking", true, "Get days to hours ranking");
    private static final Option HELP = new Option("h", "help", false, "Display usage help");

    public static void main(String[] args) throws IOException {

        Map<Object, Object> map;
        Serializer serializer = new Serializer();
        XLSReader xlsReader = new XLSReader(serializer);
        ReportGenerator reportGenerator;
        RankingPrinter printer;
        PDFPrinter pdfPrinter;
        XLSWriter xlsWriter;
        ExampleChart chart;

        CommandLineParser cmp = new DefaultParser();

        Options options = new Options();
        options.addOption(FILES);
        options.addOption(EMPLOYEES);
        options.addOption(MONTHS);
        options.addOption(DAYS);
        options.addOption(HELP);

        try {
            CommandLine cl = cmp.parse(options, args);

            if (cl.hasOption(FILES.getLongOpt())) {

                xlsReader.readWorkbook(args[1]);

            } else if (cl.hasOption(EMPLOYEES.getLongOpt())) {

                reportGenerator = new ReportGenerator(new EmployeesToHoursRanking(), serializer);
                printer = new EmployeesToHoursRankingPrinter();
                map = reportGenerator.generateReport(args[1], args[2]);
                chart = new PieChartDisplay();
                xlsWriter = new XLSWriter(EmployeesToHoursRanking.COL1_NAME, EmployeesToHoursRanking.COL2_NAME, EmployeesToHoursRanking.SHORT_TITLE);
                pdfPrinter = new PDFPrinter(EmployeesToHoursRanking.COL1_NAME, EmployeesToHoursRanking.COL2_NAME, EmployeesToHoursRanking.FULL_TITLE);

                if (args.length == 3) {
                    printer.printReport(map);
                } else if (args.length == 4 && args[3].equalsIgnoreCase("CHART")) {
                    chart.displayChart(map);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("CHART")
                        && args[4].equalsIgnoreCase("PDF")) {
                    chart.saveChartToPDF(map);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("XLS")) {
                    xlsWriter.writer(map, args[4]);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("PDF")) {
                    pdfPrinter.print(map, args[4]);
                }

            } else if (cl.hasOption(MONTHS.getLongOpt())) {

                reportGenerator = new ReportGenerator(new MonthsToHoursRanking(), serializer);
                printer = new MonthsToHoursRankingPrinter();
                map = reportGenerator.generateReport(args[1], args[2]);
                chart = new BarChartDisplayMonths();
                xlsWriter = new XLSWriter(MonthsToHoursRanking.COL1_NAME, MonthsToHoursRanking.COL2_NAME, MonthsToHoursRanking.SHORT_TITLE);
                pdfPrinter = new PDFPrinter(MonthsToHoursRanking.COL1_NAME, MonthsToHoursRanking.COL2_NAME, MonthsToHoursRanking.FULL_TITLE);

                if (args.length == 3) {
                    printer.printReport(map);
                } else if (args.length == 4 && args[3].equalsIgnoreCase("CHART")) {
                    chart.displayChart(map);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("CHART")
                        && args[4].equalsIgnoreCase("PDF")) {
                    chart.saveChartToPDF(map);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("XLS")) {
                    xlsWriter.writer(map, args[4]);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("PDF")) {
                    pdfPrinter.print(map, args[4]);
                }

            } else if (cl.hasOption(DAYS.getLongOpt())) {

                reportGenerator = new ReportGenerator(new DaysToHoursRanking(), serializer);
                printer = new DaysToHoursRankingPrinter();
                map = reportGenerator.generateReport(args[1], args[2]);
                chart = new BarChartDisplayDays();
                xlsWriter = new XLSWriter(DaysToHoursRanking.COL1_NAME, DaysToHoursRanking.COL2_NAME, DaysToHoursRanking.SHORT_TITLE);
                pdfPrinter = new PDFPrinter(DaysToHoursRanking.COL1_NAME, DaysToHoursRanking.COL2_NAME, DaysToHoursRanking.FULL_TITLE);

                if (args.length == 3) {
                    printer.printReport(map);
                } else if (args.length == 4 && args[3].equalsIgnoreCase("CHART")) {
                    chart.displayChart(map);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("CHART")
                        && args[4].equalsIgnoreCase("PDF")) {
                    chart.saveChartToPDF(map);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("XLS")) {
                    xlsWriter.writer(map, args[4]);
                } else if (args.length == 5 && args[3].equalsIgnoreCase("PDF")) {
                    pdfPrinter.print(map, args[4]);
                }

            } else if (cl.hasOption(HELP.getLongOpt())) {
                printCustomHelp();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            printCustomHelp();
        }
    }

    private static void printCustomHelp() {
        String helpMessage = "Usage: java -jar <jar_file_name> [OPTIONS] [ARGUMENTS]\n"
                + "Options:\n"
                + "  -f, --files <directory>                                   Choose directory with files\n"
                + "  -e, --employeesRanking <args>                             Get employees to hours ranking\n"
                + "                         [ASC/DESC] <limit>                 ASC/DESC with nr of rows\n"
                + "                         [ASC/DESC] <limit> CHART           Display chart\n"
                + "                         [ASC/DESC] <limit> CHART PDF       Print chart to PDF file\n"
                + "                         [ASC/DESC] <limit> XLS <filename>  Save table to XLS file\n"
                + "                         [ASC/DESC] <limit> PDF <filename>  Save table to PDF file\n"
                + "  -m, --monthsRanking    <args>                             Get months to hours ranking\n"
                + "                         [ASC/DESC] <limit>                 ASC/DESC with nr of rows\n"
                + "                         [ASC/DESC] <limit> CHART           Display chart\n"
                + "                         [ASC/DESC] <limit> CHART PDF       Print chart to PDF file\n"
                + "                         [ASC/DESC] <limit> XLS <filename>  Save table to XLS file\n"
                + "                         [ASC/DESC] <limit> PDF <filename>  Save table to PDF file\n"
                + "  -d, --daysRanking      <args>                             Get days to hours ranking\n"
                + "                         [ASC/DESC] <limit>                 ASC/DESC with nr of rows\n"
                + "                         [ASC/DESC] <limit> CHART           Display chart\n"
                + "                         [ASC/DESC] <limit> CHART PDF       Print chart to PDF file\n"
                + "                         [ASC/DESC] <limit> XLS <filename>  Save table to XLS file\n"
                + "                         [ASC/DESC] <limit> PDF <filename>  Save table to PDF file\n"
                + "  -h, --help                                                Display usage help\n";
        System.out.println(helpMessage);
    }
}
