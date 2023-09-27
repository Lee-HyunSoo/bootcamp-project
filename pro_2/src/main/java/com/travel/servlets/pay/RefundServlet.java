package com.travel.servlets.pay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travel.dao.PurchaseListDAO;
import com.travel.db.MySQLConnector;
import com.travel.vo.UserVO;

// 결제 완료 된 관광 상품 환불 처리 - purchase 테이블에서 삭제
//travel_agency 테이블의 check_value 변수를 업데이트함.(check_value = checkvalue - trvprice)
//해당 관광 상품의 현재 인원을 1명 감소시킨다.
@WebServlet("/refundServlet")
public class RefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PurchaseListDAO purchaseListDAO = new PurchaseListDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int trvidx = Integer.parseInt(request.getParameter("trvidx"));
		
		HttpSession httpSession = request.getSession();
		UserVO user = (UserVO) httpSession.getAttribute("user");
		
		purchaseListDAO.delete_pay(trvidx, user.getUserIdx());
		
		//paylist.jsp(결제완료함)로 리다이렉트
		response.sendRedirect("payList");
	}

}
