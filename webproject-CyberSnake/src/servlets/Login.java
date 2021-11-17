package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.Account;
import util.UtilAccount;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = UtilAccount.getAccount(username);
		
		if( account!= null && account.getPassword().equals(password)){
			session.setAttribute("account", account);
			response.sendRedirect("UserAccount.jsp");
		}
		else if(account == null){
			session.setAttribute("noAccount", "true");
			response.sendRedirect("LoginForm.jsp");
		}
		else if(!account.getPassword().equals(password)){
			session.setAttribute("invalidPassword", "true");
			response.sendRedirect("LoginForm.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
