package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    //List of Rates where <Username, Rating>
    public Map<String, Integer> raters;
    
    //List of Comments where <Username, Comment Text>
    public ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    
    private static final String query_createClass = 
        "INSERT INTO \"UBOARD\".u_class (class_id, created_by, class_name, "
            + "enrolled_num, price, class_limit) "
        + "VALUES (?, ?, ?, ?, ?, ?) RETURNING lesson_id";
    
    private static final String query_getClass = 
        "SELECT * FROM \"UBOARD\".u_class WHERE class_id = ?";
    
    private static final String query_saveLesson = 
        "UPDATE \"UBOARD\" SET lesson_content = ? WHERE lesson_id = ?";
    
    private static final String query_updateRating = 
        "UPDATE \"UBOARD\".u_lesson SET pos_rating = pos_rating + ? "
        + "AND tot_rating = tot_rating + 1";
    
    private static final String query_rateClass = 
        "INSERT INTO \"UBOARD\".u_rated (username, class_id, rating) "
        + "VALUES (?, ?, ?)";
    
    private static final String query_getRaters = 
        "SELECT username, rating FROM \"UBOARD\".u_rated WHERE lesson_id = ?";
    
   



}
