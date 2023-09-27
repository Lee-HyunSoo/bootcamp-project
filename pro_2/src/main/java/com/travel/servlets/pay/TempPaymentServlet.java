package com.travel.servlets.pay;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.PurchaseListDAO;
import com.travel.db.MySQLConnector;

//임시 결제중인 관광 상품을 결제하는 것
//TRVPAY를 참으로,TRVAPPROVE는 여전히 거짓임.(관리자가 승인하지 않음)
@WebServlet("/tempPaymentServlet")
public class TempPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PurchaseListDAO purchaseListDAO = new PurchaseListDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//임시 결제중인 관광 상품을 결제하는 것
		//TRVPAY를 참으로,TRVAPPROVE는 여전히 거짓임.(관리자가 승인하지 않음)
		
		request.setCharacterEncoding("UTF-8");
		
		int trvidx = Integer.parseInt(request.getParameter("trvidx"));
		int useridx = Integer.parseInt(request.getParameter("useridx"));
		//결제중인 상품 결제 완료.
		purchaseListDAO.temppay_to_pay(trvidx,useridx);

		//temp_paylist.jsp(임시 결제 리스트)로 리다이렉트 - 결제한 건 승인 대기로 뜸
		RequestDispatcher rd = request.getRequestDispatcher("userMain");
		rd.forward(request, response);
	}

}
