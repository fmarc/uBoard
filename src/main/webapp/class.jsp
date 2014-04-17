<%-- 
    Document   : class
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>

<%@page import="com.uboard.objects.Utilities"%>
<%@page import="com.uboard.objects.Class"%>
<%@page import="com.uboard.objects.Lesson"%>
<%@page import="com.uboard.objects.Assignment"%>
<%@page import="com.uboard.objects.Comment"%>
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
            <%
                Utilities util = Utilities.getInstance();
                User user           = null;
                
                Class object       = null;
                String title        = "";
                boolean isUser      = false;
                boolean isOwner     = false; 
                boolean isEnrolled  = false;
                
                try {
                    user = util.getOnlineUser(session.getId());
                } catch (Exception e){}
                
                if((title = request.getParameter("class_id")) != null){
                    object = new Class(Integer.parseInt(title));
                    if(object.classId == 0){
                        %>Class not found<%
                        return;
                    }
                } else {
                    response.sendRedirect("/");
                    return;
                }
                
                if(user != null) {
                    isEnrolled = object.isEnrolled(user.getUsername());
                    isUser = true;
                    
                    if(user.getUsername().equals(object.createdBy)){
                        isOwner = true;
                    }
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
                <div id="edit-save" class="edit-class" onclick="saveClassData(<%=object.classId%>);"><p>Edit</p></div>
            <%}%>
            
            <div id="content">
                <div id="class-side-tools">
                    
                    <div class="tool">
                        <div id="enrolled">
                            <h2>Enrolled</h2>
                            <p id="students-enrolled"><%=object.numEnrolled%> / <%=object.enrollLimit%></p>
                        </div>
                    </div>
                    
                    <div class="tool">
                        <div id="price">
                            <h2>Price</h2>
                            <p>$<span class="class-price"><%=object.price%></span></p>
                        </div>
                    </div>

                    <%if(user != null && !isEnrolled && !isOwner) {%>
                    <div class="tool">
                        <div id="class-enroll" onclick="openEnrollModal();">
                            <p id="enroll">Enroll</p>
                        </div>
                    </div>
                    <%}%>
                    
                    <%if(isEnrolled || isOwner){%>
                    <div class="tool">
                        <div id="class-stream" onclick="window.location = 'stream.jsp?class_id=<%=object.classId%>';">
                            <p id="stream">Stream Page</p>
                        </div>
                    </div>
                    <%}%>
                    
                    <%if(isEnrolled || isOwner){%>
                    <div class="tool">
                        <div id="assignments">
                            <h2>Assignments</h2>
                            <div class="assignment">
                                <%int num = 0;
                                for(Assignment asign : object.assignments){%>
                                    <p class="assignment-title" onclick="window.location = 'assignment.jsp?assignment_id=<%=asign.assignmentId%>'"><%=++num%>. <%=asign.assignName%></p>
                                <%}%>
                                <%if(isOwner) {%>
                                <div id="create-new-assignment" onclick="toggleModal('create-assignment-modal');">Create Assignment</div>
                                <%}%>

                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
                    
                <div class="class-content">
                    <div id="class-top">
                        <div class="rating" title="Total rating from lessons">
                            <p class="positive-rating"><%=object.posRating%></p>
                        </div>
                        <div class="class-title">
                            <h1><%=object.className%></h1>
                        </div>
                    </div>
                    
                    <div id="class-banner">
                        <div id="teacher">
                            <p id="teacher-name" style="cursor: pointer;" onclick="window.location = '/profile.jsp?username=<%=object.createdBy%>'"><%=object.createdBy%></p>
                        </div>
                    </div>
                    

                    <div id="class-bottom">
                        <div id="description">
                            <h2>Class Description: </h2>
                            <textarea disabled id="class-desc" maxlength="500"><%=object.description%></textarea>
                        </div>
                    </div>
                </div>
                
                <div class="class-content" id="lessons-created" class="box">
                    <div class="box-header">
                        <h1>CLASS LESSONS</h1>
                    </div>
                    <div class="box-content">
                        <%for(Lesson lesson : object.lessons){%>
                        <div class="created-lesson">
                            <div class="lesson-rating">
                                <%if(lesson.posRating >= 0) {%>
                                    <p class="positive-rating"><%=lesson.posRating%></p>
                                    <%} else {%>
                                    <p class="negative-rating"><%=lesson.posRating%></p>
                                    <%}%>
                            </div>
                            <a class="content-title" href="lesson.jsp?lesson_id=<%=lesson.lessonId%>"><%=lesson.name%></a>
                        </div>
                        <%}%>
                        <%if(isOwner) {%>
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
                        <%if(isEnrolled){%>
                        <div style="width: 100%; text-align: center;"><div id="post-comment" onclick="toggleModal('post-comment-modal');">Comment</div></div>
                        <%}%>
                        <%for(Comment comment : object.comments) {%>
                        <div class="comment">
                            <div class="comment-user"><img style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=comment.username%>' " src="/images/comments/user-comment.png"></div>
                            <div class="comment-text"><p style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=comment.username%>' "><%=comment.username%></p><p class="text"><%=comment.text%></p></div>
                        </div>
                        <%}%>
                    </div>
                </div>
                
            </div>
            
            <%if(user != null){%>
            <div id="enroll-modal" class="box-modal">
                <h2>Enroll</h2>
                <h2 style="margin:5px; text-align: center; display: inline-block; width: 15%;">$<span class="class-price"><%=object.price%></span></h2>
                <p style="display: inline-block; width: 75%;">The amount stated will be charged to your PayPal account. Please confirm the transaction.</p>
                <br>
                <input type="button" onclick="enroll('<%=object.classId%>', '<%=user.getUsername()%>', '<%=user.getPaypalEmail()%>');" value="Confirm">
                <input type="button" onclick="toggleModal('enroll-modal');" value="Cancel">
            </div>

            <div id="create-lesson-class-modal" class="box-modal">
                <h2>Create New Class Lesson</h2>
                <p>Please fill out the following information to create a new lesson in this class.</p>
                <h4>Title</h4>
                <input type="text" id="lesson-class-title" placeholder="Lesson Title">
                <input type="button" onclick="createNewLesson('<%=user.getUsername()%>', $('#lesson-class-title').val(), '<%=object.classId%>');" value="Create">
                <input type="button" onclick="toggleModal('create-lesson-class-modal');" value="Cancel">
            </div>

            <div id="error-modal" class="box-modal">
                <h2>Error</h2>
                <p>It seems you don't have a PayPal account set up yet. If you would like to set it up now, please click "OK".</p>
                <br>
                <input type="button" onclick="redirectProfile('<%=user.getUsername()%>');" value="Ok">
                <input type="button" onclick="toggleModal('error-modal');" value="Cancel">
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
               
            <div id="post-comment-modal" class="box-modal" style="top: 25%;">
                <h2>Post New Comment</h2>
                <p>Please fill out the following information to post a new comment.</p>
                <h4>Text</h4>
                <textarea maxlength="2500" id="text-comment" placeholder="Comment Text" style="min-width: 96%; max-width: 96%; min-height: 500px; max-height: 500px;"></textarea>
                <input type="button" onclick="postNewComment('<%=user.getUsername()%>', <%=object.classId%>, 0);" value="Create">
                <input type="button" onclick="toggleModal('post-comment-modal');" value="Cancel">
            </div>
                        
            <div id="create-assignment-modal" class="box-modal" style="top: 25%;">
                <h2>Create New Assignment</h2>
                <p>Please fill out the following information to create a new assignment.</p>
                <h4>Title</h4>
                <input type="text" id="assignment-title" placeholder="Assignment Title">
                <h4>Description:</h4>
                <textarea maxlength="500" style="box-sizing: border-box; padding: 10px; max-width: 100%; min-width: 100%; min-height: 400px; max-height: 400px" id="assignment-description" placeholder="Assignment Description"></textarea>
                <input type="button" onclick="createNewAssignment(<%=object.classId%>, '<%=user.getUsername()%>', $('#assignment-title').val(), $('#assignment-description').val());" value="Create">
                <input type="button" onclick="toggleModal('create-assignment-modal');" value="Cancel">
            </div>
            <%}%>
            
            <div id="modal"></div>
    </body>
</html>