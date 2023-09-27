<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.vo.*,com.travel.db.*" %>

<%
	ArrayList<PurchaseListVO> items = (ArrayList<PurchaseListVO>)request.getAttribute("items");
	UserVO user = (UserVO) session.getAttribute("user");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>나의 결제함</title>
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
	<table border="1" summary="나의결제함">
		<caption>나의 결제함</caption>
		<colgroup>
			<col width="50" />
			<col width="100" /> 
			<col width="100" /> 
			<col width="100" /> 
			<col width="80" />
			<col width="70" />
			<col width="70" />
			<col width="300" />
			<col width="300" />
		</colgroup>
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
				<tr>
						<td align="center"><%=item.getTrv_idx()%></td>
						<td><a href="itemDetailServlet?real_num=<%=item.getTrv_idx()%>"><%=item.getTrv_name()%></a></td>
						<td align="center"><%=item.getTrv_depart()%></td>
						<td align="center"><%=item.getTrv_dest()%></td>
						<td align="center"><%=item.getTrv_price()%></td>
						<td align="center"><%=item.getTrv_tcnt()%></td>
						<td align="center"><%=item.getTrv_ccnt()%></td>
						<td align="center"><%=item.getTrv_deptdate()%></td>
						<td align="center"><%=item.getTrv_destdate()%></td>
					</tr>
				<%}%>
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