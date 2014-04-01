/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.34
 * Generated at: 2014-04-01 03:04:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.BufferedReader;

public final class lesson_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Create Class - uBoard</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width\">\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"/favicon.png\">\r\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>\r\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>\r\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>\r\n");
      out.write("        \r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/master.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/lesson.css\">\r\n");
      out.write("        \r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/jquery-ui-1.10.4.min.css\">\r\n");
      out.write("        \r\n");
      out.write("        <script src=\"scripts/jquery-1.10.2.js\"></script>\r\n");
      out.write("        <script src=\"scripts/jquery-ui-1.10.4.min.js\"></script>\r\n");
      out.write("        <script src=\"scripts/home.js\"></script>\r\n");
      out.write("        <script src=\"scripts/lesson.js\"></script>\r\n");
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
                String title = "";
                boolean user    = false;
                boolean lesson  = false; 
                
                if ((id = request.getParameter("id")) != null){
                    user = true;
                }
                
                if((title = request.getParameter("lesson_id")) != null){
                    lesson  = true;
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
      out.write("                    \r\n");
      out.write("    ");
if(!lesson){
      out.write("\r\n");
      out.write("            <div id=\"save\" onclick=\"saveLessonData();\"><p>Save</p></div>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"sidebar\" class=\"hidden\">\r\n");
      out.write("                <div class=\"box drag title-box\" title=\"Title\"><img class=\"handle edit\" /><img class=\"remove edit\" /><h1 contenteditable=\"\">Edit me!</h1></div>\r\n");
      out.write("                <div class=\"box drag text-box\" title=\"Text Box\"><img class=\"handle edit\" /><img class=\"remove edit\" /><div contenteditable=\"\">Edit Me!</div></div>\r\n");
      out.write("                <div class=\"box drag image-box\" title=\"Image Box\"><img class=\"handle edit\" /><img class=\"remove edit\" /><div class=\"images\"><img class=\"image\"  src=\"/images/blanked.png\" /><img class=\"image\" src=\"/images/blanked.png\" /><img class=\"image\" src=\"/images/blanked.png\" /></div><div class=\"buttons edit\"><input type=\"button\" class=\"change-image\" onclick=\"openImageModal(this);\" value=\"Change Image\"/><input type=\"button\" class=\"change-image\" onclick=\"openImageModal(this);\" value=\"Change Image\"/><input type=\"button\" class=\"change-image\" onclick=\"openImageModal(this);\" value=\"Change Image\"/></div></div>\r\n");
      out.write("                <div class=\"box drag video-box\" title=\"Video Box\"><img class=\"handle edit\" /><img class=\"remove edit\" /><iframe class=\"video\" width=\"853\" height=\"480\"  src=\"//www.youtube.com/embed/oJg2_dUHd84\" frameborder=\"0\" allowfullscreen></iframe><input type=\"button\" class=\"change-video edit\" onclick=\"openVideoModal(this);\" value=\"Change Video\"/></div>\r\n");
      out.write("                <div id=\"sidebar-click-area\" onclick=\"toggleSideBar();\"></div>\r\n");
      out.write("                <div id=\"sidebar-handle\" onclick=\"toggleSideBar();\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div id=\"content\">\r\n");
      out.write("                <div id=\"main-lesson-title\" class=\"box\" title=\"Lesson Title\"><h1 contenteditable=\"false\">How To Bring The House Down!</h1><h2 id=\"username\">mgonz108</h2></div>\r\n");
      out.write("                <div id=\"sort\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <div id=\"img-box-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>Image Source</h2>\r\n");
      out.write("                <p>Please copy and paste the URL for the picture you wish to display</p>\r\n");
      out.write("                <input type=\"text\" id=\"image-url\" placeholder=\"Image Url\">\r\n");
      out.write("                <input type=\"button\" onclick=\"changeImage();\" value=\"Save\">\r\n");
      out.write("                <input type=\"button\" onclick=\"hideModal();\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"video-box-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>YouTube Video Source</h2>\r\n");
      out.write("                <p>Please copy and paste the URL for the YouTube video you wish to display</p>\r\n");
      out.write("                <input type=\"text\" id=\"video-url\" placeholder=\"Video Url\">\r\n");
      out.write("                <input type=\"button\" onclick=\"changeVideo();\" value=\"Save\">\r\n");
      out.write("                <input type=\"button\" onclick=\"hideModal();\" value=\"Cancel\">\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"save-confirm-modal\" class=\"box-modal\">\r\n");
      out.write("                <h2>Lesson Saved Successfully!</h2>\r\n");
      out.write("                <input type=\"button\" onclick=\"toggleModal('save-confirm-modal');\" value=\"Ok\">\r\n");
      out.write("            </div>\r\n");
      out.write("    ");
} else {
      out.write("\r\n");
      out.write("        <div id=\"rating\">\r\n");
      out.write("            <div class=\"rate\" id=\"rate-positive\" onclick=\"rate(1);\"><img src=\"/images/rating/pos-rate.png\"></div>\r\n");
      out.write("            <div class=\"lesson-rating\"><p class=\"positive-rating\">0</p></div>\r\n");
      out.write("            <div class=\"rate\" id=\"rate-negative\" onclick=\"rate(-1);\"><img src=\"/images/rating/neg-rate.png\"></div>\r\n");
      out.write("        </div>\r\n");
      out.write("    \r\n");
      out.write("        <div id=\"content\" style=\"left: 0;\">\r\n");
      out.write("            <div id=\"main-lesson-title\" class=\"box\" title=\"Lesson Title\"><h1 contenteditable=\"false\">How To Bring The House Down!</h1><h2 id=\"username\">mgonz108</h2></div>\r\n");
      out.write("            <div class=\"box title-box\" title=\"Title\"><h1 contenteditable=\"false\">Overview</h1></div>\r\n");
      out.write("            <div class=\"box text-box\" title=\"Text Box\"><div contenteditable=\"false\">In this lesson we are going to be atalking about all the different things you must do in order to bring the house down! Please make sure to read through the entire lesson in order to truly be regarded as a force to be reckoned with whenever you decided to embark in the amazing feat that is the bringing of the house down!</div></div>\r\n");
      out.write("            <div class=\"box image-box\" title=\"Image Box\"><div class=\"images\"><img class=\"image\"  src=\"http://static.tumblr.com/zlyygir/SBgll7q5e/bthd.jpg\" /><img class=\"image\" src=\"http://seangilliganproductions.com/wp-content/uploads/2012/09/Bring-the-House-Down-80s-v2-01-540x405.jpg\" /><img class=\"image\" src=\"http://a2.mzstatic.com/us/r30/Music/91/c4/34/mzi.ggbthkpb.170x170-75.jpg\" /></div></div>\r\n");
      out.write("            <div id=\"comments\" class=\"box\">\r\n");
      out.write("                <div class=\"box-header\">\r\n");
      out.write("                    <h1>COMMENTS</h1>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"box-content\" id=\"comment-section\">\r\n");
      out.write("                    <div style=\"width: 100%; text-align: center;\"><div id=\"post-comment\" onclick=\"toggleModal('post-comment-modal');\">Comment</div></div>\r\n");
      out.write("                    <div class=\"comment\">\r\n");
      out.write("                        <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                        <div class=\"comment-text\"><p class=\"user\">mgonz108</p><p class=\"text\">This is an awesome lesson! Woohoo!</p></div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"comment\">\r\n");
      out.write("                        <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                        <div class=\"comment-text\"><p class=\"user\">fmarc011</p><p class=\"text\">I agree! This lesson is so cool!</p></div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"comment\">\r\n");
      out.write("                        <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                        <div class=\"comment-text\"><p class=\"user\">CoryG</p><p class=\"text\">Yo man this thing is pretty cool</p></div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"comment\">\r\n");
      out.write("                        <div class=\"comment-user\"><img src=\"/images/comments/user-comment.png\"></div>\r\n");
      out.write("                        <div class=\"comment-text\"><p class=\"user\">LauraP</p><p class=\"text\">I have become an expert in Bringing the House Down! Thank you!!</p></div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("    ");
}
      out.write("\r\n");
      out.write("        <div id=\"create-lesson-modal\" class=\"box-modal\">\r\n");
      out.write("            <h2>Create New Lesson</h2>\r\n");
      out.write("            <p>Please fill out the following information to create a new lesson.</p>\r\n");
      out.write("            <h4>Title</h4>\r\n");
      out.write("            <input type=\"text\" id=\"lesson-title\" placeholder=\"Lesson Title\">\r\n");
      out.write("            <input type=\"button\" onclick=\"createNewLesson();\" value=\"Create\">\r\n");
      out.write("            <input type=\"button\" onclick=\"toggleModal('create-lesson-modal');\" value=\"Cancel\">\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("         <div id=\"create-class-modal\" class=\"box-modal\">\r\n");
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
      out.write("        \r\n");
      out.write("        <div id=\"post-comment-modal\" class=\"box-modal\" style=\"top: 25%;\">\r\n");
      out.write("            <h2>Post New Comment</h2>\r\n");
      out.write("            <p>Please fill out the following information to post a new comment.</p>\r\n");
      out.write("            <h4>Text</h4>\r\n");
      out.write("            <textarea id=\"text-comment\" placeholder=\"Comment Text\" style=\"min-width: 96%; max-width: 96%; min-height: 500px; max-height: 500px;\"></textarea>\r\n");
      out.write("            <input type=\"button\" onclick=\"postNewComment('mgonz108');\" value=\"Create\">\r\n");
      out.write("            <input type=\"button\" onclick=\"toggleModal('post-comment-modal');\" value=\"Cancel\">\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("        <div id=\"modal\"></div>\r\n");
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
