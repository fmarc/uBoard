package com.uboard.interfaces;

import com.uboard.objects.Class;
import com.uboard.objects.Lesson;
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
    
    /**
     * Gets the user's total rating based
     * @return int - totalRating
     */
    public int getTotalRating();
    
    /**
     * Gets the user's total positive rating
     * @return int - totalPosRating
     */
    public int getPositiveRating();
    
    /**
     * Gets all the classes this user is enrolled in
     * @return List<Class> - A list of all classes enrolled
     */
    public List<Class> getEnrolledClasses();
    
    /**
     * Gets all the Lessons created by this user
     * @return List<Lesson> - All the lessons created by this user.
     */
    public List<Lesson> getCreatedLesssons();
    
    /**
     * Creates a lesson using the provided parameters
     * @param title - Title of the lesson
     * @param username - The username of the user creating the lesson
     * @return boolean - Indicates whether the lesson was created successfully
     */
    public boolean createLesson(String title, String username);
    
    /**
     * Sets the user's profile when they update thir information
     * @return boolean - Indicates whether the profile was updated successfully
     */
    public boolean setProfile();
}
