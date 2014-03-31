<%-- 
    Document   : register
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
        <title>Register - uBoard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="icon" type="image/png" href="/favicon.png">
        <link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        
        <link rel="stylesheet" type="text/css" href="CSS/master.css">
        <link rel="stylesheet" type="text/css" href="CSS/register.css">
        
        <link rel="stylesheet" type="text/css" href="CSS/jquery-ui-1.10.4.min.css">
        <script src="scripts/jquery-1.10.2.js"></script>
        <script src="scripts/jquery-ui-1.10.4.min.js"></script>
        
        <script src="scripts/home.js"></script>
        <script src="scripts/register.js"></script>
        
    </head>
    <body>
        <!--
            /*
                JSP IMPLEMENTATION PSEUDOCODE
                
                Is the user logged in? {
                    -YES {
                        Redirect the user to his profile page (using user's username as an argument)
                    }
                }
           */
        -->
        
            <%
                String id = "";
                boolean user = false;
                
                if (request.getParameter("id") != null){
                    id = request.getParameter("id");
                    user = true;
                }
            %>
            
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
                        <%if(user) {%>
                            <div id="login">
                                <img id="user-hover" src="/images/login/user-img.png"><span style="position:relative; top:15px;"><%=id%></span>
                            </div>
                            <div id="logged-in">
                                <div onclick="window.location = '/profile.jsp'"><img src="/images/login/view-profile.png"><p>View Profile</p></div>
                                <div onclick="toggleModal('create-lesson-modal');"><img src="/images/login/create-lesson.png"><p>Create Lesson</p></div>
                                <div onclick="toggleModal('create-class-modal');"><img src="/images/login/create-class.png"><p>Create Class</p></div>
                                <div onclick="window.location = '/home.jsp'"><img src="/images/login/logout.png"><p>Log Out</p></div>
                            </div>
                        <%} else {%>
                            <div id="login">
                                <span style="position:relative; top:15px;">LOG IN</span>
                            </div>
                            <div id="login-modal">
                                <form id="login-form">
                                    <input name="id" type="text" id="login-user" class="text-input" placeholder="Username">
                                    <span id="login-user-pic"></span>
                                    <input type="password" id="login-pass" class="text-input" placeholder="Password">
                                    <span id="login-pass-pic"></span>
                                    <input id="login-button" class="button" type="submit" value="LOG IN">
                                    <a href="/register.jsp"><input href="/register.jsp" id="register-button" class="button" type="button" value="REGISTER" /></a>
                                </form>
                            </div>
                        <%}%>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
            
            <div id="content">
                <div class="content-box">
                    <h1>REGISTER</h1>
                    <form id="register" actioin="register.jsp">
                        <p>Username: </p>
                        <input type="text" name="username" placeholder="Username" />
                        <p>First Name:</p>
                        <input type="text" name="fname" placeholder="First Name" />
                        <p>Last Name:</p>
                        <input type="text" name="lname" placeholder="Last Name"/>
                        <p>Email:</p>
                        <input type="email" name="email" placeholder="Email"/>
                        <p>Password:</p>
                        <input type="password" name="password" placeholder="Password"/>
                        <input type="submit" value="Submit" />
                    </form>
                </div>
            </div>
            
            <div id="create-lesson-modal" class="box-modal">
                <h2>Create New Lesson</h2>
                <p>Please fill out the following information to create a new lesson.</p>
                <h4>Title</h4>
                <input type="text" id="lesson-title" placeholder="Lesson Title">
                <input type="button" onclick="createNewLesson();" value="Create">
                <input type="button" onclick="toggleModal('create-lesson-modal');" value="Cancel">
            </div>

            <div id="create-class-modal" class="box-modal">
                <h2>Create New Class</h2>
                <p>Please fill out the following information to create a new class.</p>
                <h4>Title</h4>
                <input type="text" id="class-title" placeholder="Class Title">
                <input type="button" onclick="createNewLesson();" value="Create">
                <input type="button" onclick="toggleModal('create-class-modal');" value="Cancel">
            </div>

            <div id="modal"></div>
    </body>
</html>