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
                        <form>
                            <input type="text" id="content-search" val="" placeholder="Search">
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
                                <img id="user-hover" src="/images/login/teacher-auth-small.png" /><span style="position:relative; top:15px;"><%=user.getUsername()%></span>
                                <%} else {%>
                                <img id="user-hover" src="/images/login/user-img.png" /><span style="position:relative; top:15px;"><%=user.getUsername()%></span>
                                <%}%>
                            </div>
                            <div id="logged-in">
                                <div onclick="window.location = '/profile.jsp'"><img src="/images/login/view-profile.png"><p>View Profile</p></div>
                                <div onclick="toggleModal('create-lesson-modal');"><img src="/images/login/create-lesson.png"><p>Create Lesson</p></div>
                                <%if(user instanceof Teacher){%>
                                    <div onclick="toggleModal('create-class-modal');"><img src="/images/login/create-class.png"><p>Create Class</p></div>
                                <%}%>
                                <div onclick="logout()"><img src="/images/login/logout.png"><p>Log Out</p></div>
                            </div>
                        <%} else {%>
                            <div id="login">
                                <span style="position:relative; top:15px;">LOG IN</span>
                            </div>
                            <div id="login-modal">
                                <form id="login-form" onsubmit="login();">
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
            
            <%if(user != null) {%>
                <div id="edit-save" class="edit-profile" onclick="saveUserData(this);"><p>Edit</p></div>
            <%}%>
            
            <div id="content">
                <div id="user" class="box">
                    <div id="user-top">
                        <img width="80" id="student-image" src="/images/profile/user.png" />
                        <div id="user-info-top">
                            <h2 id="username">mgonz108</h2>
                            <div id="rating-box"><p class="positive-rating">15</p></div>
                        </div>
                    </div>
                    <div id="user-bottom">
                        <h3>Name:</h3>
                        <p id="name" class="edit">Maylem Gonzalez</p>
                        <%if(user != null) {%>
                            <h3>Email:</h3>
                            <p>mgonz108@gmail.com</p>
                        <%}%>
                        <h3>About:</h3>
                        <textarea id="about" class="edit-area" maxlength="500" placeholder="Tell others about yourself!" disabled>I love things!</textarea>
                    </div>
                </div>
            
                <%if(user != null) {%>
                    <div id="paypal" class="box">
                        <div class="box-header">
                            <h1>PAYPAL</h1>
                        </div>
                        <div class="box-content">
                            <h3>Paypal Registered Email:</h3>
                            <p id="paypal-email" class="edit" placeholder="Paypal account email">mgonz108@gmail.com</p>
                        </div>

                    </div>
                <%}%>
                
                <div id="lessons-created" class="box">
                    <div class="box-header">
                        <h1>LESSONS CREATED</h1>
                    </div>
                    <div class="box-content">
                        <div class="created-lesson">
                            <div class="lesson-rating">
                                <p class="positive-rating">501</p>
                            </div>
                            <a class="content-title" href="lesson.jsp?lesson_id=123456789"">LESSON - How To Bring The House Down!</a>
                        </div>
                        <div class="created-lesson">
                            <div class="lesson-rating">
                                <p class="negative-rating">-30</p>
                            </div>
                            <a class="content-title" href="lesson.jsp?lesson_id=123456789"">LESSON - How to curse at people</a>
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
                            <a class="content-title" href="class.jsp?lesson_id=123456789"">CLASS - Complete series on how to be a better person</a>
                        </div>
                        <div class="created-class">
                            <div class="class-rating">
                                <p class="negative-rating">-30</p>
                            </div>
                            <a class="content-title" href="class.jsp?lesson_id=123456789"">CLASS - How to be a good person</a>
                        </div>
                    </div>
                </div>
                
                <%if(user != null) {%>
                    <div id="classes-taken" class="box">
                        <div class="box-header">
                            <h1>CLASSES TAKEN</h1>
                        </div>
                        <div class="box-content">
                            <div class="class-taken">
                                <div class="class-rating">
                                    <p class="positive-rating">15</p>
                                </div>
                                <a class="content-title" href="lesson.jsp?lesson_id=123456789"">CLASS - How to cross-stitch</a>
                            </div>
                            <div class="class-taken">
                                <div class="class-rating">
                                    <p class="negative-rating">-30</p>
                                </div>
                                <a class="content-title" href="lesson.jsp?lesson_id=123456789"">CLASS - How to break into someone's house.</a>
                            </div>
                        </div>
                    </div>
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

            <div id="modal"></div>
    </body>
</html>