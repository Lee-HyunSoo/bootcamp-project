package com.travel.servlets.item;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ItemDAO;
import com.travel.vo.ItemVO;


@WebServlet("/itemListuser")
public class ItemListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ItemDAO itemDAO = new ItemDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ArrayList<ItemVO> items= itemDAO.sellectItemuser();
		for(ItemVO i:items)
			System.out.println(i);
		
		request.setAttribute("items", items);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("item/itemListuser.jsp");
		requestDispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
