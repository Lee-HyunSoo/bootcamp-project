package com.travel.servlets.message;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.MsgDAO;
import com.travel.db.MySQLConnector;
import com.travel.vo.MsgVO;

@WebServlet("/msgDetail")
public class MsgDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MsgDAO msgDAO = new MsgDAO();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int msgIDX = Integer.parseInt(request.getParameter("msgIDX"));
		
		MsgVO msgDetail = msgDAO.msgDetail(msgIDX);
		msgDAO.userIsRead(msgIDX);
		
		request.setAttribute("msgDetail", msgDetail);
		RequestDispatcher rd = request.getRequestDispatcher("message/messageDetail.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
