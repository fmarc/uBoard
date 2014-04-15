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
 * @editedBy Francisco Marcano
 */
public class SubAssignment {
    public int subAssignId;
    public String submitBy;
    public String title;
    public String submission;
    public Feedback feedback;
    
    private static final String query_getSubAssingment = 
        "SELECT * FROM \"UBOARD\".u_sub_assignments WHERE assignment_id = ? AND submitted_by = ? ";
    
    private static final String query_getSubAssign = 
        "SELECT * FROM \"UBOARD\".u_sub_assignments WHERE assignment_id = ? ";
    
    private static final String query_submitAssignment = 
        "INSERT INTO \"UBOARD\".u_sub_assignments (assignment_id, submitted_by, title, submission) "
        + "VALUES (?, ?, ?, ?)"; 
        
    /**
     * Crates a SubAssingment object based on the parameters passed
     * @param con - The database Connection
     * @param assignid - The Assignment ID under which it was created
     * @param submitby - The student who submitted the assignment
     * @param title - The title of the assignment
     * @param submission - The submission
     */
    public SubAssignment(Connection con, int assignid, String submitby, String title, String submission){
        this.subAssignId = assignid;
        this.submitBy = submitby;
        this.title = title;
        this.submission = submission;
        this.feedback = Feedback.getFeedback(con, assignid, submitby);
    }
    
    public SubAssignment(int assignId, String username) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;  
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getSubAssingment);
            stm.setInt(1, assignId);
            stm.setString(2, username);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            if(found.next()){
                this.subAssignId    = assignId;
                this.submitBy       = username;
                this.title          = found.getString("title");
                this.submission     = found.getString("submission");
                this.feedback       = Feedback.getFeedback(con, subAssignId, submitBy);
            } else {
                this.subAssignId    = assignId;
                this.submitBy       = username;
                this.title          = "";
                this.submission     = "";
                this.feedback       = Feedback.getFeedback(con, subAssignId, submitBy);
            }            
        } catch (SQLException e) {
            System.out.println("There was SQL error when getting assignments from class object:\n");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
    }
    
    /**
     * Gets a list of SubAssingments for the Assignments
     * @param con - The Database connection
     * @param assignId - The assignment ID
     * @return Set<SubAssingment> - A list of Assignments for the Assignment passed.
     */
    public static Set<SubAssignment> getSubAssignment(Connection con, int assignId){
        PreparedStatement stm = null;
        Set<SubAssignment> assn = new HashSet<SubAssignment>();
        try{
            stm = con.prepareStatement(query_getSubAssign);
            stm.setInt(1, assignId);
            ResultSet found = stm.executeQuery();
            
            while(found.next()){
            assn.add(new SubAssignment(con, assignId, found.getString("submitted_by"), found.getString("title"), found.getString("submission")));
            }
        } catch (SQLException e) {
            System.out.println("There was an SQL error when creating submitted assignment\n");
            e.printStackTrace();
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failed to close connections.");
            }
        }
        
        return assn;
    } 
    
    
    /**
     * Submits a new Submission for the Assignment
     * @param assignid - The Assignment ID
     * @param submitby - The creator of the SubAssingment
     * @param title - The title of the SubAssingment
     * @param submission - The text of the submission
     * @return boolean - Indicates success or failure.
     */
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
        
      

