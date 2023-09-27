package com.travel.servlets.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ItemDAO;

@WebServlet("/itemPay")
public class ItemPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ItemDAO itemDAO = new ItemDAO();

    public ItemPayServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int trvidx = Integer.parseInt(request.getParameter("trvidx"));
		/** 로그인이 될때 **/
//		HttpSession httpSession = request.getSession();
//		UserDAO user = (UserDAO) httpSession.getAttribute("user");
//		int useridx = user.getUseridx();
		
		/** 로그인 기능이 없어서 임시 지정 idx**/
		int useridx = 1;
		
		itemDAO.itempay(trvidx, useridx);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("itemListuser");
		requestDispatcher.forward(request, response);
	}
	
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
