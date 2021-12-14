package servlets;

import util.UtilTimeslot;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
//import java.lang.System.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

import datamodel.Timeslot;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@WebServlet("/AddTimeslot")
public class AddTimeslot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eventId = request.getParameter("eventId");
		String date = request.getParameter("date"); // YYYY-MM-DD
		String occupancy = request.getParameter("occupancy");
		// LocalDate time = null;
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		int time = 0;

		if (date.isEmpty() || occupancy.isEmpty() || eventId.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
			time = 1;
			response.sendRedirect("UserAccount.jsp?timeslot=" + time);
		} 
		
		else if( !Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date) || !Pattern.matches("\\d{2}:\\d{2}", startTime) || 
				!Pattern.matches("\\d{2}:\\d{2}", endTime)){
		
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CreateTimeslot.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("CreateTimeslot.jsp");
		}
		
		else if( UtilTimeslot.getIntTime(startTime) < 600 || (UtilTimeslot.getIntTime(endTime) > 0 && UtilTimeslot.getIntTime(endTime) < 600) ) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CreateTimeslot.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
		
			Date dt = Date.valueOf(date);
	
			if (UtilTimeslot.available(dt, startTime, endTime)) {
				time = 0;
				if (UtilTimeslot.createTimeslot(eventId, date, startTime, endTime, occupancy)) {
					response.sendRedirect("UserAccount.jsp?time=" + time);
				} else {
					time = 1;
					response.sendRedirect("UserAccount.jsp?time=" + time);
				}
	
			} else {
				time = 1;
				response.sendRedirect("UserAccount.jsp?time=" + time);
			}
		} 
	} 

}
