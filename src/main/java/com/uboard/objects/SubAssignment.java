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
 * @author Cory
 */
public class SubAssignment {
    public int assignId;
    public String submitBy;
    public String title;
    public String submission;
    
public SubAssignment(int assignid, String submitby, String title, String submission){
    this.assignId = assignid;
    this.submitBy = submitby;
    this.title = title;
    this.submission = submission;
}
    
    private static final String query_getSubAssign = 
        "SELECT * FROM \"UBOARD\".u_sub_assignments WHERE assignment_id = ?";
    
    private static final String query_submitAssignment = 
        "INSERT INTO \"UBOARD\".u_sub_assignments (assign_id, submitted_by, title, submission)"
        + "VALUES (?, ?, ?, ?)"; 
        
    public Set<SubAssignment> getSubAssignment(int assignId){
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        Set<SubAssignment> assn = new HashSet<SubAssignment>();
        try{
            
            stm = con.prepareStatement(query_getSubAssign);
            stm.setInt(1, assignId);
            ResultSet found = stm.executeQuery();
            
            while(found.next()){
            assn.add(new SubAssignment(assignId, found.getString(submitBy), found.getString(title), found.getString(submission)));
            }
            } catch (SQLException e) {
                    System.out.println("There was an SQL error when creating submitted assignment\n");
                    e.printStackTrace();
                    }
        return assn;
        }
    
    public static boolean submitAssignment(int assignid, String submitby, String title, String submission) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (assignid, submitby, title, submission)
            stm = con.prepareStatement(query_submitAssignment);
            stm.setInt(1, assignid);
            stm.setString(2, submitby);
            stm.setString(3, title);
            stm.setString(4, submission);
            
            //Executes the query
            stm.executeUpdate();
            
            //Returns true unless the execute command hits an Exception
            return true;
        } catch (SQLException e) {
            System.out.println("\nThere was SQL error when submitting an assignment\n");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failed to close connections.");
            }
        }
        return false;
    }
    
}
        
      

