/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.34
 * Generated at: 2014-03-18 18:35:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Home - uBoard</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width\">\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"/favicon.png\">\r\n");
      out.write("        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"styles/master.css\">\r\n");
      out.write("        <script src=\"scripts/jquery1.11.0.min.js\"></script>\r\n");
      out.write("        <script src=\"scripts/home.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div id=\"container\">\r\n");
      out.write("            <div id=\"top-banner\">\r\n");
      out.write("                <div id=\"top\">\r\n");
      out.write("                    <div id=\"search-box\">\r\n");
      out.write("                        <form>\r\n");
      out.write("                            <input type=\"text\" id=\"content-search\" val=\"\" placeholder=\"Search\">\r\n");
      out.write("                            <img id=\"mag-glass\" />\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <a class=\"logo\" title=\"Take me to the Home Page!\" href=\"/\"\">\r\n");
      out.write("                        <img src=\"/images/logos/uboard_logo_side.png\" />\r\n");
      out.write("                    </a>\r\n");
      out.write("                    \r\n");
      out.write("                    <div id=\"user-auth\">\r\n");
      out.write("                            <div id=\"login\">\r\n");
      out.write("                                <p>LOG IN</p>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        <div id=\"login-modal\">\r\n");
      out.write("                            <form>\r\n");
      out.write("                                <input type=\"text\" id=\"login-user\" class=\"text-input\" placeholder=\"Username\">\r\n");
      out.write("                                <span id=\"login-user-pic\"></span>\r\n");
      out.write("                                <input type=\"password\" id=\"login-pass\" class=\"text-input\" placeholder=\"Password\">\r\n");
      out.write("                                <span id=\"login-pass-pic\"></span>\r\n");
      out.write("                                <input id=\"login-button\" class=\"button\" type=\"submit\" value=\"LOG IN\">\r\n");
      out.write("                                <input id=\"register-button\" class=\"button\" type=\"button\" value=\"REGISTER\">\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div id=\"bottom\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"sidebar\"></div>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"content\">\r\n");
      out.write("                <span class=\"message\" style=\"padding-top: 100px; padding-bottom: 50px;\"> <iframe width=\"560\" height=\"315\" src=\"//www.youtube.com/embed/QHs_2-pB_6c\" frameborder=\"0\" allowfullscreen></iframe><img src=\"images/logos/uboard_logo.png\" alt=\"images/logos/uboard_logo.png\" /></span><span class=\"message\">IN DEVELOPMENT</span> \r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
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
