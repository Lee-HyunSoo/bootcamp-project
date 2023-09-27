package com.travel.servlets.admin.item;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ItemDAO;
import com.travel.vo.ItemVO;


@WebServlet("/itemModify")
public class ItemModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ItemDAO itemDAO = new ItemDAO();

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
	    ItemVO item = itemDAO.doGet_itemmodify(Integer.parseInt(request.getParameter("trvidx")));
	    
	    request.setAttribute("item", item);
	
	    RequestDispatcher rd = request.getRequestDispatcher("admin/itemModify.jsp");
	    rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int trvidx = Integer.parseInt(request.getParameter("trvidx"));
		String trvname = request.getParameter("trvname");
		String trvdepart = request.getParameter("trvdepart");
		String trvdest = request.getParameter("trvdest");
		int trvprice = Integer.parseInt(request.getParameter("trvprice"));
		int trvtcnt = Integer.parseInt(request.getParameter("trvtcnt"));
		String[] trvdepdate = request.getParameter("trvdepdate").split("T");
		String[] trvdestdate = request.getParameter("trvdestdate").split("T");
		
		itemDAO.doPost_itemmodify(trvidx, trvname, trvdepart, trvdest, trvprice, trvtcnt, trvdepdate[0], trvdestdate[0]);
		
		response.sendRedirect("itemListadmin");
	}

}
