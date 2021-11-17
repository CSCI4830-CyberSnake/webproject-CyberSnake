package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.UtilAccount;
/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Validates user input and if all fields are filled in and under 20 characters creates the account in the DB.
	 * @param request - Information passed from the html form.
	 * @param response - Similar to the request but information can be changed.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//All fields associated with account creation.
		String userName = request.getParameter("username");
		String fName = request.getParameter("firstname");
		String lName = request.getParameter("lastname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String admin = request.getParameter("admin");
		
		
		
		//Checks if all fields were filled in, if not fills missingInfo attribute and redirects back to the form.
		if (userName.isEmpty() || fName.isEmpty() || lName.isEmpty() || address.isEmpty() ||
				state.isEmpty() || zip.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
			session.setAttribute("missingInfo", "true");
			response.sendRedirect("CreateAccountForm.jsp");
		}
		
		//Checks if any fields inserted exceeded 20 characters in size redirects with fieldLong attribute set to true if so.
		if (userName.length() > 20 || fName.length() > 20 || lName.length() > 20 || address.length() > 20 ||
				state.length() > 20 || zip.length() > 20 || email.length() > 20 || phone.length() > 20 || password.length() > 20 || 
				admin.length() > 20) {
			session.setAttribute("fieldLong", "true");
			response.sendRedirect("CreateAccountForm.jsp");
		}
		//Checks if the account is already in use and will redirect if so.
		if (UtilAccount.getAccount(userName) != null) {
			session.setAttribute("nameTaken", "true");
			response.sendRedirect("CreateAccountForm.jsp");
		}
		
		

		//Adds the account to the database.
		UtilAccount.createAccount(userName, fName, lName, address, city, state, zip, email, phone, password, admin);
		
		//Adds the account to the session (attributes are stored as objects so all account methods should work).
		session.setAttribute("account", UtilAccount.getAccount(userName));
		
		response.sendRedirect("UserAccount.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
