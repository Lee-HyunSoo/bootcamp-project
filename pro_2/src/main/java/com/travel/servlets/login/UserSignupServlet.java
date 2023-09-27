package com.travel.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.UserDAO;
import com.travel.vo.UserVO;


@WebServlet("/userSignup")
public class UserSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDAO userDAO = new UserDAO();
	private UserVO user = new UserVO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login/userSignup.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		user.setUserId(request.getParameter("id"));
		user.setUserPw(request.getParameter("pw"));
		user.setUserName(request.getParameter("name"));
		user.setUserEmail(request.getParameter("email"));
		user.setUserTel(request.getParameter("tel"));

		if (userDAO.selectUserSignUp(request.getParameter("id"))) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter outs = response.getWriter();
			outs.println("<script>alert('중복 된 회원입니다.'); location.href='userSignup';</script>");
			outs.flush();
		}
		else {
			userDAO.insertUserSignUp(user);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter outs = response.getWriter();
			outs.println("<script>alert('회원가입이 완료되었습니다.'); location.href='userMain';</script>");
			outs.flush();
		}
	}

}
