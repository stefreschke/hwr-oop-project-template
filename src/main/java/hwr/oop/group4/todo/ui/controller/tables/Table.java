package hwr.oop.group4.todo.ui.controller.tables;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private final String titleRowFormat;
    private final String rowFormat;
    private final Row titleRow;
    private final List<Row> rows = new ArrayList<>();

    public Table(List<ColumnConfig> columns) {
        rowFormat = getRowFormat(columns, false);
        titleRowFormat = getRowFormat(columns, true);
        titleRow = getTitleRow(columns);
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    private String getRowFormat(List<ColumnConfig> columns, boolean leftAligned) {
        final StringBuilder rowStringBuilder = new StringBuilder();
        columns.forEach(column -> {
            rowStringBuilder.append("| %");
            if (leftAligned) {
                rowStringBuilder.append("-");
            }
            rowStringBuilder.append(column.getLength())
                    .append(".")
                    .append(column.getLength())
                    .append("s ");
        });
        rowStringBuilder.append("|%n");

        return rowStringBuilder.toString();
    }

    private Row getTitleRow(List<ColumnConfig> columns) {
        final List<String> titles = new ArrayList<>();
        columns.forEach(column -> titles.add(column.getTile()));
        return new Row(titles);
    }

    @Override
    public String toString() {
        final StringBuilder tableStringBuilder = new StringBuilder();
        final String titles = titleRow.toString(titleRowFormat);
        final String underline = ("=").repeat(titles.length()-System.lineSeparator().length());
        tableStringBuilder.append(titles)
                .append(underline)
                .append(System.lineSeparator());

        rows.forEach(row -> tableStringBuilder.append(row.toString(rowFormat)));

        return tableStringBuilder.toString();
    }
}
