package com.andrespedes.ocpjp7.jdbc;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

/**
 * Created by Andres on 07/04/2016.
 */
public class RowSets {

    public void jdbcRowSet() throws SQLException {
        RowSetFactory myRowSetFactory = null;
        JdbcRowSet jdbcRs = null;

        myRowSetFactory = RowSetProvider.newFactory();
        jdbcRs = myRowSetFactory.createJdbcRowSet();

        jdbcRs.setUrl("jdbc:mysql://localhost:3307/test");
        jdbcRs.setUsername("root");
        jdbcRs.setPassword("root");

        jdbcRs.setCommand("SELECT * FROM department"); //Instead of statement or preparedStatement
        jdbcRs.execute(); // no longer needs resultset


        System.out.println("- Print all data:");
        jdbcRs.beforeFirst(); //Set the cursor at the beginning

        while (jdbcRs.next()) {
            int rowNo = jdbcRs.getRow();
            String s1 = jdbcRs.getString("name");
            System.out.println(rowNo + ": " + s1 );
        }

    }

    public static void main(String[] args) {
        try {
            new RowSets().jdbcRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
