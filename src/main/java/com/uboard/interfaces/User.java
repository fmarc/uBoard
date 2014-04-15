package com.uboard.interfaces;

import com.uboard.objects.Class;
import com.uboard.objects.Lesson;
import java.sql.Connection;
import java.util.List;

/**
 * This interface is mainly used for the Teacher and Student Classes to 
 * implement their respective similar methods.
 * 
 * @author Franco
 */
public interface User {
    
    /**
     * Gets the user's username
     * @return String - username
     */
    public String getUsername();
    
    /**
     * Gets the user's email
     * @return String - email
     */
    public String getEmail();
    
    /**
     * Gets the user's PayPal email
     * @return String - Paypal Email
     */
    public String getPaypalEmail();
    
    /**
     * Gets the user's about section
     * @return String - About Section
     */
    public String getAboutSection();
    
    /**
     * Gets the user's name
     * @return String - name
     */
    public String getName();
    
    
    public int getPosRating();
    
    public List<Class> getEnrollClasses();
    
    public List<Lesson> getCreateLesson();
    
    public List<Class> getCreateClasses();
    
    /**
     * Gets the user's total rating based
     * @param con - The Database connection
     * @return int - totalRating
     */
    public int getTotalRating(Connection con);
    
    /**
     * Gets the user's total positive rating
     * @param con - The Database connection
     * @return int - totalPosRating
     */
    public int getPositiveRating(Connection con);
    
    /**
     * Gets all the classes this user is enrolled in
     * @param con - The Database connection
     * @return List<Class> - A list of all classes enrolled
     */
    public List<Class> getEnrolledClasses(Connection con);
    
    /**
     * Gets all the Lessons created by this user
     * @param con - The Database connection
     * @return List<Lesson> - All the lessons created by this user.
     */
    public List<Lesson> getCreatedLesssons(Connection con);
    
    /**
     * Checks to see of the user is enrolled in this lesson
     * @param classId
     * @return boolean - Indicates success or failure
     */
    public boolean isEnrolled(int classId);
   
}
