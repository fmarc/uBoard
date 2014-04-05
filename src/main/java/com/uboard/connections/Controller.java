/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uboard.connections;

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
        
        String page     = request.getParameter("page");
        String method   = request.getParameter("method");
        
        if(page.equals("all")){
            if(method.equals("login")){
                String username     = request.getParameter("username");
                String password     = request.getParameter("password");
                String sessionId    = request.getSession().getId();
                
                if(utilities.checkLogin(sessionId, username, password)) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }else if(method.equals("logout")){
                if(utilities.logout(request.getSession().getId())) {
                    out.write("1");
                } else {
                    out.write("0");
                }
            }
            
        } else if(page.equals("home")){
            
        } else if(page.equals("register")){
            
        } else if(page.equals("lesson")){
            
        } else if(page.equals("profile")){
            
        } else if(page.equals("class")){
            
        } else if(page.equals("assignment")){
            
        } else if(page.equals("stream")){
            
        } else {
            
        }
        
        out.close();
    }
}
