<%-- 
    Document   : stream
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
        <link rel="stylesheet" type="text/css" href="CSS/stream.css">
        
        <script src="scripts/home.js"></script>
        <script src="scripts/stream.js"></script>
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
                
                if (request.getParameter("id") != null || request.getParameter("search") != null){
                    id = request.getParameter("id");
                    user = true;
                }
            %>

            <div id="top-banner">
                <div id="top">
                    <div id="search-box">
                        <form id="search-form">
                            <input name="search" type="text" id="content-search" val="" placeholder="Search">
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
                    
            <%if(user){%>
            <div id="save" onclick="saveStream('123456');"><p>Save</p></div>
            <%}%>
                    
            <div id="content">
                <div class="class-title">
                    <h1>Everything You Need To Know About Pianos</h1>
                </div>
                <div id="class-banner">
                        <div id="teacher">
                            <p id="teacher-name">mgonz108</p>
                        </div>
                    </div>
                <div id="stream-container">
                    <object id="stream-video" type="application/x-shockwave-flash" height="378" width="620" id="live_embed_player_flash" data="http://www.twitch.tv/widgets/live_embed_player.swf?channel=datmodz" bgcolor="#000000">
                        <param name="allowFullScreen" value="true" />
                        <param name="allowScriptAccess" value="always" />
                        <param name="allowNetworking" value="all" />
                        <param name="movie" value="http://www.twitch.tv/widgets/live_embed_player.swf" />
                        <param id="twitch-param" name="flashvars" value="hostname=www.twitch.tv&channel=datmodz&auto_play=true&start_volume=25" />
                    </object>
                    <iframe id="stream-chat" frameborder="0" scrolling="no" src="http://twitch.tv/datmodz/chat?popout=" height="500" width="350"></iframe>
                </div>
                <%if(user){%>
                <input id="change-stream" type="button" onclick="toggleModal('change-stream-modal');" value="Change Stream Channel">
                <%}%>
            </div>
                
            <div id="create-lesson-modal" class="box-modal">
                <h2>Create New Lesson</h2>
                <p>Please fill out the following information to create a new lesson.</p>
                <h4>Title</h4>
                <input type="text" id="lesson-title" placeholder="Lesson Title">
                <input type="button" onclick="createNewLesson();" value="Create">
                <input type="button" onclick="toggleModal('create-lesson-modal');" value="Cancel">
            </div>
            
            <div id="change-stream-modal" class="box-modal">
                <h2>Change Stream Channel</h2>
                <p>Please fill out the following information to change the stream channel.</p>
                <h4>Channel Name:</h4>
                <input type="text" id="stream-channel" placeholder="Stream Channel Name">
                <input type="button" onclick="changeStream();" value="Create">
                <input type="button" onclick="toggleModal('change-stream-modal');" value="Cancel">
            </div>

             <div id="create-class-modal" class="box-modal">
                <h2>Create New Class</h2>
                <p>Please fill out the following information to create a new class.</p>
                <h4>Title</h4>
                <input type="text" id="class-title" placeholder="Class Title">
                <h4>Price ($):</h4>
                <input type="text" id="class-price" placeholder="Class Price">
                <h4>Class Enrollment Limit:</h4>
                <input type="text" id="class-limit" placeholder="Class Limit">
                <input type="button" onclick="createNewLesson();" value="Create">
                <input type="button" onclick="toggleModal('create-class-modal');" value="Cancel">
            </div>
            
            <div id="save-confirm-modal" class="box-modal">
                <h2>Stream Saved Successfully!</h2>
                <input type="button" onclick="toggleModal('save-confirm-modal');" value="Ok">
            </div>

            <div id="modal"></div>
    </body>
</html>