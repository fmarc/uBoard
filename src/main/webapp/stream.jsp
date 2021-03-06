<%-- 
    Document   : stream
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>

<%@page import="com.uboard.objects.Utilities"%>
<%@page import="com.uboard.interfaces.User"%>
<%@page import="com.uboard.objects.Student"%>
<%@page import="com.uboard.objects.Teacher"%>
<%@page import="com.uboard.objects.Class"%>
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
            <%
                Utilities util = Utilities.getInstance();
                User user = null;
                
                boolean isOwner = false;
                
                String title = "";
                Class object = null;
                
                try {
                    user = util.getOnlineUser(session.getId());
                } catch (Exception e){}
                
                if(user == null) {
                    response.sendRedirect("/");
                }
                
                if((title = request.getParameter("class_id")) != null){
                    
                    object = new Class(Integer.parseInt(title));
                    
                    if(object.classId == 0){
                        %>Assignment not found<%
                        return;
                    }
                    
                } else {
                    response.sendRedirect("/");
                    return;
                }
                
                if(user != null && user.getUsername().equals(object.createdBy)){
                    isOwner = true;
                }
                
                if(user != null && !object.isEnrolled(user.getUsername()) && !isOwner){
                    %>Sorry, you are not enrolled in this Class. You will not be able to see this stream.<%
                    return;
                }
            %>

            <div id="top-banner">
                <div id="top">
                    <div id="search-box">
                        <form id="search-form" action="/home.jsp">
                            <input name="search" type="text" id="content-search" placeholder="Search">
                            <img id="mag-glass" />
                        </form>
                    </div>
                    
                    <a class="logo" title="Take me to the Home Page!" href="/">
                        <img src="/images/logos/uboard_logo_side.png" />
                    </a>
                    
                    <div id="user-auth">
                        <%if(user != null) {%>
                            <div id="login">
                                <%if(user instanceof Teacher){%>
                                <img id="teacher-hover" src="/images/login/teacher-auth-small.png" /><span style="position:relative; top:15px;"><%=user.getUsername()%></span>
                                <%} else {%>
                                <img id="user-hover" src="/images/login/user-img.png" /><span style="position:relative; top:15px;"><%=user.getUsername()%></span>
                                <%}%>
                            </div>
                            <div id="logged-in">
                                <%if(user instanceof Teacher){%>
                                <div onclick="window.location = '/profile.jsp?username=<%=user.getUsername()%>'"><img src="/images/login/teacher-auth-small-hover.png"><p>View Profile</p></div>
                                    <div onclick="toggleModal('create-class-modal');"><img src="/images/login/create-class.png"><p>Create Class</p></div>
                                <%} else {%>
                                    <div onclick="window.location = '/profile.jsp?username=<%=user.getUsername()%>'"><img src="/images/login/view-profile.png"><p>View Profile</p></div>
                                <%}%>
                                <div onclick="toggleModal('create-lesson-modal');"><img src="/images/login/create-lesson.png"><p>Create Lesson</p></div>
                                <div onclick="logout()"><img src="/images/login/logout.png"><p>Log Out</p></div>
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
                                <div id="login-error"><img src="/images/login/login-error.png" style="position: relative; top: 2px; display: inline-block; width: 18px; margin: 0px;"><p style="display: inline-block;color:  whitesmoke;line-height: 20px;margin: 0px 0px 0px 15px;">Invalid login, please try again</p></div>
                            </div>
                        <%}%>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
                    
            <%if(isOwner){%>
            <div id="save" onclick="saveStream('<%=object.classId%>');"><p>Save</p></div>
            <%}%>
                    
            <div id="content">
                <div class="class-title">
                    <h1><%=object.className%></h1>
                </div>
                <div id="class-banner">
                        <div id="teacher">
                            <p id="teacher-name"><%=object.createdBy%></p>
                        </div>
                    </div>
                <div id="stream-container">
                    <object id="stream-video" type="application/x-shockwave-flash" height="378" width="620" id="live_embed_player_flash" data="http://www.twitch.tv/widgets/live_embed_player.swf?channel=<%=object.streamName%>" bgcolor="#000000">
                        <param name="allowFullScreen" value="true" />
                        <param name="allowScriptAccess" value="always" />
                        <param name="allowNetworking" value="all" />
                        <param name="movie" value="http://www.twitch.tv/widgets/live_embed_player.swf" />
                        <param id="twitch-param" name="flashvars" value="hostname=www.twitch.tv&channel=<%=object.streamName%>&auto_play=true&start_volume=25" />
                    </object>
                    <iframe id="stream-chat" frameborder="0" scrolling="no" src="http://twitch.tv/<%=object.streamName%>/chat?popout=" height="500" width="350"></iframe>
                </div>
                <%if(isOwner){%>
                <input id="change-stream" type="button" onclick="toggleModal('change-stream-modal');" value="Change Stream Channel">
                <%}%>
            </div>
                
            
            <%if(user != null){%>
            <div id="change-stream-modal" class="box-modal">
                <h2>Change Stream Channel</h2>
                <p>Please fill out the following information to change the stream channel.</p>
                <h4>Channel Name:</h4>
                <input type="text" id="stream-channel" placeholder="Stream Channel Name">
                <input type="button" onclick="changeStream();" value="Change">
                <input type="button" onclick="toggleModal('change-stream-modal');" value="Cancel">
            </div>
            
            <div id="create-lesson-modal" class="box-modal">
                <h2>Create New Lesson</h2>
                <p>Please fill out the following information to create a new lesson.</p>
                <h4>Title</h4>
                <input type="text" id="lesson-title" placeholder="Lesson Title">
                <input type="button" onclick="createNewLesson('<%=user.getUsername()%>', $('#lesson-title').val(), 0);" value="Create">
                <input type="button" onclick="toggleModal('create-lesson-modal');" value="Cancel">
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
                <input type="button" onclick="createNewClass('<%=user.getUsername()%>', $('#class-title').val(), $('#class-price').val(), $('#class-limit').val());" value="Create">
                <input type="button" onclick="toggleModal('create-class-modal');" value="Cancel">
            </div>

            <div id="save-confirm-modal" class="box-modal" style="text-align: center;">
                <h2>Stream Saved Successfully!</h2>
                <input type="button" onclick="toggleModal('save-confirm-modal');" value="Ok">
            </div>
            <%}%>

            <div id="modal"></div>
    </body>
</html>