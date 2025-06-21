package com.designsql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class ResultSet {
    private ArrayList<String> columns;
    private ArrayList<Object[]> values;

    public ResultSet() {
        columns = new ArrayList<>();
        values = new ArrayList<>();
    }

    public void printTable() {
        int[] colWidths = new int[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            colWidths[i] = columns.get(i).length();
        }
        for (Object[] row : values) {
            for (int i = 0; i < row.length; i++) {
                String cell = row[i] != null ? row[i].toString() : "null";
                if (cell.length() > colWidths[i]) {
                    colWidths[i] = cell.length();
                }
            }
        }

        for (int i = 0; i < columns.size(); i++) {
            System.out.print(String.format("| %-" + colWidths[i] + "s ", columns.get(i)));
        }
        System.out.println("|");

        for (int colWidth : colWidths) {
            System.out.print("+");
            for (int j = 0; j < colWidth + 2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");

        for (Object[] row : values) {
            for (int i = 0; i < row.length; i++) {
                String cell = row[i] != null ? row[i].toString() : "null";
                System.out.print(String.format("| %-" + colWidths[i] + "s ", cell));
            }
            System.out.println("|");
        }
    }
}
