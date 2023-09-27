package com.travel.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travel.dao.UserDAO;
import com.travel.vo.UserVO;

@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO = new UserDAO();
    private UserVO user = new UserVO();  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login/userLogin.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		user = userDAO.selectUserDAO(request.getParameter("id"), request.getParameter("pw"));
	
		if (user.getUserId() == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter outs = response.getWriter();
			outs.println("<script>alert('ID 혹은 PW가 다릅니다.'); location.href='userMain';</script>");
			outs.flush();
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("userMain");
		}
	}

}
