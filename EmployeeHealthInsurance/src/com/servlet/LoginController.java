package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Login;
import com.services.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entering doGet() in LoginController Class");
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/forms/loginForm.jsp");
				
		System.out.println("Exiting doGet() in LoginController Class");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doPost() in LoginController Class");
		
		//this is retrieved from the hidden value passed while submitting the form
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		//retrieving username and password from loginForm.jsp
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//making a login bean
		Login input = new Login();
		input.setUsername(username);
		input.setPassword(password);
				
		LoginService ls = new LoginService();
		String login_reply = null;
		String register_reply = null;
		
		RequestDispatcher rd = null;
		//if-else code block for action
		if("login".equals(action)){
			System.out.println("In login action if-else action block");
			long logon = Long.parseLong(request.getParameter("logon"));
			input.setLogon(logon);
			try {
				login_reply = ls.loginUser(input);
				
				//if-else code block for the login_reply
				if("not_registered".equals(login_reply)){
					System.out.println("In not_registered if/else login_reply block");
					request.setAttribute("message", "You need to register yourself first");
					rd = request.getRequestDispatcher("jsp/forms/loginForm.jsp");
				}
				else if("wrong_credentials".equals(login_reply)){
					System.out.println("In wrong_credentials if/else login_reply block");
					request.setAttribute("message", "Your username and password doesn't match");
					rd = request.getRequestDispatcher("jsp/forms/loginForm.jsp");		
				}
				else if("session_expired".equals(login_reply)){
					System.out.println("In session_expired if/else login_reply block");
					request.setAttribute("message", "Your session has expired, please login again to continue");
					rd = request.getRequestDispatcher("jsp/forms/loginForm.jsp");		
				}
				else if("member".equals(login_reply)){
					System.out.println("In member if/else login_reply block");
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("role", "member");
					rd = request.getRequestDispatcher("jsp/homepages/member.jsp");
				}
				else if("admin".equals(login_reply)){
					System.out.println("In admin if/else login_reply block");
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("role", "admin");
					rd = request.getRequestDispatcher("jsp/homepages/admin.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("register".equals(action)){
			System.out.println("In register action if-else block");
			try {
				register_reply = ls.registerUser(input);
				
				//If-else code block for the register_reply
				if("success".equals(register_reply)){
					System.out.println("In success if/else register_reply block");
					rd = request.getRequestDispatcher("jsp/homepages/member.jsp");
				}
				else if("fail".equals(register_reply)){
					System.out.println("In fail if/else register_reply block");
					request.setAttribute("message", "The username is already in use");
					rd = request.getRequestDispatcher("jsp/forms/loginForm.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("logout".equals(action)){
			System.out.println("In logout action if-else block");

			//get reference to existing session
			HttpSession session = request.getSession(false);
			session.removeAttribute("username");
			session.removeAttribute("role");
			session.invalidate();
				
			//redirecting the user to login page
			rd = request.getRequestDispatcher("jsp/forms/loginForm.jsp");			
		}
		System.out.println("Exiting doPost() in LoginController Class");
		rd.forward(request, response);
	}	
}
