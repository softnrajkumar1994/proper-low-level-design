package com.designsql;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AlterQuery extends Query {
    public AlterQuery(String query) {
        super(query);
    }

    @Override
    public AlterQuery parseQuery() {
        String query = super.getQuery();


        return this;
    }
}
