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
public class Assignment {
    public int assignmentId;
    public int classId;
    public String createdBy;
    public String assignName;
    public String description;
    
    //List of submitted assignments
    public Set<SubAssignment> submitted = new HashSet<SubAssignment>();
    
    private static final String query_createAssignment =
        "INSERT INTO \"UBOARD\".u_assignment (class_id, created_by, ass_name, description) "
        + "VALUES (?, ?, ?, ?) RETURNING assignment_id";
    
    private static final String query_getAssignment = 
        "SELECT * FROM \"UBOARD\".u_assignment WHERE assignment_id = ? ";
    
    private static final String query_getAssignments = 
        "SELECT * FROM \"UBOARD\".u_assignment WHERE class_id = ? "
            + "ORDER BY created_date ASC";
    
    
    /**
     * Creates a new assignment object from the specified data
     * @param con - The database connection
     * @param assignmentId - The assignment ID
     * @param createdBy - The creator of the assignment
     * @param assignName - The name of the assignment
     * @param description - The description of the assignment
     */
    public Assignment(Connection con, int assignmentId, String createdBy, String assignName, String description) {
        this.assignmentId   = assignmentId;
        this.createdBy      = createdBy;
        this.assignName     = assignName;
        this.description    = description;
        this.submitted      = SubAssignment.getSubAssignment(con, assignmentId);
    }
    
    
    /**
     * Creates an assingment based on its ID
     * @param assignId 
     */
    public Assignment(int assignId) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;  
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getAssignment);
            stm.setInt(1, assignId);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            if(found.next()){
                this.assignmentId   = assignId;
                this.classId        = found.getInt("class_id");
                this.createdBy      = found.getString("created_by");
                this.assignName     = found.getString("ass_name");
                this.description    = found.getString("description");
                this.submitted      = SubAssignment.getSubAssignment(con, assignmentId);
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
     * A Light version of the Assignment Class
     * @param assignmentId
     * @param assignName 
     */
    public Assignment(int assignmentId, String assignName){
        this.assignmentId   = assignmentId;
        this.assignName     = assignName;
    }
    
    /**
     * Creates a new assingment based on the parameters given
     * @param classId - The class Id
     * @param username - The creator
     * @param name - The name of the Assignment
     * @param description - The Assignment description
     * @return Integer - The ID of the Assignment
     */
    public static int createAssignment(int classId, String username, String name, String description) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values - Query = (username, classId, lessonId, text)
            stm = con.prepareStatement(query_createAssignment);
            stm.setInt(1, classId);
            stm.setString(2, username);            
            stm.setString(3, name);
            stm.setString(4, description);
            
           //Executes the query
            ResultSet data = stm.executeQuery();
            
            //Returns the Lesson ID generated by the query
            if(data.next()){
                return data.getInt("assignment_id");
            }   
        }catch (SQLException e) {
            System.out.println("There was SQL error when creating a new assignments:\n");
            e.printStackTrace();
        } 
        return 0;
    }
    
    /**
     * Gets all the assignments for a specific class
     * @param con - The Database connection to be used
     * @param classId - The class ID
     * @return Set<Assignment> -  A list of all the assignments in the class
     */
    public static Set<Assignment> getAssignments(Connection con, int classId){
        PreparedStatement stm = null;
        Set<Assignment> userAssignments = new HashSet<Assignment>();   
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getAssignments);
            stm.setInt(1, classId);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            while(found.next()){
                userAssignments.add(new Assignment(found.getInt("assignment_id"), found.getString("ass_name")));
            }            
        } catch (SQLException e) {
            System.out.println("There was SQL error when getting assignments from class object:\n");
            e.printStackTrace();
        }
        return userAssignments;
    }
}
