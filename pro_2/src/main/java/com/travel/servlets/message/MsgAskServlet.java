package com.travel.servlets.message;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.MsgDAO;
import com.travel.db.MySQLConnector;


@WebServlet("/msgAsk")
public class MsgAskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MsgDAO msgDAO = new MsgDAO();
	
	//message.jsp에서 문의하기 버튼 클릭하면 넘어옴.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String msgTitle = request.getParameter("msgTitle");
		String msgName = request.getParameter("msgName");
		String msgSender = request.getParameter("msgSender");
		String msgContent = request.getParameter("msgContent");
				
		msgDAO.askMsg(msgTitle, msgName, msgSender, msgContent);
		response.sendRedirect("loadMsg");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
