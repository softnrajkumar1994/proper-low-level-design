package com.designsql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public abstract class Query {

    private String query;
    private QueryOperation operation;
    private Table table;

    public Query(String query) {
        this.query = query;
    }

    public abstract Query parseQuery();

}
