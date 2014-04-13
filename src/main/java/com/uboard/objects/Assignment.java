package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
/**
 *
 * @author Franco
 */
public class Assignment {
    public int assignmentId;
    public int classId;
    public String createdBy;
    public String assignName;
    public String description;
    
    //queries go here :D
    private static final String query_getAssignment= 
        "SELECT * FROM \"UBOARD\".u_assignment WHERE class_id = ? "
            + "ORDER BY created_date ASC";
    
    private static final String query_submitAssignment = 
        "INSERT INTO \"UBOARD\".u_assignment (class_id, created_by, ass_name, description) "
        + "VALUES (?, ?, ?, ?)";
    
    
    /**
     * Creates a new assignment object from the specified data
     * @param assignmentID - The assignment ID
     * @param classId - The class ID where the assignment was written
     * @param createdBy - The creator of the assignment
     * @param assignName - The name of the assignment
     * @param description - The description of the assignment
     */
    public Assignment(int assignmentId, String createdBy, String assignName, String description) {
        this.assignmentId   = assignmentId;
        this.createdBy      = createdBy;
        this.assignName     = assignName;
        this.description    = description;
    }
    
    public static ArrayList<Assignment> getAssignments(Connection con, int classId){
        PreparedStatement stm = null;
        ArrayList<Assignment> userAssignments = new ArrayList<Assignment>();   
        try{
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getAssignment);
            stm.setInt(1, classId);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            while(found.next()){
                userAssignments.add(new Assignment(found.getInt("assignment_id"), found.getString("created_by"), found.getString("ass_name") , found.getString("description")));
            }            
        }catch (SQLException e) {
            System.out.println("There was SQL error when getting assignments from class object:\n");
            e.printStackTrace();
        }
        return userAssignments;
        }
    
    /**
     * Submits a new comment into the database
     * @param assignment - The assignment to be posted
     * @return boolean - Indicates whether the submit was successful
     */
    public static boolean submitAssignment(int classId, String createdBy, 
            String assignName, String description) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_submitAssignment);
            stm.setInt(1, classId);
            stm.setString(2, createdBy);            
            stm.setString(3, assignName);
            stm.setString(4, description);
            
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
                System.out.println("Failed to close connections.");
            }
        }
        // Returns false if the process was unsuccessful
        return false;
    }
}
