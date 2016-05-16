package com.sf.kycmanager;

import org.springframework.context.annotation.PropertySource;

import java.sql.*;

/**
 * Created by Cyprian on 4/12/2016.
 */
@PropertySource("classpath:application.properties")
public class App {

    public static void main(String args[]) {
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.2.28:1521:kycdb", "biocapture",
                    "openMinds");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT SURNAME FROM  BIODATA_DEMOGRAPHICS WHERE ROWNUM BETWEEN 1 and 10 ORDER BY SURNAME");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }


}
