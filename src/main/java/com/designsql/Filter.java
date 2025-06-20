package com.designsql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Filter {
    private Column column;
    private FilterOperation filterOperation;
    private Object value;
}
