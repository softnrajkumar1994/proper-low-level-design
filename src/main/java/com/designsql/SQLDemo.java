package com.designsql;

public class SQLDemo {
    public static void main(String args[]) {
        DBConnection connection = DBConnection.getConnection();

        connection.createTable("CREATE TABLE cities (\n" +
                "id INT,\n" +
                "name VARCHAR,\n" +
                "country VARCHAR,\n" +
                "population INT,\n" +
                "is_capital BOOLEAN,\n" +
                "climate VARCHAR\n" +
                ");\n");


        connection.insert("INSERT INTO cities (id, name, country, population, is_capital, climate) VALUES\n" +
                "(1, 'New York', 'USA', 8419000, FALSE, 'TEMPERATE'),\n" +
                "(2, 'London', 'UK', 8982000, TRUE, 'TEMPERATE'),\n" +
                "(3, 'Tokyo', 'Japan', 13929000, TRUE, 'TEMPERATE'),\n" +
                "(4, 'Sydney', 'Australia', 5373000, FALSE, 'TEMPERATE'),\n" +
                "(5, 'Paris', 'France', 2148000, TRUE, 'TEMPERATE'),\n" +
                "(6, 'Berlin', 'Germany', 3769000, TRUE, 'TEMPERATE'),\n" +
                "(7, 'Toronto', 'Canada', 2930000, FALSE, 'COLD'),\n" +
                "(8, 'Dubai', 'UAE', 3331000, FALSE, 'ARID'),\n" +
                "(9, 'Mumbai', 'India', 20411000, FALSE, 'TROPICAL'),\n" +
                "(10, 'São Paulo', 'Brazil', 12252000, FALSE, 'TROPICAL');");



        ResultSet resultSet = connection.select("select * from cities");
        ResultSet resultSet1 = connection.select("select * from cities where is_capital=true order by name desc limit 5");
        ResultSet resultSet2 = connection.select("select population,country from cities where is_capital=true order by name desc limit 5");

        resultSet.printTable();
        System.out.println("\n\n\n====================================================================");
        resultSet1.printTable();
        System.out.println("\n\n\n====================================================================");
        resultSet2.printTable();
    }


}
