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
import com.travel.vo.PurchaseListVO;
import com.travel.vo.UserVO;


@WebServlet("/tempPayList")
public class TempPayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PurchaseListDAO purchaseListDAO = new PurchaseListDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ArrayList<PurchaseListVO> items = new ArrayList<PurchaseListVO>();
		//1. purchase에서 가져온 데이터를 임시결제함 리스트에 뿌리되 trvpay=F,tempapprove=F(결제전 여행 상품)들 리스트에 뿌린다.
		//1-1. trvpay = T,tempapprove=F => 결제 완료 했으나 관리자 승인 전인상태./이것도 리스트에 뿌리되 결제 로직은 없어야 한다.
		//2. 현재시간을 측정하여 출발시간이 지난 여행 상품은 제외해야한다.
		//3. 정s원과 현재인원을 비교하여 현재인원>T.O인 경우에는 리스트에서 제외해야한다.
		
		HttpSession httpSession = request.getSession();
		UserVO user = (UserVO) httpSession.getAttribute("user");
		items = purchaseListDAO.get_temp_purchaselist(user.getUserIdx());
		
		request.setAttribute("user", user);
		request.setAttribute("temp_items", items);
		
		//temp_playlist.jsp(임시결제함)로 리다이렉트
//		response.sendRedirect(request.getContextPath() + "/pay/temp_paylist.jsp");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("pay/temp_paylist.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
