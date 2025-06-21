package com.designsql;

import java.util.List;

public class CreateQuery extends Query {
   private List<Column> columns;

    public CreateQuery(String query) {
        super(query);
    }

    @Override
    public CreateQuery parseQuery() {
        String query = super.getQuery();


        return this;
    }
}
