<%-- 
    Document   : lesson
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
        <title>Create Class - uBoard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="icon" type="image/png" href="/favicon.png">
        <link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        
        <link rel="stylesheet" type="text/css" href="CSS/master.css">
        <link rel="stylesheet" type="text/css" href="CSS/lesson.css">
        
        <link rel="stylesheet" type="text/css" href="CSS/jquery-ui-1.10.4.min.css">
        
        <script src="scripts/jquery-1.10.2.js"></script>
        <script src="scripts/jquery-ui-1.10.4.min.js"></script>
        <script src="scripts/home.js"></script>
        <script src="scripts/lesson.js"></script>
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
                                    <input name="username" type="text" id="login-user" class="text-input" placeholder="Username">
                                    <span id="login-user-pic"></span>
                                    <input type="password" id="login-pass" class="text-input" placeholder="Password">
                                    <span name="password" id="login-pass-pic"></span>
                                    <input id="login-button" class="button" type="submit" value="LOG IN">
                                    <a href="/register.jsp"><input href="/register.jsp" id="register-button" class="button" type="button" value="REGISTER" /></a>
                                </form>
                            </div>
                        <%}%>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
            
        <div id="sidebar" class="hidden">
            <div class="box title-box" title="Title"><img class="handle edit" /><img class="remove edit" /><h1 contenteditable="">Edit me!</h1></div>
            <div class="box text-box" title="Text Box"><img class="handle edit" /><img class="remove edit" /><div contenteditable="">Edit Me!</div></div>
            <div class="box image-box" title="Image Box"><img class="handle edit" /><img class="remove edit" /><div class="images"><img class="image"  src="/images/blanked.png" /><img class="image" src="/images/blanked.png" /><img class="image" src="/images/blanked.png" /></div><div class="buttons edit"><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/></div></div>
            <div class="box video-box" title="Video Box"><img class="handle edit" /><img class="remove edit" /><iframe class="video" width="853" height="480"  src="//www.youtube.com/embed/oJg2_dUHd84" frameborder="0" allowfullscreen></iframe><input type="button" class="change-video edit" onclick="openVideoModal(this);" value="Change Video"/></div>
            <div id="sidebar-click-area" onclick="toggleSideBar();"></div>
            <div id="sidebar-handle" onclick="toggleSideBar();"></div>
        </div>
            
        <div id="content"></div>


        <div id="img-box-modal" class="box-modal">
            <h2>Image Source</h2>
            <p>Please copy and paste the URL for the picture you wish to display</p>
            <input type="text" id="image-url" placeholder="Image Url">
            <input type="button" onclick="changeImage();" value="Save">
            <input type="button" onclick="hideModal();" value="Cancel">
        </div>

        <div id="video-box-modal" class="box-modal">
            <h2>YouTube Video Source</h2>
            <p>Please copy and paste the URL for the YouTube video you wish to display</p>
            <input type="text" id="video-url" placeholder="Video Url">
            <input type="button" onclick="changeVideo();" value="Save">
            <input type="button" onclick="hideModal();" value="Cancel">
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