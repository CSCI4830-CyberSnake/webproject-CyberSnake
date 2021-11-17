

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.Account;


@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddEvent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name").trim();
		String description = request.getParameter("description").trim();
		
		int event = 0;
		Account account = null;
		HttpSession session = request.getSession(false);
        if (session != null) {
        	account = (Account) session.getAttribute("account");
        }

		if(name.isEmpty() || description.isEmpty()) {
			event = 1;
		}
		
		else if(!util.UtilEvent.createEvent(account.getUsername(), name, description)){
			event = 1;
		}
		
		response.sendRedirect("UserAccount.jsp?event="+event);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}