package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClaimsController
 */
@WebServlet("/ClaimsController")
public class ClaimsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaimsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String value = request.getParameter("value");
		RequestDispatcher rd = null;
		if("claim".equals(action)){
			if("domiciliary".equals(value))
				rd = request.getRequestDispatcher("/jsp/domiciliaryclaim.jsp");
			else if("hospitalization".equals(value))
				rd = request.getRequestDispatcher("/jsp/hospitalizationclaim.jsp");				
		}
		else if("search_claim".equals(action))
			rd = request.getRequestDispatcher("/jsp/search_claim.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
