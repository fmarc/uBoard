package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Franco
 */
public class SubAssignment {
    public int assignId;
    public String submitBy;
    public String title;
    public String submission;
    

    
    private static final String query_getSubAssign = 
        "SELECT * FROM \"UBOARD\".u_sub_assignments WHERE assignment_id = ?";
    
    private static final String query_submitAssignment = 
        "INSERT INTO \"UBOARD\".u_sub_assignments (assign_id, submitted_by, title, submission) "
        + "VALUES (?, ?, ?, ?)"; 
        
    public SubAssignment(int assignId){
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try{
            
            stm = con.prepareStatement(query_getSubAssign);
            stm.setInt(1, assignId);
            ResultSet found = stm.executeQuery();
            
            if(found.next()){
            this.assignId = assignId;
            this.submitBy = found.getString("submitted_by");
            this.title = found.getString("title");
            this.submission = found.getString("submission");
            }
            } catch (SQLException e) {
                    System.out.println("There was an SQL error when creating submitted assignment\n");
                    e.printStackTrace();
                    } finally{
                            try{
                            con.close();
                            stm.close();
                            } catch(SQLException e){
                            System.out.println("Failed to close connections.");
                            }
                            }
        }
    
    }
        
      

