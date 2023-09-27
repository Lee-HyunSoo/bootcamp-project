package com.travel.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.MsgDAO;
import com.travel.vo.AdminVO;
import com.travel.vo.MsgVO;

@WebServlet("/loadAdminMsg")
public class LoadAdminMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MsgDAO msgDAO = new MsgDAO();
	
	//ADMIN쪽에서 클릭하면 현재 서블릿이 호출됨
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		AdminVO admin = (AdminVO) request.getSession().getAttribute("admin");
		String adminId = admin.getAdminId(); //session에서 id 받아옴
	
		ArrayList<MsgVO> msgs = msgDAO.loadMsg(adminId); // MySQLConnector의 loadMsg 메소드 실행 후 리턴받음
		request.setAttribute("msgs", msgs);			
		RequestDispatcher rd = request.getRequestDispatcher("admin/messageAdmin.jsp");
		rd.forward(request, response);

		}//doGET() END

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
