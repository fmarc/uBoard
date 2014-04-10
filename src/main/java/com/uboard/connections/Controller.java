/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uboard.connections;

import com.uboard.objects.Comment;
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
            
        } else if(page.equals("home")){
            
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
            
        } else if(page.equals("profile")){
            
        } else if(page.equals("class")){
            
        } else if(page.equals("assignment")){
            
        } else if(page.equals("stream")){
            
        } else {
            
        }
        
        out.close();
    }
}
