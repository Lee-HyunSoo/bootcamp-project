package com.travel.servlets.pay;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.travel.dao.*;
import com.travel.db.*;
import com.travel.vo.ItemVO;

@WebServlet("/tempItemDetailServlet")
public class TempItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PurchaseListDAO purchaseListDAO = new PurchaseListDAO();
	private ItemVO item = new ItemVO();
    public TempItemDetailServlet() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* ItemDAO item = new ItemDAO();
		MySQLConnector dbclass = new MySQLConnector();
		dbclass.connectMySQL();
		int trv_idx = Integer.parseInt(request.getParameter("real_num"));
		item = dbclass.get_item(trv_idx); */
		
		int trv_idx = Integer.parseInt(request.getParameter("num"));
		item = purchaseListDAO.get_item(trv_idx);
		
		request.setAttribute("item", item);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pay/temp_itemDetail.jsp");
		requestDispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}