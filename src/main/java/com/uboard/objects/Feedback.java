package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cory
 */
public class Feedback {
    
    
    int assignId;
    String createBy;
    String feedBack;
    
    //implement queries for getFeedBack && submitFeedback
     private static final String query_getFeedback = 
        "SELECT * FROM \"UBOARD\".u_feedback WHERE assignment_id = ? ";
    
    private static final String query_submitFeedback = 
        "INSERT INTO \"UBOARD\".u_feedback (assignment_id, submitted_by, feedback) \n" +
         "VALUES (?, ?, ?)";
    
    public Feedback(int assign, 
                    String creator, String feedback){
    
        assignId = assign;
        createBy = creator;
        feedBack = feedback;
    }
    
    public static Feedback getFeedback(Connection con, int assign){
        PreparedStatement stm = null;
        Feedback feedback = null;
        try {
            
            stm = con.prepareStatement(query_getFeedback);
            stm.setInt(1, assign);
            ResultSet found = stm.executeQuery();
            
            if(found.next()){
                feedback = new Feedback(assign, found.getString("submitted_by"), found.getString("feedback"));
            }  
        }
        catch(SQLException e){
            System.out.println("SQL Error when getting Feedback");
            e.printStackTrace();
        }
        
    return feedback;
    }
    
    public static boolean submitFeedback(Feedback feedback){
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try{
            stm = con.prepareStatement(query_submitFeedback);
            stm.setInt(1, feedback.assignId);
            stm.setString(3, feedback.createBy);
            stm.setString(4, feedback.feedBack);
            
            stm.executeUpdate();
            
            return true;
        }
        catch(SQLException e){
            System.out.println("\nThere was an SQL error while submitting feedback");
           e.printStackTrace();
        
        }finally{
            try{
                con.close();
                stm.close();
            }catch(SQLException e){
                System.out.println("Failed to close connections");
            }
        
    }
    return false;
    }
}

