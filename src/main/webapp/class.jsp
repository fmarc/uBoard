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
            
            <%if(user) {%>
                <div id="edit-save" class="edit-class" onclick="saveClassData(this);"><p>Edit</p></div>
            <%}%>
            
            <div id="content">
                <div id="enrolled">
                    <h2>Enrolled</h2>
                    <p id="students-enrolled">20 / 40</p>
                </div>
                
                <div id="price">
                    <h2>Price</h2>
                    <p>$<span class="class-price">32</span></p>
                </div>
                
                <%if(user) {%>
                    <div id="class-enroll" onclick="openEnrollModal();">
                        <p id="enroll">Enroll</p>
                    </div>
                <%}%>
                    
                <div class="class-content">
                    <div id="class-top">
                        <div class="rating" title="Total rating from lessons">
                            <p class="positive-rating">471</p>
                        </div>
                        <div class="class-title">
                            <h1>TITLE GOES HERE</h1>
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
                            <textarea disabled id="class-desc">This is where the description of the Class will be. It will contain all the information provided by the user who created this Class.</textarea>
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
                        <%if(user) {%>
                            <div class="create-new-lesson" onclick="toggleModal('create-lesson-class-modal');">
                                <p>Create New Lesson</p>
                            </div>
                        <%}%>
                    </div>
                </div>
                
            </div>
            
            <div id="enroll-modal" class="box-modal">
                <h2>Enroll</h2>
                <h2 style="margin:5px; text-align: center; display: inline-block; width: 15%;">$<span class="class-price">32</span></h2>
                <p style="display: inline-block; width: 75%;">The amount stated will be charged to your PayPal account. Please confirm the transaction.</p>
                <br>
                <input type="button" onclick="enrollClass();" value="Confirm">
                <input type="button" onclick="toggleModal('enroll-modal');" value="Cancel">
            </div>

            <div id="create-lesson-class-modal" class="box-modal">
                <h2>Create New Lesson</h2>
                <p>Please fill out the following information to create a new lesson in this class.</p>
                <h4>Title</h4>
                <input type="text" id="lesson-class-title" placeholder="Lesson Title">
                <input type="button" onclick="createNewLesson();" value="Create">
                <input type="button" onclick="toggleModal('create-lesson-class-modal');" value="Cancel">
            </div>

            <div id="error-modal" class="box-modal">
                <h2>Error</h2>
                <p>It seems you don't have a PayPal account set up yet. If you would like to set it up now, please click "OK".</p>
                <br>
                <input type="button" onclick="redirectProfile();" value="Ok">
                <input type="button" onclick="toggleModal('error-modal');" value="Cancel">
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