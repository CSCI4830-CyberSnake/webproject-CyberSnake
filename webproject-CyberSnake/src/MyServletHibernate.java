import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Account;
import util.UtilDB;

@WebServlet("/MyServletHibernateDB")
public class MyServletHibernate extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernate() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDB.createAccount("elopezbanderas2", "eloy", "lopez", "1234 Main St", "Omaha", "NE", "68107", "elopezbanderas@unomaha.edu", "4021111111", "A", "test1234");
      UtilDB.createEvent("elopezbanderas", "graduation", "ceremony to recognize graduates");
      UtilDB.createTimeSlot("1", "2021-10-14", "1700-1900", "100");
      UtilDB.createRegister("elopezbanderas", "1", "2021-10-14", "1700-1900");
      UtilDB.createReview("elopezbanderas", "5", "The ceremony was great!");
      
      // #2
/*      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Account> listEmployees = UtilDBLopezBanderas.listEmployees();
      for (Account employee : listEmployees) {
         System.out.println("[DBG] " + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", " 
               + employee.getPhone());

         out.println("<li>" + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", " 
               + employee.getPhone() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>"); */
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
