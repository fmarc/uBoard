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
        + "VALUES (null, ?, ?) RETURNING lesson_id";
    
    private static final String query_getLesson = 
        "SELECT * FROM \"UBOARD\".u_lesson WHERE lesson_id = ?";
    
    private static final String query_saveLesson = 
        "UPDATE \"UBOARD\" SET lesson_content = ? WHERE lesson_id = ?";
    
    private static final String query_updateRating = 
        "UPDATE \"UBOARD\".u_lesson SET pos_rating = pos_rating + ? "
        + "AND tot_rating = tot_rating + 1";
    
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
                this.classId            = classId;
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
    
    
    
}
