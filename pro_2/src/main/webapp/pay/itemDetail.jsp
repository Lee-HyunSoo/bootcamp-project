<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.travel.vo.*,com.travel.dao.*, com.travel.db.*" %>

<%
ItemVO item = (ItemVO)request.getAttribute("item2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 상품 상세조회</title>
<script type ="text/javascript">
	function paylist(){
		document.location = "payList"
	}

</script>
<c:import url="../css.jsp"></c:import>
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<table border="1" summary="상품 상세보기">
	   <caption>결제 완료</caption>
	   
	   <thead>
			<tr>
				<th>  </th>
				<th><div style="width: 500px;">Value</div></th>
			</tr>
		</thead>
			<tbody>
			    <tr>
					<td>상품번호</td>
		            <td><%=item.getTrv_idx() %></td>
		   		</tr>
				<tr>
					<td>상품명</td>
		            <td><%=item.getTrv_name() %></td>
		   		</tr>
		   		<tr>
					<td>여행 출발지</td>
		            <td><%=item.getTrv_depart() %></td>
		   		</tr>
		   		<tr>
					<td>여행지</td>
		            <td><%=item.getTrv_dest() %></td>
		   		</tr>
		   		<tr>
					<td>여행 가격</td>
		            <td><%=item.getTrv_price() %></td>
		   		</tr>
		   		<tr>
					<td>신청 가능 총 인원</td>
		            <td><%=item.getTrv_tcnt() %></td>
		   		</tr>
		   		<tr>
					<td>신청 현재 인원</td>
		            <td><%=item.getTrv_ccnt() %></td>
		   		</tr>
		   		<tr>
					<td>여행 출발 시각</td>
		            <td><%=item.getTrv_deptdate() %></td>
		   		</tr>
		   		<tr>
					<td>여행 도착 시각</td>
		            <td><%=item.getTrv_destdate() %></td>
		   		</tr>
	  	    </tbody>
	   </table>

       <form action="refundServlet" method="POST">
      	<input type="hidden" name="trvidx" value="<%=item.getTrv_idx() %>" >
      	<input type="hidden" name="trvprice" value="<%=item.getTrv_price() %>" >
      	<input type="submit" class="btn btn--primary sign-up" value="환불">
      </form>
      <input type="button" class="btn" value="목록" onclick="paylist()" >
	<c:import url="../footer.jsp"></c:import>
</body>
</html>