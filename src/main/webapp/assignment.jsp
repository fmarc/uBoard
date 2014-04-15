<%-- 
    Document   : assignment.jsp
    Created on : Mar 2, 2014, 8:17:21 PM
    Author     : Maylem Gonzalez
                 Cory McAn
                 Francisco Marcano
--%>

<%@page import="com.uboard.objects.Utilities"%>
<%@page import="com.uboard.objects.Class"%>
<%@page import="com.uboard.objects.Lesson"%>
<%@page import="com.uboard.objects.Assignment"%>
<%@page import="com.uboard.objects.SubAssignment"%>
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
        <link rel="stylesheet" type="text/css" href="CSS/assignment.css">
        
        <script src="scripts/home.js"></script>
        <script src="scripts/assignment.js"></script>
    </head>
    <body>
            <%
                Utilities util = Utilities.getInstance();
                User user           = null;
                
                Assignment object   = null;
                SubAssignment submit = null;
                Class classObj      = null;
                String title        = "";
                String submitUser   = "";
                boolean isUser      = false;
                boolean isOwner     = false; 
                boolean isEnrolled  = false;
                boolean subUser     = false;
                
                try {
                    user = util.getOnlineUser(session.getId());
                } catch (Exception e){}
                
                if(user == null) {
                    response.sendRedirect("/");
                }
                
                if((title = request.getParameter("assignment_id")) != null){
                    
                    object = new Assignment(Integer.parseInt(title));
                    
                    if(object.classId == 0){
                        %>Assignment not found<%
                        return;
                    }
                    
                    classObj = new Class(object.classId);
                    
                } else {
                    response.sendRedirect("/");
                    return;
                }
                
                
                if(user != null) {
                    isEnrolled = user.isEnrolled(object.classId);
                    isUser = true;
                    
                    if(user.getUsername().equals(object.createdBy)){
                        isOwner = true;
                    }
                } else {
                    response.sendRedirect("/class.jsp?class_id=" + object.classId);
                }
                
                if((submitUser = request.getParameter("username")) != null) {
                    subUser = true;
                    submit = new SubAssignment(object.assignmentId, submitUser);
                } else {
                    String url = "/assignment.jsp?assignment_id=" + object.assignmentId + "&username=" + user.getUsername();
                    if(!isOwner) {
                        response.sendRedirect(url);
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
            
            <div id="content">
                <div class="class-title">
                    <h1><%=classObj.className%></h1>
                    <div id="class-banner">
                        <div id="teacher">
                            <p id="teacher-name"><%=classObj.createdBy%></p>
                        </div>
                    </div>
                </div>
                <%if(subUser && !isOwner) {%>
                <div id="assignment-content">
                    <h2 id="assignment-title"><%=object.assignName%></h2>
                    <h3 id="user"><%=submit.submitBy%></h3>
                    <p id="assignment-desc"><%=object.description%></p>
                    <%if(submit.submission.length() > 5) {%>
                        <textarea style="height: auto; min-height: 0px;" id="assignment" placeholder="Answer the assignment question above in here." disabled><%=submit.submission%></textarea>
                    <%} else {%>
                        <textarea style="height: auto; min-height: 0px;" id="assignment" placeholder="Answer the assignment question above in here." ></textarea>
                        <input type="button" value="Submit" onclick="submitAssignment(<%=object.assignmentId%> ,'<%=user.getUsername()%>', $('#assignment').val());">
                    <%}%>
                    <div id="assignment-feedback">
                        <h2 id="feedback-title">Feedback: </h2>
                        <textarea style="height: auto; min-height: 0px;" id="feedback" placeholder="Feed back will display here once given" disabled><%=submit.feedback.feedBack%></textarea>
                    </div>
                </div>
                <%} else if (subUser && isOwner){%>
                <div id="assignment-content">
                    <h2 id="assignment-title"><%=object.assignName%></h2>
                    <h3 id="user"><%=submit.submitBy%></h3>
                    <p id="assignment-desc"><%=object.description%></p>
                    <textarea style="height: auto; min-height: 0px;" id="assignment" placeholder="Answer the assignment question above in here." disabled><%=submit.submission%></textarea>
                    <div id="assignment-feedback">
                        <h2 id="feedback-title">Feedback: </h2>
                        <textarea id="feedback" placeholder="Feed back will display here once given" ><%=submit.feedback.feedBack%></textarea>
                    </div>
                    <%if(submit.feedback.feedBack.length() > 5) {%>
                    <input type="button" value="Submit" onclick="submitFeedback(<%=object.assignmentId%>, '<%=submit.submitBy%>', $('#feedback').val());">
                    <%}%>
                </div>
                <%} else {%>
                <div id="assignment-content">
                    <div class="assignment">
                        <h3 class="assignment-title"><%=object.assignName%></h3>
                        <%for(SubAssignment assign : object.submitted) {%>
                        <div class="user-assignments">
                            <div class="user-assignment" >
                                <h4 class="user"><a href="assignment.jsp?assignment_id=<%=object.assignmentId%>&username=<%=assign.submitBy%>"><%=assign.submitBy%></a></h4>
                                <%if(assign.feedback.feedBack.isEmpty()){%>
                                <p class="status"> - <span>Feedback Pending</span></p>
                                <%} else {%>
                                <p class="status"> - <span>Completed</span></p>
                                <%}%>
                                <p class="assignment-text"><%=assign.submission%></p>
                            </div>
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