package com.travel.servlets.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ItemDAO;
import com.travel.vo.ItemVO;



@WebServlet("/itemDetailuser")
public class ItemDetailUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ItemDAO itemDAO = new ItemDAO();
	private ItemVO item = new ItemVO();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int trvidx = Integer.parseInt(request.getParameter("trvidx"));
    
    item = itemDAO.itemDetail(trvidx);
   
    request.setAttribute("item", item);
    
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("item/itemDetailuser.jsp");
	requestDispatcher.forward(request, response);
}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
