package com.travel.servlets.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travel.dao.ItemDAO;
import com.travel.vo.UserVO;



@WebServlet("/itemAdd")
public class ItemAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ItemDAO itemDAO = new ItemDAO();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int trvidx = Integer.parseInt(request.getParameter("trvidx"));
		HttpSession httpSession = request.getSession();
		UserVO user = (UserVO) httpSession.getAttribute("user");
		int useridx = user.getUserIdx();
		
		itemDAO.itemadd(trvidx, useridx);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("itemListuser");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
