package com.uboard.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
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
            return connection = MyDatabase.connect2();
        else 
            return connection;
    }
    
    /**
     * This method connects to the database using a JDBC connection
     * @return Connection - SQL Database Connection
     */
    private static Connection connect() {
        String url = "jdbc:postgresql://ec2-54-204-41-178.compute-1.amazonaws.com:5432/dd5dso78uef5kv";
        Properties props = new Properties();
        props.setProperty("user","eygvzrmjofevss");
        props.setProperty("password","T6umxhf4D6-9oN37g7yXCBsZa0");
        props.setProperty("ssl","true");
        
        try {
            return DriverManager.getConnection(url, props);
        } catch(SQLException e) {
            StringBuilder error = new StringBuilder();
            error.append("The connection to the Datbase could not be stablished:\nSQL State: ");
            error.append(e.getSQLState()).append("\n").append( e.toString());
            System.out.print(error.toString());
        }
        
        return null;
    }
    
    private static Connection connect2() {
        try {
            //String url = "jdbc:postgresql://ec2-54-204-41-178.compute-1.amazonaws.com:5432/dd5dso78uef5kv?user=eygvzrmjofevss&password=T6umxhf4D6-9oN37g7yXCBsZa0&ssl=true";
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
