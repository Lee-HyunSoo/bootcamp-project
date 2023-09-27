package com.travel.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.AdminDAO;
import com.travel.vo.AdminVO;

@WebServlet("/adminSignup")
public class AdminSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminDAO adminDAO = new AdminDAO();
	private AdminVO admin = new AdminVO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login/adminSignup.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		admin.setAdminId(request.getParameter("id"));
		admin.setAdminPw(request.getParameter("pw"));
		admin.setAdminName(request.getParameter("name"));
		admin.setAdminEmail(request.getParameter("email"));
		admin.setAdminTel(request.getParameter("tel"));
		
		if (adminDAO.selectAdminSignUp(request.getParameter("id"))) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter outs = response.getWriter();
			outs.println("<script>alert('중복 된 회원입니다.'); location.href='adminSignup';</script>");
			outs.flush();
		}
		else {		
			adminDAO.insertAdminSignUp(admin);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter outs = response.getWriter();
			outs.println("<script>alert('회원가입이 완료되었습니다.'); location.href='adminMain';</script>");
			outs.flush();
		}
	}

}
