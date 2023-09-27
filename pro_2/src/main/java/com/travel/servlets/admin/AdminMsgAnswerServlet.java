package com.travel.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.MsgDAO;

@WebServlet("/adminMsgAnswer")
public class AdminMsgAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MsgDAO msgDAO = new MsgDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//messageAdminDetail.jsp로 부터 넘어옴
		request.setCharacterEncoding("UTF-8");
		
		//messageAdminAnswer.jsp로 부터 받은 파라미터
		int msgIDX = Integer.parseInt(request.getParameter("msgIDX"));
		String msgName = request.getParameter("msgName");
		String msgSender = request.getParameter("msgSender");
		String msgReceiver = request.getParameter("msgReceiver");
		String msgTitle = request.getParameter("msgTitle");
		String msgContent = request.getParameter("msgContent");
		String msgAskDate = request.getParameter("msgAskDate");
		msgDAO.answerMsg(msgName, msgSender, msgReceiver, msgTitle, msgContent, msgAskDate);//문의답변 DB저장
		msgDAO.answerStatus(msgIDX);//문의상태업데이트
		
		response.sendRedirect("loadAdminMsg");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
