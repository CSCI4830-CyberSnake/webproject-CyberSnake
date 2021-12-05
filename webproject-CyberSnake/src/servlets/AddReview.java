package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.Account;
import util.UtilReview;


@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddReview() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stars = request.getParameter("stars");
		String comment = request.getParameter("comment").trim();
		String anon = request.getParameter("anon");
		String eventId = request.getParameter("eventId");
		boolean flag = true;
		
		Account account = null;
		HttpSession session = request.getSession(false);
        if (session != null) {
        	account = (Account) session.getAttribute("account");
        } 
				
		if( stars == null ) {
			response.sendRedirect("UserAccount.jsp?review=1");
		}
		else {
			if( anon == null ) {
				if( !UtilReview.createReview(account.getUsername(), eventId, stars, comment, "false") ) {
					flag = false;
					response.sendRedirect("UserAccount.jsp?review=1");
				}
			}
			else {
				if( !UtilReview.createReview(account.getUsername(), eventId, stars, comment, "true") ) {
					flag = false;
					response.sendRedirect("UserAccount.jsp?review=1");
				}
			}
		}
		if( flag )
			response.sendRedirect("UserAccount.jsp?review=0");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}