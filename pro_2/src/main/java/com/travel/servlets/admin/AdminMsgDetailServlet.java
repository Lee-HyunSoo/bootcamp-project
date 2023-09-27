package com.travel.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.MsgDAO;
import com.travel.vo.AdminVO;
import com.travel.vo.MsgVO;

@WebServlet("/adminMsgDetail")
public class AdminMsgDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MsgDAO msgDAO = new MsgDAO();

	//messageAdmin 의 a태그를 타고 넘어옴
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int msgIDX = Integer.parseInt(request.getParameter("msgIDX"));
		
		MsgVO msgDetail = msgDAO.msgDetail(msgIDX); //IDX로 선택 메세지 데이터 읽어오기
		msgDAO.adminIsRead(msgIDX); // 관리자 읽음 상태로 변경
		AdminVO admin = (AdminVO) request.getSession().getAttribute("admin");
		
		request.setAttribute("admin", admin);
		request.setAttribute("msgDetail", msgDetail);
		RequestDispatcher rd = request.getRequestDispatcher("admin/messageAdminDetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
