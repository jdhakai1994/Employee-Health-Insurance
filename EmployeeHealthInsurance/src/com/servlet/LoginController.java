package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

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
		
		doService(request, response);
	}

	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("Entering doService() in LoginController Class");
		
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		//Retrieving username and password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Making a bean
		Login input = new Login();
		input.setUsername(username);
		input.setPassword(password);
				
		LoginService ls = new LoginService();
		String login_reply = null;
		String register_reply = null;
		
		RequestDispatcher rd = null;
		//If/else block for action
		if("login".equals(action)){
			System.out.println("In login if/else action block");
			try {
				login_reply = ls.loginUser(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("register".equals(action)){
			System.out.println("In register if/else action block");
			try {
				register_reply = ls.registerUser(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//If/else block for the login_reply
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
		else if("member".equals(login_reply)){
			System.out.println("In member if/else login_reply block");
			rd = request.getRequestDispatcher("jsp/homepages/member.jsp");
		}
		else if("admin".equals(login_reply)){
			System.out.println("In admin if/else login_reply block");
			rd = request.getRequestDispatcher("jsp/homepages/admin.jsp");
		}
		
		//If/else block for the register_reply
		if("success".equals(register_reply)){
			System.out.println("In success if/else register_reply block");
			rd = request.getRequestDispatcher("jsp/homepages/member.jsp");
		}
		else if("fail".equals(register_reply)){
			System.out.println("In fail if/else register_reply block");
			request.setAttribute("message", "The username is already in use");
			rd = request.getRequestDispatcher("jsp/forms/loginForm.jsp");
		}
		
		rd.forward(request, response);
	}
	
}
