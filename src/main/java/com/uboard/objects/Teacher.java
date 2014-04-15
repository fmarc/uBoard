package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import com.uboard.interfaces.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Franco
 */
public class Teacher implements User{
    
    public String          username;
    public String          email;
    public String          name;
    public String          paypalEmail;
    public String          about;
    public int             totalRating;
    public int             posRating;
    
    public List<Lesson>    createdLessons;
    public List<Class>     createdClasses;
    public List<Class>     enrolledClasses;

    private static final String query_getUser = 
        "SELECT * FROM \"UBOARD\".u_user WHERE username = ?";
    
    private static final String query_getTotalRating =
        "SELECT SUM(tot_rating) AS tot_rating FROM \"UBOARD\".u_lesson WHERE created_by = ?";
    
    private static final String query_getPosRating =
        "SELECT SUM(pos_rating) AS pos_rating FROM \"UBOARD\".u_lesson WHERE created_by = ?";
    
    private static final String query_getCreatedLessons =
        "SELECT * FROM \"UBOARD\".u_lesson WHERE created_by = ?";
    
    private static final String query_getCreatedClasses = 
        "SELECT * FROM \"UBOARD\".u_class WHERE created_by = ? ";
    
    private static final String query_getEnrolledClasses =
        "SELECT A.* FROM \"UBOARD\".u_class A, \"UBOARD\".u_enrolled B "
        + "WHERE A.class_id = B.class_id AND B.username = ? ";
    
    /**
     * This constructor is to create a new Student object from the specified
     * parameter using the database to retrieve the information. 
     * @param username - The User's username
     */
    public Teacher(String username) {
        Connection con = MyDatabase.connect();
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getUser);
            stm.setString(1, username);
            ResultSet found = stm.executeQuery();
            
            //Check for the record to see if the user credentials match the 
            //ones in the Database
            found.next();
            this.username           = username;
            this.email              = found.getString("email");
            this.name               = found.getString("name");
            this.paypalEmail        = found.getString("paypal_email");
            if(this.paypalEmail == null) this.paypalEmail = "";
            this.about              = found.getString("about");
            if(this.about == null) this.about = "";
            this.totalRating        = getTotalRating(con);
            this.posRating          = getPositiveRating(con);
            this.createdLessons     = getCreatedLesssons(con);
            this.createdClasses     = getCreatedClasses(con);
            this.enrolledClasses    = getEnrolledClasses(con);
        } catch (SQLException e) {
            System.out.println("There was SQL error when creating a Student object:\n");
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
    
    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }
    
    public String getPaypalEmail() {
        return this.paypalEmail;
    }
    
    public String getAboutSection() {
        return this.about;
    }

    public String getName() {
        return this.name;
    }

    public int getTotalRating(Connection con) {
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getTotalRating);
            stm.setString(1, this.username);
            ResultSet found = stm.executeQuery();
            
            //Returns one sum of all the User's ratings
            found.next();
            return found.getInt("tot_rating");
            
        } catch (SQLException e) {
            System.out.println("There was SQL error when retrieving the total rating of a Student object:\n");
            e.printStackTrace();
        }
        return 0;
    }

    public int getPositiveRating(Connection con) {
        PreparedStatement stm = null;
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getPosRating);
            stm.setString(1, this.username);
            ResultSet found = stm.executeQuery();
            
            //Returns one sum of all the User's ratings
            found.next();
            return found.getInt("pos_rating");
            
        } catch (SQLException e) {
            System.out.println("There was SQL error when retrieving the positive rating of a Student object:\n");
            e.printStackTrace();
        }
        
        return 0;
    }

    public List<Class> getEnrolledClasses(Connection con) {
        PreparedStatement stm = null;
        List<Class> classes = new ArrayList<Class>();
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getEnrolledClasses);
            stm.setString(1, this.username);
            ResultSet found = stm.executeQuery();
            
            //Returns one sum of all the User's ratings
            while(found.next()) {
                classes.add(new Class(con, found.getInt("class_id"), found.getString("class_name")));
            }
        } catch (SQLException e) {
            System.out.println("There was SQL error when retrieving the total rating of a Student object:\n");
            e.printStackTrace();
        }
        
        return classes;
    }

    public List<Lesson> getCreatedLesssons(Connection con) {
        PreparedStatement stm = null;
        List<Lesson> lessons = new ArrayList<Lesson>();
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getCreatedLessons);
            stm.setString(1, this.username);
            ResultSet found = stm.executeQuery();
            
            //Returns one sum of all the User's ratings
            while(found.next()) {
                lessons.add(new Lesson(found.getInt("lesson_id"), found.getInt("class_id"), found.getString("lesson_name"), found.getInt("pos_rating")));
            }
        } catch (SQLException e) {
            System.out.println("There was SQL error when retrieving the total rating of a Student object:\n");
            e.printStackTrace();
        }
        
        return lessons;
    }
    
    public List<Class> getCreatedClasses(Connection con) {
        PreparedStatement stm = null;
        List<Class> classes = new ArrayList<Class>();
        try {
            //Creates a prepared statement that takes care of the query and its
            //values
            stm = con.prepareStatement(query_getCreatedClasses);
            stm.setString(1, this.username);
            ResultSet found = stm.executeQuery();
            
            //Returns one sum of all the User's ratings
            while(found.next()) {
                classes.add(new Class(con, found.getInt("class_id"), found.getString("class_name")));
            }
        } catch (SQLException e) {
            System.out.println("There was SQL error when retrieving the total rating of a Student object:\n");
            e.printStackTrace();
        }
        
        return classes;
    }
    
    /**
     * Checks to see if the user is Enrolled in the class.
     * @param classId
     * @return 
     */
    public boolean isEnrolled(int classId) {
        if(classId == 0) {
            return true;
        }
        for(Class classes: this.enrolledClasses){
            if(classes.classId == classId) return true;
        }
        return false;
    }
    
    public int getPosRating() {
        return this.posRating;
    }

    public List<Class> getEnrollClasses() {
        return this.enrolledClasses;
    }

    public List<Lesson> getCreateLesson() {
        return this.createdLessons;
    }

    public List<Class> getCreateClasses() {
        return this.createdClasses;
    }
}
