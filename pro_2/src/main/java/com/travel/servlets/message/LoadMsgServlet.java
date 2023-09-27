package com.travel.servlets.message;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.MsgDAO;
import com.travel.db.MySQLConnector;
import com.travel.vo.AdminVO;
import com.travel.vo.MsgVO;
import com.travel.vo.UserVO;


@WebServlet("/loadMsg")
public class LoadMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MsgDAO msgDAO = new MsgDAO();
       	
	//유저쪽에서 문의하기 누르면 호출됨
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		UserVO user = (UserVO) request.getSession().getAttribute("user");
		String userId = user.getUserId(); //session에서 id 받아옴
		ArrayList<MsgVO> msgs = msgDAO.loadMsg(userId); // MySQLConnector의 loadMsg 메소드 실행 후 리턴받음
		ArrayList<AdminVO> admins = msgDAO.getAdmin(); // admin 조회하여 리턴받음
		
		
		request.setAttribute("admins", admins);
		request.setAttribute("msgs", msgs);
		RequestDispatcher rd = request.getRequestDispatcher("message/message.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
