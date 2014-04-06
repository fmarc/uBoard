package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import com.uboard.interfaces.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is in charged of keeping all necessary methods and objects related
 * to the current state of the application.
 * @author Franco
 */
public class Utilities {
    private static Utilities instance;
    private Map<String, User> usersOnline;
    
    private static final String query_findUser = 
        "SELECT username, user_password, user_type FROM \"UBOARD\".u_user WHERE username = ? "
        +   "AND user_password = ?";
    
    private static final String query_usernameExists  = 
        "SELECT username FROM \"UBOARD\".u_user WHERE username = ?";
    
    private static final String query_emailExists  = 
        "SELECT email FROM \"UBOARD\".u_user WHERE email = ?";
    
    private static final String query_registerUser = 
        "INSERT INTO \"UBOARD\".u_user (username, email, name, user_password) "
        + "VALUES (?, ?, ?, ?)";
    
    /**
     * Singleton Object, no constructor needed
     */
    private Utilities() { this.usersOnline = new HashMap<String, User>(); }
    
    /**
     * Gets an the active instance of the Utilities Object, if none are active
     * (instance == null) then creates a new instance of the Utilities Object
     * @return Utilities - The current Utilities object
     */
    public static Utilities getInstance(){
        if(instance != null)
            return instance;
        else
            return instance = new Utilities();
    }
    
    /**
     * Checks to see if the User trying to log in has the correct credentials
     * @param sessionId - The current user's Session ID
     * @param username - The USer's username
     * @param password - The User's password
     * @return boolean - Indicates if the User was successfully logged in
     */
    public boolean checkLogin(String sessionId, String username, String password) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_findUser);
            stm.setString(1, username.toLowerCase());
            stm.setString(2, password);
            ResultSet found = stm.executeQuery();
            
            //Check for all the records (in this case just one) to see if the
            //user credentials match the ones in the Database
            while(found.next()){
                if(found.getString("username").equalsIgnoreCase(username)
                && found.getString("user_password").equals(password)) {
                    login(sessionId, username, found.getString("user_type"));
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when finding the User while logging in:");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        
        return false;
    }
    
    /**
     * Logs the User into the application. This implies keeping a list in memory
     * with all the Users that are currently online.
     * @param sessionId - The User's current session
     * @param username - The User's username
     * @param type - The type of the user to be logged in (Teacher or Student)
     */
    private void login(String sessionId, String username, String type) {
        //Checks to see whether the User is a Teacher or Student
        if(type.equals("0")){
            usersOnline.put(sessionId, new Student(username.toLowerCase()));
        } else {
            usersOnline.put(sessionId, new Teacher(username.toLowerCase()));
        }
    }
    
    /**
     * Logs the current User out of the application
     * @param sessionId - The session ID of the current User
     * @return boolean - Indicates whether the current User was successfully logged out
     */
    public boolean logout(String sessionId){
        try {    
            usersOnline.remove(sessionId);
        } catch (Exception e){
            System.out.println("\n The current User could not be logged out: " + sessionId);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Gets the current logged in user from the list of users logged in
     * @param sessionId - The session ID of the current User
     * @return User - The currently logged in User
     */
    public User getOnlineUser(String sessionId){
        return usersOnline.get(sessionId);
    }
    
    /**
     * Checks to see if the username passed exists in the database
     * @param username - User's username
     * @return boolean - Indicates whether the username exists
     */
    public boolean usernameExists(String username) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_usernameExists);
            stm.setString(1, username.toLowerCase());
            ResultSet found = stm.executeQuery();
            
            //Check for all the records (in this case just one) to see if the
            //user credentials match the ones in the Database
            if(found.next())
                return true;
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when finding the User while logging in:");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        return false;
    }
    
    /**
     * Checks to see if the email passed exists in the database
     * @param email - User's email
     * @return boolean - Indicates whether the email exists
     */
    public boolean emailExists(String email) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_emailExists);
            stm.setString(1, email.toLowerCase());
            ResultSet found = stm.executeQuery();
            
            //Check for all the records (in this case just one) to see if the
            //user credentials match the ones in the Database
            if(found.next())
                return true;
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when finding the User while logging in:");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        return false;
    }
    
    public boolean register(String username, String email, String name, String password){
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            //Query = (username, email, name, user_password)
            stm = con.prepareStatement(query_registerUser);
            stm.setString(1, username.toLowerCase());
            stm.setString(2, email.toLowerCase());
            stm.setString(3, name.toLowerCase());
            stm.setString(4, password);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when registeringa new User:");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        
        return false;
    }
    
}
