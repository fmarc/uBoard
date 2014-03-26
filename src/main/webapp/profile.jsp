<%-- 
    Document   : profile
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
        <link href='http://fonts.googleapis.com/css?family=Source+Code+Pro:200,300,400,500,600,700,900' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="CSS/jquery-ui-1.10.4.min.css">
        <script src="scripts/jquery-1.10.2.js"></script>
        <script src="scripts/jquery-ui-1.10.4.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="CSS/master.css">
        <link rel="stylesheet" type="text/css" href="CSS/profile.css">
        
        <script src="scripts/home.js"></script>
        <script src="scripts/profile.js"></script>
    </head>
    <body>
        
            <!--
                JSP IMPLEMENTATION PSEUDOCODE
                
                Is the user logged in? {
                    -YES {
                        Is the user accessing his own profile? {
                        -YES {
                            Show special information only the user should know about
                            Enable Editing
                        }
                        -NO {
                            Show regular user information
                            Disable Editing
                        }
                    }
            
                   -NO {
                        Show regular user information
                        Disable Editing
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
            
            <div id="edit-save" class="edit-profile" onclick="saveUserData(this);"><p>Edit</p></div>
            
            <div id="content">
                <div id="user" class="box">
                    <div id="user-top">
                        <img width="80" id="student-image" src="/images/profile/user.png" />
                        <div id="user-info-top">
                            <h2 id="username">USERNAME</h2>
                            <div id="rating-box">rating here</div>
                        </div>
                    </div>
                    <div id="user-bottom">
                        <h3>Name:</h3>
                        <p id="name" class="edit">First_Name Last_Name</p>
                        <h3>Email:</h3>
                        <p>Email@Email.com</p>
                        <h3>About:</h3>
                        <textarea id="about" class="edit-area" maxlength="500" placeholder="Tell others about yourself!" disabled></textarea>
                    </div>
                </div>
                
                <div id="paypal" class="box">
                    <div class="box-header">
                        <h1>PAYPAL</h1>
                    </div>
                    <div class="box-content">
                        <h3>Paypal Registered Email:</h3>
                        <p id="paypal-email" class="edit" placeholder="Paypal account email">Email@Email.com</p>
                    </div>
                    
                </div>
                
                <div id="lessons-created" class="box">
                    <div class="box-header">
                        <h1>LESSONS CREATED</h1>
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
                    </div>
                </div>
                
                <div id="classes-created" class="box">
                    <div class="box-header">
                        <h1>CLASSES CREATED</h1>
                    </div>
                    <div class="box-content">
                        <div class="created-class">
                            <div class="class-rating">
                                <p class="positive-rating">15</p>
                            </div>
                            <a class="content-title" href="home.jsp">Title of the Lesson goes in here (change to valid link)</a>
                        </div>
                        <div class="created-class">
                            <div class="class-rating">
                                <p class="negative-rating">-30</p>
                            </div>
                            <a class="content-title" href="home.jsp">Title of the Lesson goes in here (change to valid link)</a>
                        </div>
                    </div>
                </div>
                
                <div id="classes-taken" class="box">
                    <div class="box-header">
                        <h1>CLASSES TAKEN</h1>
                    </div>
                    <div class="box-content">
                        <div class="class-taken">
                            <div class="class-rating">
                                <p class="positive-rating">15</p>
                            </div>
                            <a class="content-title" href="home.jsp">Title of the Lesson goes in here (change to valid link)</a>
                        </div>
                        <div class="class-taken">
                            <div class="class-rating">
                                <p class="negative-rating">-30</p>
                            </div>
                            <a class="content-title" href="home.jsp">Title of the Lesson goes in here (change to valid link)</a>
                        </div>
                        <p>Classes Created go here.
                            Won't display if a user is looking at another user's profile
                        </p>
                    </div>
                </div>
            </div>
    </body>
</html>