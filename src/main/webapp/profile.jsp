<%-- 
    Document   : profile
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>

<%@page import="com.uboard.objects.Utilities"%>
<%@page import="com.uboard.interfaces.User"%>
<%@page import="com.uboard.objects.Student"%>
<%@page import="com.uboard.objects.Teacher"%>
<%@page import="com.uboard.objects.Lesson"%>
<%@page import="com.uboard.objects.Class"%>
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
        <%
            Utilities util = Utilities.getInstance();
            User user = null;
            User profile = null;
            
            boolean isOwner = false;

            String username = "";
            
            try {
                user = util.getOnlineUser(session.getId());
            } catch (Exception e){}
            
            
            if((username = request.getParameter("username")) != null){
                profile = util.getUser(username);
                if(profile == null) {
                    response.sendRedirect("/profile.jsp?username=" + user.getUsername());
                }
                
            } else {
                response.sendRedirect("/profile.jsp?username=" + user.getUsername());
            }
            
            if(user != null && profile.getUsername().equalsIgnoreCase(user.getUsername())){
                isOwner = true;
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

        <%if(isOwner) {%>
            <div id="edit-save" class="edit-profile" onclick="saveUserData('<%=profile.getUsername()%>');"><p>Edit</p></div>
        <%}%>

        <div id="content">
            <div id="user" class="box">
                <div id="user-top">
                    <img width="80" id="student-image" src="/images/profile/user.png" />
                    <div id="user-info-top">
                        <h2 id="username"><%=profile.getUsername()%></h2>
                        <%if(profile.getPosRating() >= 0) {%>
                        <div id="rating-box"><p class="positive-rating"><%=profile.getPosRating()%></p></div>
                        <%} else {%>
                        <div id="rating-box"><p class="negative-rating"><%=profile.getPosRating()%></p></div>
                        <%}%>
                    </div>
                </div>
                <div id="user-bottom">
                    <h3>Name:</h3>
                    <p id="name" class="edit"><%=profile.getName()%></p>
                    <%if(user != null) {%>
                        <h3>Email:</h3>
                        <p><%=profile.getEmail()%></p>
                    <%}%>
                    <h3>About:</h3>
                    <textarea maxlength="500" id="about" class="edit-area" maxlength="500" placeholder="Tell others about yourself!" disabled><%=profile.getAboutSection()%></textarea>
                </div>
            </div>

            <%if(isOwner) {%>
                <div id="paypal" class="box">
                    <div class="box-header">
                        <h1>PAYPAL</h1>
                    </div>
                    <div class="box-content">
                        <h3>Paypal Registered Email:</h3>
                        <p id="paypal-email" class="edit" placeholder="Paypal account email"><%=profile.getPaypalEmail()%></p>
                    </div>

                </div>
            <%}%>

            <div id="lessons-created" class="box">
                <div class="box-header">
                    <h1>LESSONS CREATED</h1>
                </div>
                <div class="box-content">
                    <%for(Lesson lesson : profile.getCreateLesson()) {%>
                    <div class="created-lesson">
                        <div class="lesson-rating">
                            <%if(lesson.posRating >= 0){%>
                            <p class="positive-rating"><%=lesson.posRating%></p>
                            <%}else {%>
                            <p class="negative-rating"><%=lesson.posRating%></p>
                            <%}%>
                        </div>
                        <a class="content-title" href="lesson.jsp?lesson_id=<%=lesson.lessonId%>"">LESSON - <%=lesson.name%></a>
                    </div>
                    <%}%>
                </div>
            </div>
                
            <%if(profile instanceof Teacher) {%>    
            <div id="classes-created" class="box">
                <div class="box-header">
                    <h1>CLASSES CREATED</h1>
                </div>
                <div class="box-content">
                    <%for(Class cls : profile.getCreateClasses()) {%>
                    <div class="created-class">
                        <div class="class-rating">
                            <%if(cls.posRating >= 0){%>
                            <p class="positive-rating"><%=cls.posRating%></p>
                            <%}else {%>
                            <p class="negative-rating"><%=cls.posRating%></p>
                            <%}%>
                        </div>
                        <a class="content-title" href="class.jsp?class_id=<%=cls.classId%>"">CLASS - <%=cls.className%></a>
                    </div>
                    <%}%>
                </div>
            </div>
            <%}%>

            <%if(isOwner) {%>
                <div id="classes-taken" class="box">
                    <div class="box-header">
                        <h1>CLASSES TAKEN</h1>
                    </div>
                    <div class="box-content">
                         <%for(Class cls : profile.getEnrollClasses()) {%>
                        <div class="created-class">
                            <div class="class-rating">
                                <%if(cls.posRating >= 0){%>
                                <p class="positive-rating"><%=cls.posRating%></p>
                                <%}else {%>
                                <p class="negative-rating"><%=cls.posRating%></p>
                                <%}%>
                            </div>
                            <a class="content-title" href="class.jsp?class_id=<%=cls.classId%>"">CLASS - <%=cls.className%></a>
                        </div>
                        <%}%>
                    </div>
                </div>
            <%}%>
        </div>

        <%if(user != null){%>
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
        <%}%>

        <div id="modal"></div>
    </body>
</html>