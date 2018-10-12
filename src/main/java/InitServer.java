 package uptime;
// TEST 
 import java.io.IOException;
 import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
 import javax.servlet.ServletConfig;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.jsp.JspEngineInfo;
 import javax.servlet.jsp.JspFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
 
 public class InitServer extends HttpServlet
 {
   private static final long serialVersionUID = -3720115042877301162L;
   private String message;
 
   public void init()
     throws ServletException
   {
    System.setProperty("start", new Long(new Date().getTime()).toString());
   }
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     response.setContentType("text/html");
 
     PrintWriter out = response.getWriter();
     String x = System.getProperty("start");
     String Node = "Application";
     long milliSeconds = Long.parseLong(x);
     Date start = new Date(milliSeconds);
     Date now = new Date();
 
     long diff = Math.abs(now.getTime() - start.getTime()) / 1000L;
     ServletContext application = getServletConfig().getServletContext();
     this.message = 
       ("<html><head><style>#header {	position: relative;	background: blue;	height: 10em;	padding-left: 2em;}</style> <META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"><META HTTP-EQUIV=\"Expires\" CONTENT=\"-1\"><link rel=\"stylesheet\" type=\"text/css\" href=\"default.css\"><title>" + System.getProperty("TCNODE") + "</title></head><body><div id=\"outer\"> <div id=\"header\">  <h1><a href=\"#\">Uptime Application " +  System.getProperty("TCNODE") + " </a></h1>" + 
       " </div>" + 
       " <div class=\"main\"><table><tr><td align=\"right\">Uptime Application Version :</td><td>" + application.getServerInfo() + "</td></tr>");
     this.message = (this.message + "<tr><td align=\"right\">Servlet Specification Version :</td><td>" + application.getMajorVersion() + "." + application.getMinorVersion() + "</td></tr>");
     this.message = (this.message + "<tr><td align=\"right\">JSP version :</td><td>" + JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() + "</td></tr>");	 	 
     this.message = (this.message + "<tr><td align=\"right\">Start Time :</td><td>" + start.toString() + "</td></tr>");
     this.message += "<tr><td align=\"right\">&nbsp;</td><td>&nbsp;</td></tr>";
     this.message += "<tr><td align=\"right\">Application Version :</td><td>3.3</td></tr>";
     this.message = (this.message + "<tr><td align=\"right\">Up Time :</td><td>" + diff + " seconds</td></tr>");
 
     this.message += "</table>";
     out.println(this.message + "</div></div></body></html>");
   }
 
   public void destroy()
   {
   }
 }

