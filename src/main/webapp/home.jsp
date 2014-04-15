<%-- 
    Document   : home
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
<%@page import="java.util.Set"%>
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
                Utilities util = Utilities.getInstance();
                User user = null;
                
                try {
                    user = util.getOnlineUser(session.getId());
                } catch (Exception e){}
            %>

            <div id="top-banner">
                <div id="top">
                    <div id="search-box">
                        <form id="search-form">
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
                                    <a href="/register.jsp"><input id="register-button" class="button" type="button" value="REGISTER" /></a>
                                </form>
                                <div id="login-error"><img src="/images/login/login-error.png" style="position: relative; top: 2px; display: inline-block; width: 18px; margin: 0px;"><p style="display: inline-block;color:  whitesmoke;line-height: 20px;margin: 0px 0px 0px 15px;">Invalid login, please try again</p></div>
                            </div>
                        <%}%>
                    </div>
                </div>
                <div id="bottom"></div>
            </div>
            
            <div id="content">
                <%if(user == null && request.getParameter("search") == null) {%>
                    <div id="welcome-message" class="content-box">

                        <p>Pages done or work in progress:</p>
                        <a href='home.jsp'>Home</a>
                        <a href='register.jsp'>Register</a>
                        <a href='lesson.jsp'>Lesson</a>
                        <a href='profile.jsp'>Profile</a>
                        <a href='class.jsp'>Class</a>
                        <a href='assignment.jsp'>Assignment</a>
                        <a href='stream.jsp'>Stream</a>

                        <h1>U | BOARD - COMMUNITY LEARNING</h1>
                        <h2>A tool made for learning and teaching just about anything you can imagine.</h2>
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
               <%}%>
               
               <%if(request.getParameter("search") != null){%>
                    <div id="search-content">
                        <div class="box-header">
                            <h1>SEARCH: <span id="search-keyword" style="font-style: italic; font-weight: normal; font-family: Arial;"><%=request.getParameter("search")%></span></h1>
                        </div>
                        <div class="box-content">
                            <%for(Lesson lesson : util.searchContent(request.getParameter("search"))) {%>
                            <div class="lesson">
                                <div class="lesson-rating">
                                    <p class="positive-rating"><%=lesson.posRating%></p>
                                </div>
                                <a class="content-title" href="lesson.jsp?lesson_id=<%=lesson.lessonId%>">LESSON - <%=lesson.name%></a>
                            </div>
                            <%}%>
                        </div>
                    </div>
                <%}%>
               
                <div id="high-rated" class="box">
                    <div class="box-header">
                        <h1>HIGHEST RATED CONTENT</h1>
                    </div>
                    <div class="box-content">
                        
                        <%for(Lesson lesson: util.getTopRated()){%>
                        <div class="lesson">
                            <div class="lesson-rating">
                                <%if(lesson.posRating >= 0){%>
                                <p class="positive-rating"><%=lesson.posRating%></p>
                                <%} else {%>
                                <p class="negative-rating"><%=lesson.posRating%></p>
                                <%}%>
                            </div>
                            <a class="content-title" href="lesson.jsp?lesson_id=<%=lesson.lessonId%>">LESSON - <%=lesson.name%></a>
                        </div>
                        <%}%>
                    </div>
                </div>
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