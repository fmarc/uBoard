package com.uboard.objects;

import com.uboard.objects.Class;
import com.uboard.connections.MyDatabase;
import com.uboard.interfaces.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    
    private static final String query_getUser = 
        "SELECT user_type FROM \"UBOARD\".u_user WHERE username = ? ";
    
    private static final String query_usernameExists  = 
        "SELECT username FROM \"UBOARD\".u_user WHERE username = ?";
    
    private static final String query_emailExists  = 
        "SELECT email FROM \"UBOARD\".u_user WHERE email = ?";
    
    private static final String query_registerUser = 
        "INSERT INTO \"UBOARD\".u_user (username, email, name, user_password) "
        + "VALUES (?, ?, ?, ?)";
    
    private static final String query_Search = 
        "SELECT * FROM \"UBOARD\".u_lesson WHERE class_id = 0 AND lower(lesson_name) LIKE '%' || ? || '%' "
            + "OR lower(lesson_name) LIKE '%' || ? OR lower(lesson_name) LIKE ? || '%' ";
    
    private static final String query_SearchClass = 
        "SELECT * FROM \"UBOARD\".u_class WHERE class_id <> 0 AND lower(class_name) LIKE '%' || ? || '%' "
            + "OR lower(class_name) LIKE '%' || ? OR lower(class_name) LIKE ? || '%' ";
    
    private static final String query_getTopRated =
        "SELECT * FROM \"UBOARD\".u_lesson WHERE lesson_id <> 0 AND class_id = 0 ORDER BY pos_rating DESC LIMIT 10";
    
    private static final String query_getTopRatedClasses =
        "SELECT * FROM \"UBOARD\".u_class WHERE class_id <> 0 ORDER BY pos_rating DESC LIMIT 10";
    
    private static final String query_saveProfile = 
        "UPDATE \"UBOARD\".u_user SET name = ?, about = ?, paypal_email = ? "
        + "WHERE username = ? ";
    
    private static final String query_promoteUser = 
        "UPDATE \"UBOARD\".u_user SET user_type = 1 WHERE username = ? ";
    
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
     * Gets the specified user from its username
     * @param username
     * @return 
     */
    public User getUser(String username) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getUser);
            stm.setString(1, username.toLowerCase());
            ResultSet found = stm.executeQuery();
            
            //Check for all the records (in this case just one) to see if the
            //user credentials match the ones in the Database
            if(found.next()){
                if(found.getInt("user_type") == 0) {
                    return new Student(username.toLowerCase());
                } else {
                    return new Teacher(username.toLowerCase());
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
        return null;
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
    
    
    /**
     * This searches the lessons in the database based on the search criteria
     * @param search - The search criteria the User enters
     * @return Set<Lesson> - All the lessons that match the search criteria.
     */
    public Set<Lesson> searchContent(String search) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        Set<Lesson> lessons = new HashSet<Lesson>(); 
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            //Query = (username, email, name, user_password)
            stm = con.prepareStatement(query_Search);
            stm.setString(1, search.toLowerCase());
            stm.setString(2, search.toLowerCase());
            stm.setString(3, search.toLowerCase());

            ResultSet found = stm.executeQuery();
            while(found.next()) {
                lessons.add(new Lesson(found.getInt("lesson_id"), found.getInt("class_id")));
            }
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
        return lessons;
    }
    
    
    /**
     * This searches the lessons in the database based on the search criteria
     * @param search - The search criteria the User enters
     * @return Set<Lesson> - All the lessons that match the search criteria.
     */
    public Set<Class> searchContentClass(String search) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        Set<Class> classes = new HashSet<Class>(); 
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            //Query = (username, email, name, user_password)
            stm = con.prepareStatement(query_SearchClass);
            stm.setString(1, search.toLowerCase());
            stm.setString(2, search.toLowerCase());
            stm.setString(3, search.toLowerCase());
            ResultSet found = stm.executeQuery();
            while(found.next()) {
                classes.add(new Class(con, found.getInt("class_id"), found.getString("class_name")));
            }
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
        return classes;
    }
    
    
    /**
     * Looks for the highest rated lessons
     * @return Set<Lesson> - A list of the first 15 highest rated lessons
     */
    public Set<Lesson> getTopRated(){
        Set<Lesson> lessons = new HashSet<Lesson>();
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(query_getTopRated);
            ResultSet found = stm.executeQuery();

            while(found.next()){
                lessons.add(new Lesson(found.getInt("lesson_id"), found.getInt("class_id"),
                        found.getString("lesson_name"), found.getInt("pos_rating")));
            }
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when retrieving topRated Lessons");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }

        return lessons;

    }
    
    /**
     * Looks for the highest rated Classes
     * @return Set<Lesson> - A list of the first 15 highest rated lessons
     */
    public Set<Class> getTopRatedClasses(){
        Set<Class> classes = new HashSet<Class>();
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(query_getTopRatedClasses);
            ResultSet found = stm.executeQuery();

            while(found.next()){
                classes.add(new Class(con, found.getInt("class_id"), found.getString("class_name")));
            }
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when retrieving topRated Lessons");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }

        return classes;

    }
    
    /**
     * Saves the profile of the username provided
     * @param name
     * @param about
     * @param paypal
     * @param username
     * @return 
     */
    public boolean saveProfile(String name, String about, String paypal, String username, User user) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (name, about, paypal, username)
            stm = con.prepareStatement(query_saveProfile);
            stm.setString(1, name);
            stm.setString(2, about);
            stm.setString(3, paypal);
            stm.setString(4, username);
            
            //Executes the query
            stm.executeUpdate();
            
            user.setPaypal(paypal);
            
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when saving a Lesson:");
            e.printStackTrace();
            return false;
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        // Returns 0 if the profile could not be saved
        return true;
    }
    
    /**
     * Promotes the current user if his rating is above a certain threshold
     * @return 
     */
    public boolean promoteUser(String sessionId) {
        
        Student user = (Student) Utilities.getInstance().getOnlineUser(sessionId);
        
        if(user.getPosRating() > 1000) {
            Connection con = MyDatabase.connect();
            PreparedStatement stm = null;

            try {
                //Creates a prepared statement that takes care of the query and its
                //values - Query = (name, about, paypal, username)
                stm = con.prepareStatement(query_promoteUser);
                stm.setString(1, user.getUsername());

                //Executes the query
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println("\nThere was SQL error when Promoting User:");
                e.printStackTrace();
                return false;
            } finally {
                try{
                    con.close();
                    stm.close();
                } catch(SQLException e) {
                    System.out.println("Failes to close connections.");
                }
            }
            // Returns 0 if the profile could not be saved
            return true;
        }
        
        return false;
    }
    
}
