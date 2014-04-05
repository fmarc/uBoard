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
                String title = "";
                boolean lesson  = false; 
                
                Utilities util = Utilities.getInstance();
                User user = null;
                
                try {
                    user = util.getOnlineUser(session.getId());
                } catch (Exception e){}
                
                if((title = request.getParameter("lesson_id")) != null){
                    lesson  = true;
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
                    
    <%if(!lesson){%>
            <div id="save" onclick="saveLessonData();"><p>Save</p></div>
            
            <div id="sidebar" class="hidden">
                <div class="box drag title-box" title="Title"><img class="handle edit" /><img class="remove edit" /><h1 contenteditable="">Edit me!</h1></div>
                <div class="box drag text-box" title="Text Box"><img class="handle edit" /><img class="remove edit" /><div contenteditable="">Edit Me!</div></div>
                <div class="box drag image-box" title="Image Box"><img class="handle edit" /><img class="remove edit" /><div class="images"><img class="image"  src="/images/blanked.png" /><img class="image" src="/images/blanked.png" /><img class="image" src="/images/blanked.png" /></div><div class="buttons edit"><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/><input type="button" class="change-image" onclick="openImageModal(this);" value="Change Image"/></div></div>
                <div class="box drag video-box" title="Video Box"><img class="handle edit" /><img class="remove edit" /><iframe class="video" width="853" height="480"  src="//www.youtube.com/embed/oJg2_dUHd84" frameborder="0" allowfullscreen></iframe><input type="button" class="change-video edit" onclick="openVideoModal(this);" value="Change Video"/></div>
                <div id="sidebar-click-area" onclick="toggleSideBar();"></div>
                <div id="sidebar-handle" onclick="toggleSideBar();"></div>
            </div>
            <div id="content">
                <div id="main-lesson-title" class="box" title="Lesson Title"><h1 contenteditable="false">How To Bring The House Down!</h1><h2 id="username">mgonz108</h2></div>
                <div id="sort"></div>
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
        <div id="rating">
            <div class="rate" id="rate-positive" onclick="rate(1);"><img src="/images/rating/pos-rate.png"></div>
            <div class="lesson-rating"><p class="positive-rating">0</p></div>
            <div class="rate" id="rate-negative" onclick="rate(-1);"><img src="/images/rating/neg-rate.png"></div>
        </div>
    
        <div id="content" style="left: 0;">
            <div id="main-lesson-title" class="box" title="Lesson Title"><h1 contenteditable="false">How To Bring The House Down!</h1><h2 id="username">mgonz108</h2></div>
            <div class="box title-box" title="Title"><h1 contenteditable="false">Overview</h1></div>
            <div class="box text-box" title="Text Box"><div contenteditable="false">In this lesson we are going to be atalking about all the different things you must do in order to bring the house down! Please make sure to read through the entire lesson in order to truly be regarded as a force to be reckoned with whenever you decided to embark in the amazing feat that is the bringing of the house down!</div></div>
            <div class="box image-box" title="Image Box"><div class="images"><img class="image"  src="http://static.tumblr.com/zlyygir/SBgll7q5e/bthd.jpg" /><img class="image" src="http://seangilliganproductions.com/wp-content/uploads/2012/09/Bring-the-House-Down-80s-v2-01-540x405.jpg" /><img class="image" src="http://a2.mzstatic.com/us/r30/Music/91/c4/34/mzi.ggbthkpb.170x170-75.jpg" /></div></div>
            <div id="comments" class="box">
                <div class="box-header">
                    <h1>COMMENTS</h1>
                </div>
                <div class="box-content" id="comment-section">
                    <div style="width: 100%; text-align: center;"><div id="post-comment" onclick="toggleModal('post-comment-modal');">Comment</div></div>
                    <div class="comment">
                        <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                        <div class="comment-text"><p class="user">mgonz108</p><p class="text">This is an awesome lesson! Woohoo!</p></div>
                    </div>
                    <div class="comment">
                        <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                        <div class="comment-text"><p class="user">fmarc011</p><p class="text">I agree! This lesson is so cool!</p></div>
                    </div>
                    <div class="comment">
                        <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                        <div class="comment-text"><p class="user">CoryG</p><p class="text">Yo man this thing is pretty cool</p></div>
                    </div>
                    <div class="comment">
                        <div class="comment-user"><img src="/images/comments/user-comment.png"></div>
                        <div class="comment-text"><p class="user">LauraP</p><p class="text">I have become an expert in Bringing the House Down! Thank you!!</p></div>
                    </div>
                </div>
            </div>
        </div>
        
    <%}%>
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
        
        <div id="modal"></div>
    </body>
</html>