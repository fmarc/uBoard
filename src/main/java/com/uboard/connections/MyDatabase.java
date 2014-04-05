package com.uboard.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is in charged of maintaining a connection to the Database
 * @author Franco
 */
public class MyDatabase {
    private static final String URL = "jdbc:postgresql://ec2-54-204-41-178.compute-1.amazonaws.com:5432/dd5dso78uef5kv?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";   
    private static final String USERNAME = "eygvzrmjofevss";   
    private static final String PASSWORD = "T6umxhf4D6-9oN37g7yXCBsZa0"; 
    private static Connection connection;
    
    /**
     * Singleton Object, no constructor needed
     */
    private MyDatabase() { }
    
    /**
     * This method is in charged of checking if a connection already exists, if
     * it doesn't it returns a valid SQL Connection
     * @return Connection - Database Connection
     */
    public static Connection getConnection() {
        if(connection == null)
            return connection = MyDatabase.connect();
        else 
            return connection;
    }
    
    /**
     * This method connects to the database using a JDBC connection
     * @return Connection - SQL Database Connection
     */
    private static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            StringBuilder error = new StringBuilder();
            error.append("The connection to the Datbase could not be stablished:\nSQL State: ");
            error.append(e.getSQLState()).append("\n").append( e.toString());
            System.out.print(error.toString());
        }
        
        return null;
    }
}
