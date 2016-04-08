package com.andrespedes.ocpjp7.jdbc;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.Properties;

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
            System.out.println(rowNo + ": " + s1);
        }

    }


    public Connection connectToDb(){
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "root");
        Connection conn = null;
        try {
             conn =  DriverManager.getConnection("jdbc:mysql://localhost:3307/test", connectionProps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public void insertRow() {
        try (Connection connection = connectToDb();
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM department")) {
            System.out.println("Before the insert");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("idDepartment") + "\t"
                        + resultSet.getString("name"));
            }
            resultSet.moveToInsertRow();
            resultSet.updateInt("idDepartment", 2016);
            resultSet.updateString("name", "Limon");
            resultSet.insertRow();
            System.out.println("After the insert");
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("idDepartment") + "\t"
                        + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        try {
//            new RowSets().jdbcRowSet();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        new RowSets().insertRow();
    }
}
