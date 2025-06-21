package com.designsql;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataLake {

    private static DataLake dataLake = null;
    private List<Table> tableList;

    private DataLake() {
        tableList = new ArrayList<>();
    }

    public static DataLake getDataLake() {
        if (dataLake == null) {
            dataLake = new DataLake();
        }
        return dataLake;
    }

    public void addTable(Table t) {
        tableList.add(t);
    }

    public Table getTable(String tname) {
        for (Table t : tableList) {
            if (t.getName().equals(tname)) {
                return t;
            }
        }
        return null;
    }
}
