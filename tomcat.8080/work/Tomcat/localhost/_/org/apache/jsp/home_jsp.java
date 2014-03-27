/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.34
 * Generated at: 2014-03-27 04:05:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.BufferedReader;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/master.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/jquery-ui-1.10.4.min.css\">\r\n");
      out.write("        <script src=\"scripts/jquery-1.10.2.js\"></script>\r\n");
      out.write("        <script src=\"scripts/jquery-ui-1.10.4.min.js\"></script>\r\n");
      out.write("        <script src=\"scripts/home.js\"></script>\r\n");
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
      out.write("                            <div id=\"login\">\r\n");
      out.write("                                <span style=\"position:relative; top:15px;\">LOG IN</span>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        <div id=\"login-modal\">\r\n");
      out.write("                            <form>\r\n");
      out.write("                                <input type=\"text\" id=\"login-user\" class=\"text-input\" placeholder=\"Username\">\r\n");
      out.write("                                <span id=\"login-user-pic\"></span>\r\n");
      out.write("                                <input type=\"password\" id=\"login-pass\" class=\"text-input\" placeholder=\"Password\">\r\n");
      out.write("                                <span id=\"login-pass-pic\"></span>\r\n");
      out.write("                                <input id=\"login-button\" class=\"button\" type=\"submit\" value=\"LOG IN\">\r\n");
      out.write("                                <a href=\"/register.jsp\"><input href=\"/register.jsp\" id=\"register-button\" class=\"button\" type=\"button\" value=\"REGISTER\" /></a>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div id=\"bottom\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"content\">\r\n");
      out.write("                <div class=\"content-box\">\r\n");
      out.write("                    \r\n");
      out.write("                    <p>Pages done or work in progress:</p>\r\n");
      out.write("                    <a href='home.jsp'>Home</a>\r\n");
      out.write("                    <a href='register.jsp'>Register</a>\r\n");
      out.write("                    <a href='lesson.jsp'>Lesson</a>\r\n");
      out.write("                    <a href='profile.jsp'>Profile</a>\r\n");
      out.write("                    <a href='class.jsp'>Class</a>\r\n");
      out.write("                    \r\n");
      out.write("                    <h1>U | BOARD - COMMUNITY LEARNING</h1>\r\n");
      out.write("                    <h2>A tool made for learning and teaching just about anything you can imagine.</h2>\r\n");
      out.write("                    <hr>\r\n");
      out.write("                    <h3>Overview</h3>\r\n");
      out.write("                    <p>\r\n");
      out.write("                        Welcome to Uboard, if you are here that means that you must also share the same passion for learning as we do. That same passion\r\n");
      out.write("                        is what drove us to create this website. We, the Uboard team, wanted to be able to learn about many different subjects without having\r\n");
      out.write("                        to pay for expensive classes. The problem then was how could we learn new things, in an organized fashion, without the difficulties that\r\n");
      out.write("                        bring a physical class or browsing endlessly through the internet for information?\r\n");
      out.write("                    </p>\r\n");
      out.write("                    <p>\r\n");
      out.write("                        After a lot of thinking, the Uboard team realized that by creating a medium in which users could both teach and learn in a smooth and\r\n");
      out.write("                        organized manner, then learning about new subjects would be fun and painless. Many users in our community are both teaching and learning\r\n");
      out.write("                        new things everyday; from how to cook a delicious dinner to the principals of quantum physics, all organized by community members that\r\n");
      out.write("                        have experience with these topics.\r\n");
      out.write("                    </p>\r\n");
      out.write("                    <p>\r\n");
      out.write("                        Think you have the same passion that drives our community? Become a member now and start your journey through a cybernetic land filled \r\n");
      out.write("                        with the passion for learning. Also, don't be discouraged! If you think you are great at a particular subject, create a lesson and\r\n");
      out.write("                        contribute to the community's growing passion for knowledge.\r\n");
      out.write("                    </p>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
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
