package com.travel.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adminMsgDetailToAnswer")
public class AdminMsgDetailToAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int msgIdx = Integer.parseInt(request.getParameter("msgIDX"));
		String msgName = request.getParameter("msgName");
		String msgSender = request.getParameter("msgSender");
		String msgAskDate = request.getParameter("msgAskDate");
		
		request.setAttribute("msgIDX", msgIdx);
		request.setAttribute("msgName", msgName);
		request.setAttribute("msgSender", msgSender);
		request.setAttribute("msgAskDate", msgAskDate);
		
		RequestDispatcher rs = request.getRequestDispatcher("admin/messageAdminAnswer.jsp");
		rs.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
