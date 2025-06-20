package com.designsql;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SelectQuery extends Query {
    private List<Filter> filter;
    private List<Sort> sorts;
    private int limit;

    public SelectQuery(String query) {
        super(query);
    }

    @Override
    public SelectQuery parseQuery() {
        String query = super.getQuery();


        return this;
    }
}
