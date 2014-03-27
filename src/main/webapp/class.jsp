<%-- 
    Document   : class
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>

<%@page import="java.io.BufferedReader"%>
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
        <link rel="stylesheet" type="text/css" href="CSS/jquery-ui-1.10.4.min.css">
        <script src="scripts/jquery-1.10.2.js"></script>
        <script src="scripts/jquery-ui-1.10.4.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="CSS/master.css">
        <link rel="stylesheet" type="text/css" href="CSS/class.css">
        
        <script src="scripts/home.js"></script>
        <script src="scripts/class.js"></script>
    </head>
    <body>
        
            <!--
                JSP IMPLEMENTATION PSEUDOCODE
                
                Is the user logged in? {
                    -YES {
                        Redirect the user to search location (with no parameters, showing recent lessons/classes and highest rated lessons/classes)
                    }
                }
            -->
            <div id="top-banner">
                <div id="top">
                    <div id="search-box">
                        <form>
                            <input type="text" id="content-search" val="" placeholder="Search">
                            <img id="mag-glass" />
                        </form>
                    </div>
                    
                    <a class="logo" title="Take me to the Home Page!" href="/">
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
                                <a href="/register.jsp"><input href="/register.jsp" id="register-button" class="button" type="button" value="REGISTER" /></a>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
            
            <div id="content">
                <div id="enrolled">
                    <h2>ENROLLED</h2>
                    <p id="students-enrolled">20 / 40</p>
                </div>

                <div id="class-enroll" onclick="openEnrollModal();">
                    <p id="enroll">Enroll</p>
                </div>
                
                <div class="class-content">
                    <div id="class-top">
                        <div class="rating" title="Total rating from lessons">
                            <p class="positive-rating">123</p>
                        </div>
                        <div class="class-title">
                            <h1 class="edit">TITLE GOES HERE</h1>
                        </div>
                    </div>
                    
                    <div id="class-banner">
                        <div id="teacher">
                            <p id="teacher-name">ThisISaLongUsername</p>
                        </div>
                    </div>
                    

                    <div id="class-bottom">
                        <div id="description">
                            <h2>Class Description: </h2>
                            <textarea disabled class="edit" id="class-desc">This is where the description of the Class will be. It will contain all the information provided by the user who created this Class.</textarea>
                        </div>
                    </div>
                </div>
                
                <div class="class-content" id="lessons-created" class="box">
                    <div class="box-header">
                        <h1>CLASS LESSONS</h1>
                    </div>
                    <div class="box-content">
                        <div class="created-lesson">
                            <div class="lesson-rating">
                                <p class="positive-rating">501</p>
                            </div>
                            <a class="content-title" href="home.jsp">Title of the Lesson goes in here (change to valid link)</a>
                        </div>
                        <div class="created-lesson">
                            <div class="lesson-rating">
                                <p class="negative-rating">-30</p>
                            </div>
                            <a class="content-title" href="home.jsp">Title of the Lesson goes in here (change to valid link)</a>
                        </div>
                        <div class="create-new-lesson">
                            <p>Create New Lesson</p>
                        </div>
                    </div>
                </div>
                
                <div id="enroll-modal" class="box-modal">
                    <h2>Enroll</h2>
                    <p>Please provide the following information in order to enroll...</p>
                    <input type="text" id="image-url" placeholder="Image Url">
                    <input type="button" onclick="changeImage();" value="Save">
                    <input type="button" onclick="toggleModal();" value="Cancel">
                </div>
                <div id="modal"></div>
            </div>
    </body>
</html>