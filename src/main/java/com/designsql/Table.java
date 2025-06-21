package com.designsql;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Table {
    private String name;
    private List<Column> columns;
    private List<Row> rows;

}
