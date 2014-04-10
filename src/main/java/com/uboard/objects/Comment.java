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
public class Comment {
    public String username;
    public int lessonId;
    public int classId;
    public String text;
    
    private static final String query_getComments = 
        "SELECT created_by, comment_text FROM \"UBOARD\".u_comments WHERE lesson_id = ? "
            + "ORDER BY created_date ASC";
    
    private static final String query_submitComment = 
        "INSERT INTO \"UBOARD\".u_comments (created_by, class_id, lesson_id, comment_text) "
        + "VALUES (?, ?, ?, ?)";
    
    /**
     * Creates a new comment object from the specified data
     * @param username - The commenter's Username
     * @param lessonId - The lesson ID where the comment was written
     * @param classId - Class ID where the comment was written
     * @param text - The comment's text
     */
    public Comment(String username, int classId, int lessonId, String text) {
        this.username   = username;
        this.lessonId   = lessonId;
        this.classId    = classId;
        this.text       = text;
    }
    
    /**
     * Retrieves all the comments for this particular lesson
     * @param con - The Database connection used
     * @return Comments - HashMap<Username, Comment Text>
     */
    public static Set<Comment> getComments(Connection con, int classId, int lessonId){
        PreparedStatement stm = null;
        Set<Comment> usercomments = new HashSet<Comment>();
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getComments);
            stm.setInt(1, lessonId);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            while(found.next()){
                usercomments.add(new Comment(found.getString("created_by"), classId, lessonId, found.getString("comment_text")));
            }
        } catch (SQLException e) {
            usercomments = null;
            System.out.println("There was SQL error when getting comments for lesson object:\n");
            e.printStackTrace();
        }
        return usercomments;
    }
    
    /**
     * Submits a new comment into the database
     * @param comment - The comment to be posted
     * @return boolean - Indicates whether the submit was successful
     */
    public static boolean submitComment(Comment comment) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_submitComment);
            stm.setString(1, comment.username.toLowerCase());
            stm.setInt(2, comment.classId);
            stm.setInt(3, comment.lessonId);
            stm.setString(4, comment.text);
            
            //Executes the query
            stm.executeUpdate();
            
            //Returns true unless the execute command hits an Exception
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
        
        // Returns false if the process was unseccessful
        return false;
    }
}
