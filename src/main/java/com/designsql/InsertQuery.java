package com.designsql;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsertQuery extends Query {
    public InsertQuery(String query) {
        super(query);
    }

    @Override
    public InsertQuery parseQuery() {
        String query = super.getQuery();


        return this;
    }
}
