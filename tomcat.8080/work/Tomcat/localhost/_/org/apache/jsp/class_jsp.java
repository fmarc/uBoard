/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.34
 * Generated at: 2014-04-01 05:54:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.BufferedReader;

public final class class_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Home - uBoard</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width\">\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"/favicon.png\">\r\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>\r\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>\r\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/jquery-ui-1.10.4.min.css\">\r\n");
      out.write("        <script src=\"scripts/jquery-1.10.2.js\"></script>\r\n");
      out.write("        <script src=\"scripts/jquery-ui-1.10.4.min.js\"></script>\r\n");
      out.write("        \r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/master.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/class.css\">\r\n");
      out.write("        \r\n");
      out.write("        <script src=\"scripts/home.js\"></script>\r\n");
      out.write("        <script src=\"scripts/class.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        \r\n");
      out.write("            <!--\r\n");
      out.write("                JSP IMPLEMENTATION PSEUDOCODE\r\n");
      out.write("                \r\n");
      out.write("                Is the user logged in? {\r\n");
      out.write("                    -YES {\r\n");
      out.write("                        Redirect the user to search location (with no parameters, showing recent lessons/classes and highest rated lessons/classes)\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("            -->\r\n");
      out.write("            \r\n");
      out.write("            ");

                String id = "";
                String teach = "";
                boolean user = false;
                boolean teacher = false;
                
                if (request.getParameter("id") != null){
                    id = request.getParameter("id");
                    user = true;
                }
                if (request.getParameter("teacher") != null){
                    teach = request.getParameter("teacher");
                    teacher = true;
                }
            
      out.write("\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"top-banner\">\r\n");
      out.write("                <div id=\"top\">\r\n");
      out.write("                    <div id=\"search-box\">\r\n");
      out.write("                        <form>\r\n");
      out.write("                            <input type=\"text\" id=\"content-search\" val=\"\" placeholder=\"Search\">\r\n");
      out.write("                            <img id=\"mag-glass\" />\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <a class=\"logo\" title=\"Take me to the Home Page!\" href=\"/\">\r\n");
      out.write("                        <img src=\"/images/logos/uboard_logo_side.png\" />\r\n");
      out.write("                    </a>\r\n");
      out.write("                    \r\n");
      out.write("                    <div id=\"user-auth\">\r\n");
      out.write("                        ");
if(user) {
      out.write("\r\n");
      out.write("                            <div id=\"login\">\r\n");
      out.write("                                <img id=\"user-hover\" src=\"/images/login/user-img.png\"><span style=\"position:relative; top:15px;\">");
      out.print(id);
      out.write("</span>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div id=\"logged-in\">\r\n");
      out.write("                                <div onclick=\"window.location = '/profile.jsp'\"><img src=\"/images/login/view-profile.png\"><p>View Profile</p></div>\r\n");
      out.write("                                <div onclick=\"toggleModal('create-lesson-modal');\"><img src=\"/images/login/create-lesson.png\"><p>Create Lesson</p></div>\r\n");
      out.write("                                <div onclick=\"window.location = '/home.jsp'\"><img src=\"/images/login/logout.png\"><p>Log Out</p></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        ");
} else if(teacher) {
      out.write("\r\n");
      out.write("                            <div id=\"login\">\r\n");
      out.write("                                <img id=\"user-hover\" src=\"/images/login/teacher-auth-small.png\"><span style=\"position:relative; top:15px;\">");
      out.print(teach);
      out.write("</span>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div id=\"logged-in\">\r\n");
      out.write("                                <div onclick=\"window.location = '/profile.jsp'\"><img src=\"/images/login/view-profile.png\"><p>View Profile</p></div>\r\n");
      out.write("                                <div onclick=\"toggleModal('create-lesson-modal');\"><img src=\"/images/login/create-lesson.png\"><p>Create Lesson</p></div>\r\n");
      out.write("                                <div onclick=\"toggleModal('create-class-modal');\"><img src=\"/images/login/create-class.png\"><p>Create Class</p></div>\r\n");
      out.write("                                <div onclick=\"window.location = '/home.jsp'\"><img src=\"/images/login/logout.png\"><p>Log Out</p></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        ");
} else {
      out.write("\r\n");
      out.write("                            <div id=\"login\">\r\n");
      out.write("                                <span style=\"position:relative; top:15px;\">LOG IN</span>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div id=\"login-modal\">\r\n");
      out.write("                                <form id=\"login-form\">\r\n");
      out.write("                                    <input name=\"id\" type=\"text\" id=\"login-user\" class=\"text-input\" placeholder=\"Username\">\r\n");
      out.write("                                    <span id=\"login-user-pic\"></span>\r\n");
      out.write("                                    <input type=\"password\" id=\"login-pass\" class=\"text-input\" placeholder=\"Password\">\r\n");
      out.write("                                    <span id=\"login-pass-pic\"></span>\r\n");
      out.write("                                    <input id=\"login-button\" class=\"button\" type=\"submit\" value=\"LOG IN\">\r\n");
      out.write("                                    <a href=\"/register.jsp\"><input href=\"/register.jsp\" id=\"register-button\" class=\"button\" type=\"button\" value=\"REGISTER\" /></a>\r\n");
      out.write("                                </form>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        ");
}
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div id=\"bottom\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            ");
if(teacher) {
      out.write("\r\n");
      out.write("                <div id=\"edit-save\" class=\"edit-class\" onclick=\"saveClassData(this);\"><p>Edit</p></div>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"content\">\r\n");
      out.write("                <div id=\"class-side-tools\">\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"tool\">\r\n");
      out.write("                        <div id=\"enrolled\">\r\n");
      out.write("                            <h2>Enrolled</h2>\r\n");
      out.write("                            <p id=\"students-enrolled\">20 / 40</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"tool\">\r\n");
      out.write("                        <div id=\"price\">\r\n");
      out.write("                            <h2>Price</h2>\r\n");
      out.write("                            <p>$<span class=\"class-price\">32</span></p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    ");
if(user) {
      out.write("\r\n");
      out.write("                    <div class=\"tool\">\r\n");
      out.write("                        <div id=\"class-enroll\" onclick=\"openEnrollModal();\">\r\n");
      out.write("                            <p id=\"enroll\">Enroll</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");
}
      out.write("\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"tool\">\r\n");
      out.write("                        ");
if(!user) {
      out.write("\r\n");
      out.write("                        <div id=\"class-stream\" onclick=\"window.location = 'stream.jsp';\">\r\n");
      out.write("                        ");
} else {
      out.write("\r\n");
      out.write("                        <div id=\"class-stream\" onclick=\"window.location = 'stream.jsp?id=mgonz108';\">\r\n");
      out.write("                        ");
}
      out.write("\r\n");
      out.write("                            <p id=\"stream\">Stream Page</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"tool\">\r\n");
      out.write("                        <div id=\"assignments\">\r\n");
      out.write("                            <h2>Assignments</h2>\r\n");
      out.write("                            <div class=\"assignment\">\r\n");
      out.write("                                ");
if(!user) {
      out.write("\r\n");
      out.write("                                <p class=\"assignment-title\" onclick=\"window.location = 'assignment.jsp'\">1. Bringing Down The House !</p>\r\n");
      out.write("                                ");
} else {
      out.write("\r\n");
      out.write("                                <p class=\"assignment-title\" onclick=\"window.location = 'assignment.jsp?teacher=mgonz108'\">1. Bringing Down The House !</p>\r\n");
      out.write("                                ");
}
      out.write("\r\n");
      out.write("                                ");
if(!user) {
      out.write("\r\n");
      out.write("                                <p class=\"assignment-title\" onclick=\"window.location = 'assignment.jsp'\">2. Piano History and its Roots</p>\r\n");
      out.write("                                ");
} else {
      out.write("\r\n");
      out.write("                                <p class=\"assignment-title\" onclick=\"window.location = 'assignment.jsp?teacher=mgonz108'\">2. Piano History and its Roots</p>\r\n");
      out.write("                                ");
}
      out.write("\r\n");
      out.write("                                ");
if(teacher) {
      out.write("\r\n");
      out.write("                                <div id=\"create-new-assignment\" onclick=\"toggleModal('create-assignment-modal');\">Create Assignment</div>\r\n");
      out.write("                                ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                    \r\n");
      out.write("                <div class=\"class-content\">\r\n");
      out.write("                    <div id=\"class-top\">\r\n");
      out.write("                        <div class=\"rating\" title=\"Total rating from lessons\">\r\n");
      out.write("                            <p class=\"positive-rating\">471</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"class-title\">\r\n");
      out.write("                            <h1>Everything You Need To Know About Pianos</h1>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <div id=\"class-banner\">\r\n");
      out.write("                        <div id=\"teacher\">\r\n");
      out.write("                            <p id=\"teacher-name\">mgonz108</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("\r\n");
      out.write("                    <div id=\"class-bottom\">\r\n");
      out.write("                        <div id=\"description\">\r\n");
      out.write("                            <h2>Class Description: </h2>\r\n");
      out.write("                            <textarea disabled id=\"class-desc\">This class is aimed at those individuals who wish to learn all there is to do about pianos in a simple and easy way.</textarea>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                \r\n");
      out.write("                <div class=\"class-content\" id=\"lessons-created\" class=\"box\">\r\n");
      out.write("                    <div class=\"box-header\">\r\n");
      out.write("                        <h1>CLASS LESSONS</h1>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"box-content\">\r\n");
      out.write("                        <div class=\"created-lesson\">\r\n");
      out.write("                            <div class=\"lesson-rating\">\r\n");
      out.write("                                <p class=\"positive-rating\">501</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <a class=\"content-title\" href=\"lesson.jsp?lesson_id=123456789\"\">A Brief History On How Pianos Came About</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"created-lesson\">\r\n");
      out.write("                            <div class=\"lesson-rating\">\r\n");
      out.write("                                <p class=\"negative-rating\">-30</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <a class=\"content-title\" href=\"lesson.jsp?lesson_id=123456789\"\">Material Pianos are Made Out Of</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        ");
if(teacher) {
      out.write("\r\n");
      out.write("                            <div class=\"create-new-lesson\" onclick=\"toggleModal('create-lesson-class-modal');\">\r\n");
      out.write("                                <p>Create New Lesson</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        ");
}
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                \r\n");
      out.write("                <div id=\"comments\" class=\"box\">\r\n");
      out.write("                    <div class=\"box-header\">\r\n");
      out.write("                        <h1>COMMENTS</h1>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"box-content\" id=\"comment-section\">\r\n");
      out.write("                        ");
if(user){
      out.write("\r\n");
      out.write("                        <div style=\"width: 100%; text-align: center;\"><div id=\"post-comment\" onclick=\"toggleModal('post-comment-modal');\">Comment</div></div>\r\n");
      out.write("                        ");
}
      out.write("\r\n");
      out.write("                        <div class=\"comment\">\r\n");
      out.write("                            <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                            <div class=\"comment-text\"><p class=\"user\">mgonz108</p><p class=\"text\">This is an awesome class! Woohoo!</p></div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"comment\">\r\n");
      out.write("                            <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                            <div class=\"comment-text\"><p class=\"user\">fmarc011</p><p class=\"text\">I agree! This class is so cool!</p></div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"comment\">\r\n");
      out.write("                            <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                            <div class=\"comment-text\"><p class=\"user\">CoryG</p><p class=\"text\">Yo man this thing is pretty cool</p></div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"comment\">\r\n");
      out.write("                            <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                            <div class=\"comment-text\"><p class=\"user\">LauraP</p><p class=\"text\">I have become an expert in Pianos! Thank you!!</p></div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                \r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"enroll-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>Enroll</h2>\r\n");
      out.write("                <h2 style=\"margin:5px; text-align: center; display: inline-block; width: 15%;\">$<span class=\"class-price\">32</span></h2>\r\n");
      out.write("                <p style=\"display: inline-block; width: 75%;\">The amount stated will be charged to your PayPal account. Please confirm the transaction.</p>\r\n");
      out.write("                <br>\r\n");
      out.write("                <input type=\"button\" onclick=\"enrollClass();\" value=\"Confirm\">\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('enroll-modal');\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"create-lesson-class-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>Create New Lesson</h2>\r\n");
      out.write("                <p>Please fill out the following information to create a new lesson in this class.</p>\r\n");
      out.write("                <h4>Title</h4>\r\n");
      out.write("                <input type=\"text\" id=\"lesson-class-title\" placeholder=\"Lesson Title\">\r\n");
      out.write("                <input type=\"button\" onclick=\"createNewLesson();\" value=\"Create\">\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('create-lesson-class-modal');\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"error-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>Error</h2>\r\n");
      out.write("                <p>It seems you don't have a PayPal account set up yet. If you would like to set it up now, please click \"OK\".</p>\r\n");
      out.write("                <br>\r\n");
      out.write("                <input type=\"button\" onclick=\"redirectProfile();\" value=\"Ok\">\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('error-modal');\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"create-lesson-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>Create New Lesson</h2>\r\n");
      out.write("                <p>Please fill out the following information to create a new lesson.</p>\r\n");
      out.write("                <h4>Title</h4>\r\n");
      out.write("                <input type=\"text\" id=\"lesson-title\" placeholder=\"Lesson Title\">\r\n");
      out.write("                <input type=\"button\" onclick=\"createNewLesson();\" value=\"Create\">\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('create-lesson-modal');\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"create-class-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>Create New Class</h2>\r\n");
      out.write("                <p>Please fill out the following information to create a new class.</p>\r\n");
      out.write("                <h4>Title</h4>\r\n");
      out.write("                <input type=\"text\" id=\"class-title\" placeholder=\"Class Title\">\r\n");
      out.write("                <h4>Price ($):</h4>\r\n");
      out.write("                <input type=\"text\" id=\"class-price\" placeholder=\"Class Price\">\r\n");
      out.write("                <h4>Class Enrollment Limit:</h4>\r\n");
      out.write("                <input type=\"text\" id=\"class-limit\" placeholder=\"Class Limit\">\r\n");
      out.write("                <input type=\"button\" onclick=\"createNewLesson();\" value=\"Create\">\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('create-class-modal');\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("               \r\n");
      out.write("            <div id=\"post-comment-modal\" class=\"box-modal\" style=\"top: 25%;\">\r\n");
      out.write("                <h2>Post New Comment</h2>\r\n");
      out.write("                <p>Please fill out the following information to post a new comment.</p>\r\n");
      out.write("                <h4>Text</h4>\r\n");
      out.write("                <textarea id=\"text-comment\" placeholder=\"Comment Text\" style=\"min-width: 96%; max-width: 96%; min-height: 500px; max-height: 500px;\"></textarea>\r\n");
      out.write("                <input type=\"button\" onclick=\"postNewComment('mgonz108');\" value=\"Create\">\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('post-comment-modal');\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("                        \r\n");
      out.write("            <div id=\"create-assignment-modal\" class=\"box-modal\" style=\"top: 25%;\">\r\n");
      out.write("                <h2>Create New Assignment</h2>\r\n");
      out.write("                <p>Please fill out the following information to create a new assignment.</p>\r\n");
      out.write("                <h4>Title</h4>\r\n");
      out.write("                <input type=\"text\" id=\"assignment-title\" placeholder=\"Assignment Title\">\r\n");
      out.write("                <h4>Description:</h4>\r\n");
      out.write("                <textarea style=\"box-sizing: border-box; padding: 10px; max-width: 100%; min-width: 100%; min-height: 400px; max-height: 400px\" id=\"assignment-description\" placeholder=\"Assignment Description\"></textarea>\r\n");
      out.write("                <input type=\"button\" onclick=\"createNewAssignment();\" value=\"Create\">\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('create-assignment-modal');\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"modal\"></div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
