package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Franco
 */
public class Lesson {
    public int lessonId;
    public int classId;
    public String createdBy;
    public String name;
    public int posRating;
    public int totRating;
    public String html;
    
    //List of Rates where <Username, Rating>
    public Map<String, Integer> raters;
    
    //List of Comments where <Username, Comment Text>
    public Set<Comment> comments;
    
    private static final String query_createLesson = 
        "INSERT INTO \"UBOARD\".u_lesson (class_id, created_by, lesson_name) "
        + "VALUES (?, ?, ?) RETURNING lesson_id";
    
    private static final String query_getLesson = 
        "SELECT * FROM \"UBOARD\".u_lesson WHERE lesson_id = ?";
    
    private static final String query_saveLesson = 
        "UPDATE \"UBOARD\".u_lesson SET lesson_content = ? WHERE lesson_id = ?";
    
    private static final String query_updateRating = 
        "UPDATE \"UBOARD\".u_lesson SET pos_rating = pos_rating + ?, "
        + "tot_rating = tot_rating + 1 WHERE lesson_id = ?";
    
    private static final String query_rateLesson = 
        "INSERT INTO \"UBOARD\".u_rated (username, lesson_id, rating) "
        + "VALUES (?, ?, ?)";
    
    private static final String query_getRaters = 
        "SELECT username, rating FROM \"UBOARD\".u_rated WHERE lesson_id = ?";
    
    
    /**
     * This constructor creates a new Lesson with the following information
     * @param lessonId - The Lesson ID of the Lesson being constructed
     * @param classId - The Class ID of the Lesson being constructed 
     */
    public Lesson(int lessonId, int classId) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getLesson);
            stm.setInt(1, lessonId);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            if(found.next()){
                this.lessonId           = lessonId;
                this.classId            = found.getInt("class_id");
                this.createdBy          = found.getString("created_by");
                this.name               = found.getString("lesson_name");
                this.posRating          = found.getInt("pos_rating");
                this.totRating          = found.getInt("tot_rating");
                this.html               = found.getString("lesson_content");
                this.raters             = getRaters(con);
                this.comments           = Comment.getComments(con, classId, lessonId);
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
     * Creates a light Lesson Object
     * @param lessonId - The Id of the lesson
     * @param classId - The ID of the class
     * @param name - The name of the lesson
     * @param posRating - The positive rating for the lesson
     */ 
    public Lesson(int lessonId, int classId, String name, int posRating) {
        this.lessonId           = lessonId;
        this.classId            = classId;
        this.name               = name;
        this.posRating          = posRating;
    }
    
    
    /**
     * Creates a new lesson using the specified information then returns the
     * newly created Lesson's ID
     * @param username - The user's username
     * @param title - The lesson title
     * @param classId - The class ID this lesson is linked to
     * @return Integer - The ID of the newly created lesson
     */
    public static int createLesson(String username, int classId, String title) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_createLesson);
            stm.setInt(1, classId);
            stm.setString(2, username.toLowerCase());
            stm.setString(3, title);
            
            //Executes the query
            ResultSet data = stm.executeQuery();
            
            //Returns the Lesson ID generated by the query
            if(data.next()){
                return data.getInt("lesson_id");
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
     * Saves the lesson to the database
     * @param lessonId - The id of the lesson being saved
     * @param html - The content to be saved to the database
     * @return 
     */
    public static boolean saveLesson(int lessonId, String html) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_saveLesson);
            stm.setString(1, html);
            stm.setInt(2, lessonId);
            
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
     * Retrieves all the Users who rated this particular Lesson
     * @param con - The Database connection used
     * @return Raters - HashMap<Username, Rating>
     */
    private HashMap<String, Integer> getRaters(Connection con){
        PreparedStatement stm = null;
        HashMap<String, Integer> userraters = new HashMap<String, Integer>();
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getRaters);
            stm.setInt(1, this.lessonId);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            while(found.next()){
                userraters.put(found.getString("username"), found.getInt("rating"));
            }
        } catch (SQLException e) {
            userraters = null;
            System.out.println("There was SQL error when getting Users who rated the lesson:\n");
            e.printStackTrace();
        }
        return userraters;
    }
    
    /**
     * Stores Users who rated this Lesson in the UserRaters Table
     * @param con - The Database connection used
     * @return Raters - HashMap<Username, Rating>
     */
    public static boolean rateLesson(String username, int lessonId, int rating){
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        PreparedStatement stm2 = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, lessonId, rating)
            stm = con.prepareStatement(query_rateLesson);
            stm.setString(1, username);
            stm.setInt(2, lessonId);            
            stm.setInt(3, rating);
            
            //Executes the query
            stm.executeUpdate();
            
            //Updates the lesson rating in order to keep lesson rating up to date.
            stm2 = con.prepareStatement(query_updateRating);
            stm2.setInt(1, rating);
            stm2.setInt(2, lessonId);
            
            //Executes the query
            stm2.executeUpdate();
            
            //Returns true unless the execute command hits an Exception
            return true;
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when inserting a rater");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failed to close connections.");
            }
        }
        // Returns false if the process was unsuccessful
        return false;
    }
}
