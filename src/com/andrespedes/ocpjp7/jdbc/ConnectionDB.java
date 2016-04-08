package com.andrespedes.ocpjp7.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Andres on 06/04/2016.
 */
public class ConnectionDB {


    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "root");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/test", connectionProps);
        System.out.println("Database Vendor connection: " + conn.getMetaData().getDatabaseProductName());
        return conn;
    }

    public void getConnectionOld() {
        // url points to jdbc protocol : mysql subprotocol; localhost is the address
        // of the server where we installed our DBMS (i.e. on local machine) and
        // 3306 is the port on which we need to contact our DBMS
        String url = "jdbc:mysql://localhost:3307/";
        // we are connecting to the addressBook database we created earlier
        String database = "test";
        // we login as "root" user with password "mysql123"
        String userName = "root";
        String password = "root";
        try (Connection connection = DriverManager.getConnection
                (url + database, userName, password)) {
            System.out.println("Database Vendor connection: " + connection.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            System.out.println("Database connection: Failed");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new ConnectionDB().getConnection();
            new ConnectionDB().getConnectionOld();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
