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
                        <p>First_Name Last_Name</p>
                        <h3>Email:</h3>
                        <p>Email@Email.com</p>
                        <h3>About:</h3>
                        <textarea maxlength="500">Test</textarea>
                    </div>
                </div>
                
                <div id="paypal" class="box">
                    <div class="box-header">
                        <h1>Paypal</h1>
                    </div>
                    <div class="box-content">
                        <p>Paypal goes here.
                            Won't display if a user is looking at another user's profile
                        </p>
                    </div>
                    
                </div>
                
                <div id="lessons-created" class="box">
                    <div class="box-header">
                        <h1>Lessons Created</h1>
                    </div>
                    <div class="box-content">
                        <p>Lessons Created go here</p>
                    </div>
                </div>
                
                <div id="classes-created" class="box">
                    <div class="box-header">
                        <h1>Classes Created</h1>
                    </div>
                    <div class="box-content">
                        <p>Classes Created go here.
                            Won't display if a user is looking at another user's profile
                        </p>
                    </div>
                </div>
                
                <div id="classes-taken" class="box">
                    <div class="box-header">
                        <h1>Classes Taken</h1>
                    </div>
                    <div class="box-content">
                        <p>Classes Taken go here</p>
                    </div>
                </div>
            </div>
    </body>
</html>