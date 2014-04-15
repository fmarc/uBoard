<%-- 
    Document   : lesson
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
<%@page import="com.uboard.objects.Comment"%>
<%@page import="java.util.Map"%>
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
            <%
                Utilities util = Utilities.getInstance();
                User user           = null;
                
                Lesson object       = null;
                String title        = "";
                boolean isUser      = false;
                boolean isOwner     = false; 
                
                try {
                    user = util.getOnlineUser(session.getId());
                } catch (Exception e){}
                
                if((title = request.getParameter("lesson_id")) != null){
                    object = new Lesson(Integer.parseInt(title), 0);
                    if(object.lessonId == 0){
                        %>Lesson not found<%
                        return;
                    }
                } else {
                    response.sendRedirect("/");
                    return;
                }
                
                if(user != null) {
                    
                    isUser = true;
                    
                    if(!user.isEnrolled(object.classId)){
                        %>You are not enrolled in this Lesson's Class<%
                        return;
                    }
                        
                    
                    if(user.getUsername().equals(object.createdBy)){
                        isOwner = true;
                    }
                } else {
                    if(object.classId != 0) {
                        %>You are not enrolled in this Lesson's Class<%
                        return;
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
                    
        <%if(isOwner){%>
            <div id="save" onclick="saveLessonData(<%=object.lessonId%>);"><p>Save</p></div>
            
            <div id="sidebar" class="hidden">
                <div class="box drag title-box" title="Title"><img class="handle edit" /><img class="remove edit" /><h1 class="editable" contenteditable="false">Edit me!</h1></div>
                <div class="box drag text-box" title="Text Box"><img class="handle edit" /><img class="remove edit" /><div class="editable" contenteditable="false">Edit Me!</div></div>
                <div class="box drag image-box" title="Image Box"><img class="handle edit" /><img class="remove edit" /><div class="images"><img class="image"  src="/images/blanked.png" /><img class="image" src="/images/blanked.png" /><img class="image" src="/images/blanked.png" /></div><div class="buttons edit"><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/></div></div>
                <div class="box drag video-box" title="Video Box"><img class="handle edit" /><img class="remove edit" /><iframe class="video" width="853" height="480"  src="//www.youtube.com/embed/oJg2_dUHd84" frameborder="0" allowfullscreen></iframe><input type="button" class="change-video edit" onclick="openVideoModal(this);" value="Change Video"/></div>
                <div id="sidebar-click-area" onclick="toggleSideBar();"></div>
                <div id="sidebar-handle" onclick="toggleSideBar();"></div>
            </div>
            <div id="content">
                <div id="main-lesson-title" class="box" title="Lesson Title"><h1 contenteditable="false"><%=object.name%></h1><h2 id="username" style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=object.createdBy%>' "><%=object.createdBy%></h2></div>
                <div id="sort"><%if(object.html != null){%>
                        <%=object.html%>
                    <%}%>
                </div>
                <div id="comments" class="box">
                    <div class="box-header">
                        <h1>COMMENTS</h1>
                    </div>
                    <div class="box-content" id="comment-section">
                        <%if(isUser){%>
                        <div style="width: 100%; text-align: center;"><div id="post-comment" onclick="toggleModal('post-comment-modal');">Comment</div></div>
                        <%}%>
                        <%for(Comment comment : object.comments){%>
                        <div class="comment">
                            <div class="comment-user"><img style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=comment.username%>' " src="/images/comments/user-comment.png"></div>
                            <div class="comment-text"><p style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=comment.username%>' "><%=comment.username%></p><p class="text"><%=comment.text%></p></div>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>


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
            
            <div id="save-confirm-modal" class="box-modal">
                <h2>Lesson Saved Successfully!</h2>
                <input type="button" onclick="toggleModal('save-confirm-modal');" value="Ok">
            </div>
        <%} else {%>
        
        <%if(user != null) {%>
            <%if(!object.raters.containsKey(user.getUsername())){%>
                <div id="rating">
                    <div class="rate" id="rate-positive" onclick="rate('<%=user.getUsername()%>', <%=object.lessonId%>, 1);"><img src="/images/rating/pos-rate.png"></div>
                    <div class="lesson-rating"><p class="positive-rating"><%=object.posRating%></p></div>
                    <div class="rate" id="rate-negative" onclick="rate('<%=user.getUsername()%>', <%=object.lessonId%>, -1);"><img src="/images/rating/neg-rate.png"></div>
                </div>
            <%} else {%>
                <div id="rating">
                    <% if(object.raters.get(user.getUsername()) > 0) {%>
                    <div class="rate rated user-rated" id="rate-positive"><img src="/images/rating/pos-rate.png"></div>
                    <%if(object.posRating > 0) {%>
                    <div class="lesson-rating"><p class="positive-rating"><%=object.posRating%></p></div>
                    <%} else {%>
                    <div class="lesson-rating"><p class="negative-rating"><%=object.posRating%></p></div>
                    <%}%>
                    <div class="rate rated" id="rate-negative"><img src="/images/rating/neg-rate.png"></div>
                    <%} else {%>
                    <div class="rate rated" id="rate-positive"><img src="/images/rating/pos-rate.png"></div>
                    <%if(object.posRating > 0) {%>
                    <div class="lesson-rating"><p class="positive-rating"><%=object.posRating%></p></div>
                    <%} else {%>
                    <div class="lesson-rating"><p class="negative-rating"><%=object.posRating%></p></div>
                    <%}%>
                    <div class="rate rated user-rated" id="rate-negative"><img src="/images/rating/neg-rate.png"></div>
                    <%}%>
                </div>
            <%}%>
        <%} else {%>
            <div id="rating">
                <div class="lesson-rating" style="width: 60px;"><p class="positive-rating" style="border-radius: 10px;"><%=object.posRating%></p></div>
            </div>
        <%}%>
    
        <div id="content" style="left: 0;">
            <div id="main-lesson-title" class="box" title="Lesson Title"><h1 contenteditable="false"><%=object.name%></h1><h2 id="username" style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=object.createdBy%>' "><%=object.createdBy%></h2></div>
            <%if(object.html != null){%>
                <%=object.html%>
            <%}%>
            <div id="comments" class="box">
                <div class="box-header">
                    <h1>COMMENTS</h1>
                </div>
                <div class="box-content" id="comment-section">
                    <%if(isUser){%>
                    <div style="width: 100%; text-align: center;"><div id="post-comment" onclick="toggleModal('post-comment-modal');">Comment</div></div>
                    <%}%>
                    <%for(Comment comment : object.comments){%>
                    <div class="comment">
                        <div class="comment-user"><img style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=comment.username%>' " src="/images/comments/user-comment.png"></div>
                            <div class="comment-text"><p style="cursor: pointer;" class="user" onclick="window.location = '/profile.jsp?username=<%=comment.username%>' "><%=comment.username%></p><p class="text"><%=comment.text%></p></div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
        <%}%>
        
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

        <div id="post-comment-modal" class="box-modal" style="top: 25%;;">
            <h2>Post New Comment</h2>
            <p>Please fill out the following information to post a new comment.</p>
            <h4>Text</h4>
            <textarea id="text-comment" placeholder="Comment Text" style="min-width: 96%; max-width: 96%; min-height: 300px; max-height: 300px;"></textarea>
            <input type="button" onclick="postNewComment('<%=user.getUsername()%>', <%=object.classId%>, <%=object.lessonId%>);" value="Create">
            <input type="button" onclick="toggleModal('post-comment-modal');" value="Cancel">
        </div>
        <%}%>
        
        <div id="modal"></div>
    </body>
</html>