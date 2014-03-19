<%-- 
    Document   : home
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home - uBoard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="icon" type="image/png" href="/favicon.png">
        <link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="CSS/master.css">
        <link rel="stylesheet" type="text/css" href="CSS/jquery-ui-1.10.4.min.css">
        <script src="scripts/jquery-1.10.2.js"></script>
        <script src="scripts/jquery-ui-1.10.4.min.js"></script>
        <script src="scripts/home.js"></script>
    </head>
    <body>
        <%
        //File f = new File(System.getProperty("user.dir").concat("/src/main/webapp/lessons"), "source.html");
        //BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        //bw.write("<html>");
        //bw.write("<body>");
        //bw.write("<h1>ShowGeneratedHtml source</h1>");
        //bw.write("<textarea cols=75 rows=30>");

        //bw.write("</text" + "area>");
        //bw.write("</body>");
        //bw.write("</html>");
        //bw.close();
        %>
        <!--<div id="container">-->
            <div id="top-banner">
                <div id="top">
                    <div id="search-box">
                        <form>
                            <input type="text" id="content-search" val="" placeholder="Search">
                            <img id="mag-glass" />
                        </form>
                    </div>
                    
                    <a class="logo" title="Take me to the Home Page!" href="/"">
                        <img src="/images/logos/uboard_logo_side.png" />
                    </a>
                    
                    <div id="user-auth">
                            <div id="login">
                                <span style="position:relative; top:15px;">LOG IN</span>
                            </div>
                        <div id="login-modal">
                            <form>
                                <input type="text" id="login-user" class="text-input" placeholder="Username">
                                <span id="login-user-pic"></span>
                                <input type="password" id="login-pass" class="text-input" placeholder="Password">
                                <span id="login-pass-pic"></span>
                                <input id="login-button" class="button" type="submit" value="LOG IN">
                                <input id="register-button" class="button" type="button" value="REGISTER">
                            </form>
                        </div>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
            
        <div id="sidebar" class="hidden">
            <div id="sidebar-click-area"></div>
            <div id="sidebar-handle"></div>
        </div>
            
            <div id="content">
                <div class="content-box">
                    <h1><span>U</span> BOARD - COMMUNITY LEARNING</h1>
                    <h2>Help us create a community where you are both the Student and the Teacher</h2>
                    <hr>
                    <br>
                    <p>We would like to welcome you to uBoard! </p>
                </div>
                <div class="content-box">
                    <h1><span>U</span> BOARD - COMMUNITY LEARNING</h1>
                    <h2>Help us create a community where you are both the Student and the Teacher</h2>
                    <hr>
                    <br>
                    <p>We would like to welcome you to uBoard! </p>
                </div>
                <div class="content-box">
                    <h1><span>U</span> BOARD - COMMUNITY LEARNING</h1>
                    <h2>Help us create a community where you are both the Student and the Teacher</h2>
                    <hr>
                    <br>
                    <p>We would like to welcome you to uBoard! </p>
                </div>
                <div class="content-box">
                    <h1><span>U</span> BOARD - COMMUNITY LEARNING</h1>
                    <h2>Help us create a community where you are both the Student and the Teacher</h2>
                    <hr>
                    <br>
                    <p>We would like to welcome you to uBoard! </p>
                </div>
                <div class="content-box">
                    <h1><span>U</span> BOARD - COMMUNITY LEARNING</h1>
                    <h2>Help us create a community where you are both the Student and the Teacher</h2>
                    <hr>
                    <br>
                    <p>We would like to welcome you to uBoard! </p>
                </div>
            </div>
        <!--</div>-->
    </body>
</html>