<%-- 
    Document   : home
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
        <link rel="stylesheet" type="text/css" href="CSS/master.css">
        <link rel="stylesheet" type="text/css" href="CSS/jquery-ui-1.10.4.min.css">
        <script src="scripts/jquery-1.10.2.js"></script>
        <script src="scripts/jquery-ui-1.10.4.min.js"></script>
        <script src="scripts/home.js"></script>
        <script src="scripts/create-class.js"></script>
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
            
        <div id="sidebar" class="hidden">
            <div id="sidebar-click-area"></div>
            <div id="sidebar-handle"></div>
        </div>
            
            <div id="content">
                <div class="content-box">
                    <h1>U | BOARD - COMMUNITY LEARNING</h1>
                    <h2>A tool made for learning and teaching just about anything you want.</h2>
                    <hr>
                    <h3>Overview</h3>
                    <p>
                        Welcome to Uboard, if you are here that means that you must also share the same passion for learning as we do. That same passion
                        is what drove us to create this website. We, the Uboard team, wanted to be able to learn about many different subjects without having
                        to pay for expensive classes. The problem then was how could we learn new things, in an organized fashion, without the difficulties that
                        bring a physical class or browsing endlessly through the internet for information?
                    </p>
                    <p>
                        After a lot of thinking, the Uboard team realized that by creating a medium in which users could both teach and learn in a smooth and
                        organized manner, then learning about new subjects would be fun and painless. Many users in our community are both teaching and learning
                        new things everyday; from how to cook a delicious dinner to the principals of quantum physics, all organized by community members that
                        have experience with these topics.
                    </p>
                    <p>
                        Think you have the same passion that drives our community? Become a member now and start your journey through a cybernetic land filled 
                        with the passion for learning. Also, don't be discouraged! If you think you are great at a particular subject, create a lesson and
                        contribute to the community's growing passion for knowledge.
                    </p>
                </div>
            </div>
    </body>
</html>