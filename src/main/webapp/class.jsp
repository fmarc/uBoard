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
                <div id="class-side-tools">
                    
                    <div class="tool">
                        <div id="enrolled">
                            <h2>Enrolled</h2>
                            <p id="students-enrolled">20 / 40</p>
                        </div>
                    </div>
                    
                    <div class="tool">
                        <div id="price">
                            <h2>Price</h2>
                            <p>$<span class="class-price">32</span></p>
                        </div>
                    </div>

                    <%if(user) {%>
                    <div class="tool">
                        <div id="class-enroll" onclick="openEnrollModal();">
                            <p id="enroll">Enroll</p>
                        </div>
                    </div>
                    <%}%>
                    
                    <div class="tool">
                        <%if(!user) {%>
                        <div id="class-stream" onclick="window.location = 'stream.jsp';">
                        <%} else {%>
                        <div id="class-stream" onclick="window.location = 'stream.jsp?id=mgonz108';">
                        <%}%>
                            <p id="stream">Stream Page</p>
                        </div>
                    </div>
                    
                    <div class="tool">
                        <div id="assignments">
                            <h2>Assignments</h2>
                            <div class="assignment">
                                <%if(!user) {%>
                                <p class="assignment-title" onclick="window.location = 'assignment.jsp'">1. Bringing Down The House !</p>
                                <%} else {%>
                                <p class="assignment-title" onclick="window.location = 'assignment.jsp?teacher=mgonz108'">1. Bringing Down The House !</p>
                                <%}%>
                                <%if(!user) {%>
                                <p class="assignment-title" onclick="window.location = 'assignment.jsp'">2. Piano History and its Roots</p>
                                <%} else {%>
                                <p class="assignment-title" onclick="window.location = 'assignment.jsp?teacher=mgonz108'">2. Piano History and its Roots</p>
                                <%}%>
                                <%if(user) {%>
                                <div id="create-new-assignment" onclick="toggleModal('create-assignment-modal');">Create Assignment</div>
                                <%}%>

                            </div>
                        </div>
                    </div>
                </div>
                    
                <div class="class-content">
                    <div id="class-top">
                        <div class="rating" title="Total rating from lessons">
                            <p class="positive-rating">471</p>
                        </div>
                        <div class="class-title">
                            <h1>Everything You Need To Know About Pianos</h1>
                        </div>
                    </div>
                    
                    <div id="class-banner">
                        <div id="teacher">
                            <p id="teacher-name">mgonz108</p>
                        </div>
                    </div>
                    

                    <div id="class-bottom">
                        <div id="description">
                            <h2>Class Description: </h2>
                            <textarea disabled id="class-desc">This class is aimed at those individuals who wish to learn all there is to do about pianos in a simple and easy way.</textarea>
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
                            <a class="content-title" href="lesson.jsp?lesson_id=123456789"">A Brief History On How Pianos Came About</a>
                        </div>
                        <div class="created-lesson">
                            <div class="lesson-rating">
                                <p class="negative-rating">-30</p>
                            </div>
                            <a class="content-title" href="lesson.jsp?lesson_id=123456789"">Material Pianos are Made Out Of</a>
                        </div>
                        <%if(user) {%>
                            <div class="create-new-lesson" onclick="toggleModal('create-lesson-class-modal');">
                                <p>Create New Lesson</p>
                            </div>
                        <%}%>
                    </div>
                </div>
                
                <div id="comments" class="box">
                    <div class="box-header">
                        <h1>COMMENTS</h1>
                    </div>
                    <div class="box-content" id="comment-section">
                        <%if(user){%>
                        <div style="width: 100%; text-align: center;"><div id="post-comment" onclick="toggleModal('post-comment-modal');">Comment</div></div>
                        <%}%>
                        <div class="comment">
                            <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                            <div class="comment-text"><p class="user">mgonz108</p><p class="text">This is an awesome class! Woohoo!</p></div>
                        </div>
                        <div class="comment">
                            <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                            <div class="comment-text"><p class="user">fmarc011</p><p class="text">I agree! This class is so cool!</p></div>
                        </div>
                        <div class="comment">
                            <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                            <div class="comment-text"><p class="user">CoryG</p><p class="text">Yo man this thing is pretty cool</p></div>
                        </div>
                        <div class="comment">
                            <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                            <div class="comment-text"><p class="user">LauraP</p><p class="text">I have become an expert in Pianos! Thank you!!</p></div>
                        </div>
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
                <h4>Price ($):</h4>
                <input type="text" id="class-price" placeholder="Class Price">
                <h4>Class Enrollment Limit:</h4>
                <input type="text" id="class-limit" placeholder="Class Limit">
                <input type="button" onclick="createNewLesson();" value="Create">
                <input type="button" onclick="toggleModal('create-class-modal');" value="Cancel">
            </div>
               
            <div id="post-comment-modal" class="box-modal" style="top: 25%;">
                <h2>Post New Comment</h2>
                <p>Please fill out the following information to post a new comment.</p>
                <h4>Text</h4>
                <textarea id="text-comment" placeholder="Comment Text" style="min-width: 96%; max-width: 96%; min-height: 500px; max-height: 500px;"></textarea>
                <input type="button" onclick="postNewComment('mgonz108');" value="Create">
                <input type="button" onclick="toggleModal('post-comment-modal');" value="Cancel">
            </div>
                        
            <div id="create-assignment-modal" class="box-modal" style="top: 25%;">
                <h2>Create New Assignment</h2>
                <p>Please fill out the following information to create a new assignment.</p>
                <h4>Title</h4>
                <input type="text" id="assignment-title" placeholder="Assignment Title">
                <h4>Description:</h4>
                <textarea style="box-sizing: border-box; padding: 10px; max-width: 100%; min-width: 100%; min-height: 400px; max-height: 400px" id="assignment-description" placeholder="Assignment Description"></textarea>
                <input type="button" onclick="createNewAssignment();" value="Create">
                <input type="button" onclick="toggleModal('create-assignment-modal');" value="Cancel">
            </div>
            
            <div id="modal"></div>
    </body>
</html>