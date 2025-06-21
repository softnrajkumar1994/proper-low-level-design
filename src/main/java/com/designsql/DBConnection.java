// DBConnection.java
package com.designsql;

import java.util.*;

public class DBConnection {
    private static DBConnection dbConnection = null;
    private static DataLake dataLake;

    private DBConnection() {
        dataLake = DataLake.getDataLake();
    }

    public static DBConnection getConnection() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Table createTable(String createQuery) {
        createQuery = createQuery.replace(";", "").replaceAll("\n", "").trim();
        createQuery = createQuery.toLowerCase(Locale.ROOT);
        createQuery = createQuery.split("create table")[1];
        int indexOf = createQuery.indexOf("(");
        String table = createQuery.substring(0, indexOf).trim();

        String columnStr = createQuery.substring(indexOf + 1, createQuery.lastIndexOf(")"));

        String columnsWithDataTypes[] = columnStr.split(",");
        List<Column> columnList = new ArrayList<>();
        for (String columnsWithDataType : columnsWithDataTypes) {
            String spl[] = columnsWithDataType.trim().split(" ");
            Column column = new Column(spl[0], ColumnType.valueOf(spl[1].toUpperCase(Locale.ROOT)));
            columnList.add(column);
        }
        Table t = new Table();
        t.setName(table.trim());
        t.setColumns(columnList);

        dataLake.addTable(t);
        return t;
    }

    public boolean insert(String insertQuery) {
        insertQuery = insertQuery.replace(";", "").replaceAll("\n", "").trim();
        insertQuery = insertQuery.toLowerCase(Locale.ROOT);
        insertQuery = insertQuery.split("insert into")[1];
        int indexOf = insertQuery.indexOf("(");
        String tableStr = insertQuery.substring(0, indexOf).trim();

        String columnStr = insertQuery.substring(indexOf + 1, insertQuery.lastIndexOf(") values")).replace(" ", "");
        String columns[] = columnStr.split(",");
        Table table = dataLake.getTable(tableStr);

        String valuesStr = insertQuery.substring(insertQuery.indexOf("values") + 6).trim();
        if (valuesStr.startsWith("(")) valuesStr = valuesStr.substring(1);
        if (valuesStr.endsWith(")")) valuesStr = valuesStr.substring(0, valuesStr.length() - 1);

        String[] rows = valuesStr.split("\\),\\(");
        for (String rowStr : rows) {
            rowStr = rowStr.replace("'", "");
            String columnValues[] = rowStr.split(",");

            List<Column> defitionsColumn = table.getColumns();
            Row row = new Row();
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < columns.length; i++) {
                map.put(columns[i], columnValues[i].trim());
            }
            List<Object> values = new ArrayList<>();
            for (Column column : defitionsColumn) {
                if (!map.containsKey(column.getColumnName())) {
                    values.add(null);
                } else if (ColumnType.BOOLEAN.equals(column.getDataType())) {
                    values.add(Boolean.parseBoolean(map.get(column.getColumnName())));
                } else if (ColumnType.CHAR.equals(column.getDataType())) {
                    values.add(map.get(column.getColumnName()).charAt(0));
                } else if (ColumnType.INT.equals(column.getDataType())) {
                    values.add(Integer.parseInt(map.get(column.getColumnName())));
                } else {
                    values.add(map.get(column.getColumnName()));
                }
            }
            row.setValues(values);
            if (table.getRows() == null) {
                table.setRows(new ArrayList<>());
            }
            table.getRows().add(row);
        }
        return true;
    }

    public ResultSet select(String query) {
        String originalQuery = query.toLowerCase(Locale.ROOT);
        String columnsString = originalQuery.substring(originalQuery.indexOf("select ") + 6, originalQuery.indexOf(" from")).trim();
        boolean isAllColumns = columnsString.equals("*");

        String tableName = "";
        if (originalQuery.contains(" where ")) {
            tableName = originalQuery.substring(originalQuery.indexOf("from ") + 5, originalQuery.indexOf(" where")).trim();
        } else if (originalQuery.contains(" order by ")) {
            tableName = originalQuery.substring(originalQuery.indexOf("from ") + 5, originalQuery.indexOf(" order by")).trim();
        } else if (originalQuery.contains(" limit ")) {
            tableName = originalQuery.substring(originalQuery.indexOf("from ") + 5, originalQuery.indexOf(" limit")).trim();
        } else {
            tableName = originalQuery.substring(originalQuery.indexOf("from ") + 5).trim();
        }

        Table table = dataLake.getTable(tableName);
        List<Column> columnList = table.getColumns();

        if (!isAllColumns) {
            String[] selectedColumns = columnsString.split(",");
            List<Column> newColumns = new ArrayList<>();
            for (String col : selectedColumns) {
                col = col.trim();
                for (Column column : columnList) {
                    if (column.getColumnName().equals(col)) {
                        newColumns.add(column);
                    }
                }
            }
            columnList = newColumns;
        }

        List<Row> filteredRows = new ArrayList<>(table.getRows());

        if (originalQuery.contains(" where ")) {
            String whereClause = originalQuery.substring(originalQuery.indexOf(" where ") + 7);
            if (whereClause.contains(" order by ")) {
                whereClause = whereClause.substring(0, whereClause.indexOf(" order by ")).trim();
            } else if (whereClause.contains(" limit ")) {
                whereClause = whereClause.substring(0, whereClause.indexOf(" limit ")).trim();
            }
            String[] condition = whereClause.split("=");
            String key = condition[0].trim();
            String val = condition[1].trim();

            Column condColumn = null;
            int condIndex = -1;
            for (int i = 0; i < table.getColumns().size(); i++) {
                Column c = table.getColumns().get(i);
                if (c.getColumnName().equals(key)) {
                    condColumn = c;
                    condIndex = i;
                    break;
                }
            }

            if (condColumn != null) {
                Iterator<Row> iterator = filteredRows.iterator();
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    Object valObj = row.getValues().get(condIndex);
                    if (!String.valueOf(valObj).equalsIgnoreCase(val)) {
                        iterator.remove();
                    }
                }
            }
        }

        if (originalQuery.contains(" order by ")) {
            String orderClause = originalQuery.substring(originalQuery.indexOf(" order by ") + 10);
            if (orderClause.contains(" limit ")) {
                orderClause = orderClause.substring(0, orderClause.indexOf(" limit ")).trim();
            }
            String[] orderSplit = orderClause.trim().split(" ");
            String orderCol = orderSplit[0];
            boolean asc = orderSplit.length == 1 || orderSplit[1].equalsIgnoreCase("asc");

            int orderIndex = -1;
            for (int i = 0; i < table.getColumns().size(); i++) {
                if (table.getColumns().get(i).getColumnName().equals(orderCol)) {
                    orderIndex = i;
                    break;
                }
            }
            if (orderIndex != -1) {
                final int idx = orderIndex;
                filteredRows.sort(new Comparator<Row>() {
                    @Override
                    public int compare(Row o1, Row o2) {
                        Comparable c1 = (Comparable) o1.getValues().get(idx);
                        Comparable c2 = (Comparable) o2.getValues().get(idx);
                        return asc ? c1.compareTo(c2) : c2.compareTo(c1);
                    }
                });
            }
        }

        if (originalQuery.contains(" limit ")) {
            String limitStr = originalQuery.substring(originalQuery.indexOf(" limit ") + 7).trim();
            int limit = Integer.parseInt(limitStr);
            if (filteredRows.size() > limit) {
                filteredRows = filteredRows.subList(0, limit);
            }
        }

        ResultSet resultSet = new ResultSet();
        ArrayList<Integer> colIndexes = new ArrayList<>();
        for (Column column : columnList) {
            resultSet.getColumns().add(column.getColumnName());
            colIndexes.add(table.getColumns().indexOf(column));
        }

        for (Row row : filteredRows) {
            Object[] vals = new Object[colIndexes.size()];
            for (int i = 0; i < colIndexes.size(); i++) {
                vals[i] = row.getValues().get(colIndexes.get(i));
            }
            resultSet.getValues().add(vals);
        }
        return resultSet;
    }
}
