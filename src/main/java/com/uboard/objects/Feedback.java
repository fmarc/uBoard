package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cory
 * @editedBy Francisco Marcano
 */
public class Feedback {
    public int subAssingId;
    public String submitby;
    public String feedBack;
    
    //implement queries for getFeedBack && submitFeedback
     private static final String query_getFeedback = 
        "SELECT * FROM \"UBOARD\".u_feedback WHERE assignment_id = ? AND submitted_by = ? ";
    
    private static final String query_submitFeedback = 
        "INSERT INTO \"UBOARD\".u_feedback (assignment_id, submitted_by, feedback) " +
         "VALUES (?, ?, ?)";
    
    public Feedback(int subAssingId, String submitby, String feedback){
        this.subAssingId = subAssingId;
        this.submitby = submitby;
        this.feedBack = feedback;
    }
    
    public static Feedback getFeedback(Connection con, int assign, String submitby){
        PreparedStatement stm = null;
        Feedback feedback = null;
        try {
            
            stm = con.prepareStatement(query_getFeedback);
            stm.setInt(1, assign);
            stm.setString(2, submitby);
            ResultSet found = stm.executeQuery();
            
            if(found.next()){
                feedback = new Feedback(assign, submitby, found.getString("feedback"));
            } else {
                feedback = new Feedback(assign, submitby, "");
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
            stm.setInt(1, feedback.subAssingId);
            stm.setString(2, feedback.submitby);
            stm.setString(3, feedback.feedBack);
            
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

