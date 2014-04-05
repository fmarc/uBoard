/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uboard.test;

import com.uboard.connections.MyDatabase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Franco
 */
public class TestConnection {
    
    public static void main (String[] args) throws SQLException{
        Connection con = MyDatabase.getConnection();
        String query = "SELECT * FROM \"UBOARD\".u_user";

        ResultSet data = con.prepareStatement(query).executeQuery();
        
        while(data.next())
            System.out.println("User: " + data.getString("username") + "\tPassword: " + data.getString("user_password"));
    } 
    
}
