package com.travel.servlets.pay;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travel.dao.PurchaseListDAO;
import com.travel.db.MySQLConnector;
import com.travel.vo.PurchaseListVO;
import com.travel.vo.UserVO;


@WebServlet("/payList")
public class PayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PurchaseListDAO purchaseListDAO = new PurchaseListDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
//		//0. 로그인 중일 때만 이 페이지가 열려야 한다. -> 아니면 바로 main.jsp로 리다이렉트

		HttpSession httpSession = request.getSession();
		UserVO user = (UserVO) httpSession.getAttribute("user");
		ArrayList<PurchaseListVO> items = new ArrayList<PurchaseListVO>();
		//1. purchase에서 가져온 데이터를 임시결제함 리스트에 뿌리되,
		//trvpay=1,tempapprove=1(결제 및 승인완료)들만 리스트에 뿌린다.
		items = purchaseListDAO.get_purchaselist(user.getUserIdx());
		
		request.setAttribute("items", items);
		
		//temp_playlist.jsp(임시결제함)로 리다이렉트
		RequestDispatcher rd = request.getRequestDispatcher("pay/paylist.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
