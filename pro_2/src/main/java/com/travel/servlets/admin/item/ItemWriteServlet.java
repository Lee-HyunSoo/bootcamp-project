package com.travel.servlets.admin.item;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ItemDAO;
import com.travel.vo.ItemVO;

@WebServlet("/itemWrite")
public class ItemWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ItemDAO itemDAO = new ItemDAO();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ItemVO item = new ItemVO();
		item.setTrv_name(request.getParameter("trvname"));
		item.setTrv_depart(request.getParameter("trvdepart"));
		item.setTrv_dest(request.getParameter("trvdest"));
		item.setTrv_price(Integer.parseInt(request.getParameter("trvprice")));
		item.setTrv_tcnt(Integer.parseInt(request.getParameter("trvtcnt")));
		item.setTrv_ccnt(Integer.parseInt(request.getParameter("trvccnt")));
		itemDAO.insertNewitem(item, request.getParameter("trvdepdate"), request.getParameter("trvdestdate"));
		
		RequestDispatcher rd = request.getRequestDispatcher("itemListadmin");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
		
	}

}
