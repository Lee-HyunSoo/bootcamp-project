package com.travel.servlets.pay;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travel.dao.PurchaseListDAO;
import com.travel.db.MySQLConnector;
import com.travel.vo.UserVO;


//결제 완료되지 않은, 결제 대기중인 관광 상품 purchase 테이블에서 삭제
@WebServlet("/tempRefundServlet")
public class TempRefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PurchaseListDAO purchaseListDAO = new PurchaseListDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//결제 완료되지 않은, 결제 대기중인 관광 상품 purchase 테이블에서 삭제
		request.setCharacterEncoding("UTF-8");
		int trvidx = Integer.parseInt(request.getParameter("trvidx"));
		
		HttpSession httpSession = request.getSession();
		UserVO user = (UserVO) httpSession.getAttribute("user");
		//결제 완료되지 않은, 결제 대기중인 관광 상품 purchase 테이블에서 삭제
		purchaseListDAO.delete_temp_pay(trvidx, user.getUserIdx());
		
		//메인 화면으로 리다이렉트
		RequestDispatcher rd = request.getRequestDispatcher("userMain");
		rd.forward(request, response);
	}

}
