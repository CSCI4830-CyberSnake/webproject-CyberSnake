package servlets;

import util.UtilRegister;
import util.UtilTimeslot;
import datamodel.Timeslot;
import datamodel.Account;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegisterEvent")
public class RegisterEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegisterEvent() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int timeslotId = Integer.parseInt(request.getParameter("timeslotId"));
		HttpSession session = request.getSession();
		Timeslot ts = UtilTimeslot.getTimeslot(timeslotId);
		Account acc = (Account)session.getAttribute("account");
		int register = 0;
    
		if(ts.getOccupancy() > 0) {
			
			if(UtilRegister.createRegister(acc.getUsername(), String.valueOf(timeslotId))){
				UtilTimeslot.decreaseOccupancy(timeslotId);
				register = 0;
				response.sendRedirect("UserAccount.jsp?register="+ register);
			}
			else{
				register = 1;
				response.sendRedirect("UserAccount.jsp?register="+ register);
			}
		}else {
			register = 1;
			response.sendRedirect("UserAccount.jsp?register="+ register);
		}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


			
	





