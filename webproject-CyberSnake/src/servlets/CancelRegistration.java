package servlets;

import util.UtilRegister;
import util.UtilTimeslot;
import datamodel.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CancelRegistration")
public class CancelRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
       public CancelRegistration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String timeslotId = request.getParameter("timeslotId");
		HttpSession session = request.getSession();
		
		Account acc = (Account)session.getAttribute("account");
		
		int cancel = 0;
		
		if(UtilRegister.cancelRegister(acc.getUsername(), timeslotId)){
			UtilTimeslot.increaseOccupancy(Integer.parseInt(timeslotId));
			cancel = 0;
			response.sendRedirect("UserAccount.jsp?cancel="+ cancel);
		}
		else {
			cancel = 1;
			response.sendRedirect("UserAccount.jsp?cancel="+ cancel);
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
