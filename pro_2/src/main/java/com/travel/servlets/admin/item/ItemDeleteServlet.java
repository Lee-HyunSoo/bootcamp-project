package com.travel.servlets.admin.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ItemDAO;


@WebServlet("/itemDelete")
public class ItemDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ItemDAO itemDAO = new ItemDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int trvidx = Integer.parseInt(request.getParameter("trvidx"));
        itemDAO.itemdelete(trvidx); // MySQLConnector의 itemDelete() 메서드 호출
        
        
        RequestDispatcher rd = request.getRequestDispatcher("itemListadmin");
		rd.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
