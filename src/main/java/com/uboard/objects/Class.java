package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Franco
 */
public class Class {
    public int classId;
    public String createdBy;
    public String className;
    public int numEnrolled;
    public float price;
    public int enrollLimit;
    public int posRating;
    public int totRating;
    public String description;
    public String streamName;
    public String html;
    
    //REMINDER: USE LESSON CONSTRUCTOR TO CREATE CLASS LESSONS
    
    //List of Comments where <Username, Comment Text>
    public Set<Assignment> assignments = new HashSet<Assignment>();
    
    //List of Lessons
    public Set<Lesson> lessons = new HashSet<Lesson>();
    
    private static final String query_createClass = 
        "INSERT INTO \"UBOARD\".u_class (created_by, class_name, "
            + "price, class_limit) "
        + "VALUES (?, ?, ?, ?) RETURNING class_id";
    
    private static final String query_getClass = 
        "SELECT * FROM \"UBOARD\".u_class WHERE class_id = ?";
    
    public static int createClass(String username, int classId, String title) {
        return 0;
    }
    
    public static boolean saveClass(int classId, String html) {
        return false;
    }
    
}
