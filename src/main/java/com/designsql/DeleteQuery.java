package com.designsql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DeleteQuery extends Query {

    private List<Filter> filter;
    private List<Sort> sorts;
    private int limit;

    public DeleteQuery(String query) {
        super(query);
    }

    @Override
    public DeleteQuery parseQuery() {
        String query = super.getQuery();


        return this;
    }
}
