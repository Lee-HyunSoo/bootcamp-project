package com.travel.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.ApproveDAO;
import com.travel.vo.ApproveVO;

@WebServlet("/adminApprove")
public class AdminApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ApproveDAO approveDAO = new ApproveDAO();
	private List<ApproveVO> approves = new ArrayList<ApproveVO>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		approves = approveDAO.selectApproveDAO();
		
		request.setAttribute("approves", approves);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/adminApprove.jsp");
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		approveDAO.updateApprovePurchase(Integer.parseInt(request.getParameter("trvIdx")), Integer.parseInt(request.getParameter("userIdx")));
		approveDAO.updateApproveItem(Integer.parseInt(request.getParameter("trvIdx")));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter outs = response.getWriter();
		outs.println("<script>alert('승인 되었습니다.'); location.href = 'adminApprove';</script>");
		outs.flush();
	}

}
