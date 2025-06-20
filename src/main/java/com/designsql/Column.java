package com.designsql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.StandardException;

@Getter
@Setter
@NoArgsConstructor
public class Column {
    private String columnName;
    private ColumnType dataType;
}
