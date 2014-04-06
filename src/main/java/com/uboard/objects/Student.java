package com.uboard.objects;

import com.uboard.connections.MyDatabase;
import com.uboard.interfaces.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Franco
 */
public class Student implements User{
    
    private String          username;
    private String          email;
    private String          name;
    private String          paypalEmail;
    private String          about;
    private int             totalRating;
    private int             posRating;
    private List<Lesson>    createdLessons;
    private List<Class>     enrolledClasses;

    private static final String query_getUser = 
        "SELECT * FROM \"UBOARD\".u_user WHERE username = ?";
    
    private static final String query_getTotalRating =
        "SELECT SUM(tot_rating) AS tot_rating FROM \"UBOARD\".u_lesson WHERE created_by = ?";
    
    private static final String query_getPosRating =
        "SELECT SUM(pos_rating) AS pos_rating FROM \"UBOARD\".u_lesson WHERE created_by = ?";
    
    private static final String query_getCreatedLessons =
        "SELECT * FROM \"UBOARD\".u_lesson WHERE created_by = ?";
    
    private static final String query_getEnrolledClasses =
        "SELECT A.* FROM \"UBOARD\".u_class A, \"UBOARD\".u_enrolled B "
        + "WHERE A.class_id = B.class_id AND B.username = ?";
    
    /**
     * This constructor is to create a new Student object from the specified
     * parameter using the database to retrieve the information. 
     * @param username - The User's username
     */
    public Student(String username) {
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
            this.about              = found.getString("about");
            this.totalRating        = this.getTotalRating();
            this.posRating          = this.getPositiveRating();
            this.createdLessons     = this.getCreatedLesssons();
            this.enrolledClasses    = this.getEnrolledClasses();
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

    public int getTotalRating() {
        Connection con = MyDatabase.connect();
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
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        return 0;
    }

    public int getPositiveRating() {
        Connection con = MyDatabase.connect();
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
        } finally {
            try{
                con.close();
                stm.close();
            } catch(SQLException e) {
                System.out.println("Failes to close connections.");
            }
        }
        return 0;
    }

    public List<Class> getEnrolledClasses() {
        return null;
    }

    public List<Lesson> getCreatedLesssons() {
        return null;
    }

    public boolean createLesson(String title, String username) {
        return false;
    }

    public boolean setProfile() {
        return false;
    }
}
