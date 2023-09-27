<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.travel.vo.*,com.travel.dao.*, com.travel.db.*" %>

<%
	PurchaseListDAO purchaseListDAO = new PurchaseListDAO();
	ArrayList<PurchaseListVO> items = (ArrayList<PurchaseListVO>) request.getAttribute("temp_items");
	UserVO user = (UserVO) request.getAttribute("user");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>임시 결제함</title>
<style type="text/css">
* {
	font-size: 9pt;
}

p {
	width: 600px;
	text-align: right;
}

table thead tr th {
	background-color: gray;
}
</style>
<c:import url="../css.jsp"></c:import>
</head>
<body>
<c:import url="../header.jsp"></c:import>
	<table border="1" summary="임시결제함">
		<caption>임시결제함</caption>
		
		<thead>
			<tr>
				<th>상품번호</th>
				<th>이름</th>
				<th>출발지</th>
				<th>목적지</th>
				<th>가격</th>
				<th>정원</th>
				<th>현재인원</th>
				<th>출발일</th>
				<th>도착일</th>

			</tr>
		</thead>
		<tbody>
			<%
			if (items.size() == 0) {
			%>
			<tr>
				<td align="center" colspan="5">결제 중인 상품이 없습니다.</td>
			</tr>
			<%
			} else {
			%>
			<%
				for (PurchaseListVO item : items) {
				%>
			<% if (purchaseListDAO.get_TRVPAY_from_itemidx(item, user.getUserIdx())) { %>
			<tr>
				<td><%= item.getTrv_idx() %></td>
				<td><%= item.getTrv_name() %></td>
				<td><%= item.getTrv_depart() %></td>
				<td><%= item.getTrv_dest() %></td>
				<td><%= item.getTrv_price() %></td>
				<td><%= item.getTrv_tcnt() %></td>
				<td><%= item.getTrv_ccnt() %></td>
				<td><%= item.getTrv_deptdate() %></td>
				<td><%= item.getTrv_destdate() %></td>

			</tr>
			<% } else { %>
			<tr>
				<td><%= item.getTrv_idx() %></td>
				<td><a
					href="tempItemDetailServlet?num=<%= item.getTrv_idx() %>"><%= item.getTrv_name() %></a></td>
				<td align="center"><%= item.getTrv_depart() %></td>
				<td align="center"><%= item.getTrv_dest() %></td>
				<td align="center"><%= item.getTrv_price() %></td>
				<td align="center"><%= item.getTrv_tcnt() %></td>
				<td align="center"><%= item.getTrv_ccnt() %></td>
				<td align="center"><%= item.getTrv_deptdate() %></td>
				<td align="center"><%= item.getTrv_destdate() %></td>
			</tr>
			<% } %>
			<% } %>

			<%}%>
		</tbody>
		<tfoot>
			<tr>
				<td align="center" colspan="5">1</td>
			</tr>
		</tfoot>
	</table>
<c:import url="../footer.jsp"></c:import>
</body>
</html>