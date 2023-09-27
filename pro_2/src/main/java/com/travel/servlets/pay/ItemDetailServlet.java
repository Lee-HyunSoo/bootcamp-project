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
import com.travel.vo.ItemVO;

@WebServlet("/itemDetailServlet")
public class ItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PurchaseListDAO purchaseListDAO = new PurchaseListDAO();
	private ItemVO item = new ItemVO(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLConnector dbclass = new MySQLConnector();
		int trv_idx = Integer.parseInt(request.getParameter("real_num"));
		item = purchaseListDAO.get_item(trv_idx);
		
		request.setAttribute("item2", item);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("pay/itemDetail.jsp");
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
