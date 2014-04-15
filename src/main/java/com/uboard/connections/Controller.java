/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uboard.connections;

import com.uboard.objects.Assignment;
import com.uboard.objects.Comment;
import com.uboard.objects.Feedback;
import com.uboard.objects.Lesson;
import com.uboard.objects.Class;
import com.uboard.objects.SubAssignment;
import com.uboard.objects.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Servlet acts as the receiver for all the AJAX or POST requests handled
 * by the Client.
 * @author Franco
 */
public class Controller extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        out.write("Test!");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        Utilities utilities = Utilities.getInstance();
        
        String page         = request.getParameter("page");
        String method       = request.getParameter("method");
        String sessionId    = request.getSession().getId();
        
        if(page.equals("all")){
            
            //Handles the Login
            if(method.equals("login")){
                String username     = request.getParameter("username");
                String password     = request.getParameter("password");
                
                
                if(utilities.checkLogin(sessionId, username, password)) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
            //Handles the Logout
            if(method.equals("logout")){
                if(utilities.logout(sessionId)) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
            //Handles creating new lessons
            if(method.equals("createLesson")) {
                String username     = request.getParameter("username");
                int classId         = Integer.parseInt(request.getParameter("classId"));
                String title        = request.getParameter("title");
                
                out.write(Lesson.createLesson(username, classId, title) + "");
            }
            
            if(method.equals("createClass")) {
                String username     = request.getParameter("username");
                String name         = request.getParameter("name");
                float price         = Float.parseFloat(request.getParameter("price"));
                int limit           = Integer.parseInt(request.getParameter("limit"));
                
                out.write(Class.createClass(username, name, price, limit) + "");
            }
            
        } else if(page.equals("register")){
            
            //Handles the Registering
            if(method.equals("register")) {
                String username     = request.getParameter("username");
                String email        = request.getParameter("email");
                String name         = request.getParameter("name");
                String password     = request.getParameter("password");
                
                if(utilities.usernameExists(username)){
                    out.write("2");
                } else if(utilities.emailExists(email)) {
                    out.write("3");
                } else {
                    utilities.register(username, email, name, password);
                    utilities.checkLogin(sessionId, username, password);
                    out.write("1");
                }
            }
            
        } else if(page.equals("lesson")){
            
            if(method.equals("saveLesson")) {
                int lessonId        = Integer.parseInt(request.getParameter("lessonId"));
                String html         = request.getParameter("html");
                
                if(Lesson.saveLesson(lessonId, html)) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
            //Handles the psoting of comments
            if(method.equals("comment")) {
                String username     = request.getParameter("username");
                int classId         = Integer.parseInt(request.getParameter("classId"));
                int lessonId        = Integer.parseInt(request.getParameter("lessonId"));
                String text         = request.getParameter("text");
                
                if(Comment.submitComment(new Comment(username, classId, lessonId, text))){
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
            //Handles the rating of a lesson
            if(method.equals("rate")) {
                String username     = request.getParameter("username");
                int lessonId        = Integer.parseInt(request.getParameter("lessonId"));
                int rating          = Integer.parseInt(request.getParameter("rating"));
                
                if(Lesson.rateLesson(username, lessonId, rating)){
                    out.write("1");
                } else {
                    out.write("0");
                }
                
            }
            
        } else if(page.equals("profile")){
            
            if(method.equals("saveProfile")) {
                String name     = request.getParameter("name");
                String about    = request.getParameter("about");
                String paypal   = request.getParameter("paypal");
                String username = request.getParameter("username");
                
                if(utilities.saveProfile(name, about, paypal, username)){
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
        } else if(page.equals("class")){
            
            if(method.equals("saveClass")) {
                int classId         = Integer.parseInt(request.getParameter("classId"));
                String desc         = request.getParameter("description");
                
                if(Class.saveClass(classId, desc)) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
            //Handles the psoting of comments
            if(method.equals("comment")) {
                String username     = request.getParameter("username");
                int classId         = Integer.parseInt(request.getParameter("classId"));
                int lessonId        = 0;
                String text         = request.getParameter("text");
                
                if(Comment.submitComment(new Comment(username, classId, lessonId, text))){
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
            if(method.equals("enroll")) {
                int classId         = Integer.parseInt(request.getParameter("classId"));
                String username     = request.getParameter("username");
                
                if(Class.enroll(classId, username)) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
            if(method.equals("createAssignment")) {
                int classId         = Integer.parseInt(request.getParameter("classId"));
                String username     = request.getParameter("username");
                String name         = request.getParameter("name");
                String desc         = request.getParameter("description");
                
                out.write(Assignment.createAssignment(classId, username, name, desc)+ "");
            }
            
        } else if(page.equals("assignment")){
            
            if(method.equals("subAssignment")){
                int assignId        = Integer.parseInt(request.getParameter("assignmentId"));
                String submitBy     = request.getParameter("username");
                String title        = request.getParameter("title");
                String submission   = request.getParameter("submission");
                
                if(SubAssignment.submitAssignment(assignId, submitBy, title, submission)){
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            else if(method.equals("feedback")){
                int assignId        = Integer.parseInt(request.getParameter("assignId"));
                String submitby     = request.getParameter("submitBy");
                String feedBack     = request.getParameter("feedback");
                
                
                if(Feedback.submitFeedback(new Feedback(assignId, submitby, feedBack))){
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
        } else if(page.equals("stream")){
            
            if(method.equals("saveStream")) {
                int classId         = Integer.parseInt(request.getParameter("classId"));
                String stream       = request.getParameter("streamName");
                
                if(Class.saveStream(classId, stream)) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
        } else {
            
        }
        
        out.close();
    }
}
