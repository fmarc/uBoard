package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Franco
 */
public class Class {
    public int classId;
    public String createdBy;
    public String className;
    public int numEnrolled;
    public float price;
    public int enrollLimit;
    public int posRating;
    public int totRating;
    public String description;
    public String streamName;
    public String html;
    
    //List of Assingnmets
    public Set<Assignment> assignments = new HashSet<Assignment>();
    
    //List of Lessons
    public Set<Lesson> lessons = new HashSet<Lesson>();
    
    //List of enrolled Users
    public Set<String> enrolled = new HashSet<String>();
    
    //List of comments
    public Set<Comment> comments = new HashSet<Comment>();
    
    
    private static final String query_createClass = 
        "INSERT INTO \"UBOARD\".u_class (created_by, class_name, price, class_limit) "
        + "VALUES (?, ?, ?, ?) RETURNING class_id";
    
    private static final String query_saveClass = 
        "UPDATE \"UBOARD\".u_class SET description = ? WHERE class_id = ? ";
    
    private static final String query_saveStream = 
        "UPDATE \"UBOARD\".u_class SET stream_name = ? WHERE class_id = ? ";
    
    private static final String query_getClass = 
        "SELECT * FROM \"UBOARD\".u_class WHERE class_id = ? ";
    
    private static final String query_getClassLesson =
        "SELECT * FROM \"UBOARD\".u_lesson WHERE class_id = ? ";
    
    private static final String query_enroll = 
        "INSERT INTO \"UBOARD\".u_enrolled (class_id, username) "
        + "VALUES (?, ?) ";
    
    private static final String query_getEnrolled = 
        "SELECT username FROM \"UBOARD\".u_enrolled WHERE class_id = ? ";
    
    
    /**
     * Creates a new instance of a Class Object
     * @param classId 
     */
     public Class(int classId) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getClass);
            stm.setInt(1, classId);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            if(found.next()){
                this.classId            = classId;
                this.className          = found.getString("class_name");
                this.createdBy          = found.getString("created_by");
                this.enrolled           = getEnrolled(con);
                this.numEnrolled        = enrolled.size();
                this.price              = found.getFloat("price");
                this.enrollLimit        = found.getInt("class_limit");
                this.description        = found.getString("description");
                this.streamName         = found.getString("stream_name");
                this.assignments        = Assignment.getAssignments(con, classId);
                this.lessons            = getClassLessons(con);
                this.comments           = Comment.getComments(con, classId, 0);
                this.posRating          = getPosRating();
                this.totRating          = getTotRating();
            } else {
                this.classId            = 0;
            }
        } catch (SQLException e) {
            System.out.println("There was SQL error when creating a Lesson object:\n");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failed to close connections.");
            }
        }
     }
     
     /**
      * This constructor is user for fast access to light data
      * @param con - The Database connection
      * @param classId
      * @param name 
      */
     public Class(Connection con, int classId, String name) {
         this.classId   = classId;
         this.className = name;
         this.lessons   = getClassLessons(con);
         this.posRating = getPosRating();
     }
    
    /**
     * Creates a new Class based on the parameters given
     * @param username - The username of the User creating the class
     * @param title - The title of the Class
     * @param price - The price of the Class
     * @param limit - The size limit of the class
     * @return Integer - The Class ID that was created.
     */
    public static int createClass(String username, String title, float price, int limit) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_createClass);
            stm.setString(1, username);
            stm.setString(2, title);
            stm.setFloat(3, price);
            stm.setInt(4, limit);
            
            //Executes the query
            ResultSet data = stm.executeQuery();
            
            //Returns the Lesson ID generated by the query
            if(data.next()){
                return data.getInt("class_id");
            }
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when creating a new Lesson:");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        
        // Returns 0 if the lessonID could not be created
        return 0;
    }
    
    /**
     * Saves the Class based on the parameters given
     * @param classId - The id of the class
     * @param description - The description text
     * @return boolean - Indicates success or failure
     */
    public static boolean saveClass(int classId, String description) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_saveClass);
            stm.setString(1, description);
            stm.setInt(2, classId);
            
            //Executes the query
            stm.executeUpdate();
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
        // Returns 0 if the lessonID could not be created
        return true;
    }
    
    
    /**
     * Saves the stream name for the stream page
     * @param classId - The id of the class saving
     * @param streamName - The stream name.
     * @return boolean - Indicates success or failure
     */
    public static boolean saveStream(int classId, String streamName) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_saveStream);
            stm.setString(1, streamName);
            stm.setInt(2, classId);
            
            //Executes the query
            stm.executeUpdate();
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
        
        // Returns 0 if the lessonID could not be created
        return true;
    }
    
    
    /**
     * Gets the rating out of all the lessosn that belong
     * to this class.
     * @return int - the Class' rating
     */
    private int getPosRating() {
        int total = 0;
        
        for(Lesson lesson : this.lessons){
            total += lesson.posRating;
        }
        
        return total;
    }
    
    /**
     * Gets the rating out of all the lessosn that belong
     * to this class.
     * @return int - the Class' rating
     */
    private int getTotRating() {
        int total = 0;
        
        for(Lesson lesson : this.lessons){
            total += lesson.totRating;
        }
        
        return total;
    }
    
    /**
     * Gets all the lessons for this class.
     * @param con
     * @return 
     */
    private Set<Lesson> getClassLessons(Connection con) {
        Set<Lesson> classLessons = new HashSet<Lesson>();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_getClassLesson);
            stm.setInt(1, this.classId);
            
            //Executes the query
            ResultSet found = stm.executeQuery();
            
            while(found.next()) {
                classLessons.add(new Lesson(found.getInt("lesson_id"), 
                    this.classId, found.getString("lesson_name"), found.getInt("pos_rating")));
            }
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when saving a Lesson:");
            e.printStackTrace();
        } finally {
            try{
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        
        return classLessons;
    }
    
    /**
     * Enroll a student into the class
     * @param classId - The class ID 
     * @param username - The user enrolling
     * @return boolean - Indicates success or failure
     */
    public static boolean enroll(int classId, String username) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_enroll);
            stm.setInt(1, classId);
            stm.setString(2, username);
            
            //Executes the query
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when enrolling user: " + username);
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
        return true;
    }
    
    /**
     * Gets a list of all the User's currently enrolled in
     * the class.
     * @return 
     */
    private Set<String> getEnrolled(Connection con) {
        PreparedStatement stm = null;
        Set<String> users = new HashSet<String>();
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_getEnrolled);
            stm.setInt(1, this.classId);
            
            //Executes the query
            ResultSet found = stm.executeQuery();
            
            while(found.next()) {
                users.add(found.getString("username"));
            }
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when getting enrolled Students:");
            e.printStackTrace();
        } finally {
            try{
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        
        // Returns 0 if the lessonID could not be created
        return users;
    }
    
    /**
     * Checks to see if the user is enrolled in the class
     * @param username
     * @return 
     */
    public boolean isEnrolled(String username){
        for(String name : this.enrolled){
            if(name.equalsIgnoreCase(username))
                return true;
        }
        
        return false;
    }
    
}
