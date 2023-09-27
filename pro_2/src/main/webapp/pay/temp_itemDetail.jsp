<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.travel.vo.*,com.travel.dao.*, com.travel.db.*" %>
    
<%
    ItemVO item = new ItemVO();
	PurchaseListDAO purchaseListDAO = new PurchaseListDAO();
    int trv_idx = Integer.parseInt(request.getParameter("num"));
    item = purchaseListDAO.get_item(trv_idx);
    
    HttpSession httpSession = request.getSession();
    UserVO user = (UserVO) httpSession.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 전 상품 상세조회</title>
<script type ="text/javascript">
	function temppaylist(){
		document.location = "tempPayList"
	}
	
	function cancel(){
		document.location = "tempRefundServlet"
	}

</script>
<c:import url="../css.jsp"></c:import>
</head>
<body>
<c:import url="../header.jsp"></c:import>
	  <h2>상품 등록</h2>
      <form action="tempPaymentServlet" method="POST">
       	<input type="hidden" name="trvidx" value="<%=item.getTrv_idx() %>" >
       	<input type="hidden" name="useridx" value="<%=user.getUserIdx() %>" >
      <table border="1" summary="상품 상세보기">
	     <caption>상품 상세보기</caption>
	   
	     <thead>
			<tr>
				<th>  </th>
				<th><div style="width: 500px;">Value</div></th>
			</tr>
		  </thead>
		  <tbody>
				<tr>
					<td>상품 번호</td>
		            <td><%=item.getTrv_idx() %></td>
		   		</tr>
		   		<tr>
					<td>여행 제목</td>
		            <td><%=item.getTrv_name() %></td>
		   		</tr>
		   		<tr>
					<td>여행지</td>
		            <td><%=item.getTrv_dest() %></td>
		   		</tr>
		   		<tr>
					<td>출발 지역</td>
		            <td><%=item.getTrv_depart() %></td>
		   		</tr>
		   		<tr>
					<td>가격</td>
		            <td><%=item.getTrv_price() %></td>
		   		</tr>
		   		<tr>
					<td>출발 시간</td>
		            <td><%=item.getTrv_deptdate() %></td>
		   		</tr>
		   		<tr>
					<td>도착 시간</td>
		            <td><%=item.getTrv_destdate() %></td>
		   		</tr>
		   		<tr>
					<td>상품 총인원</td>
		            <td><%=item.getTrv_tcnt() %></td>
		   		</tr>
		   		<tr>
					<td>상품 현재 인원</td>
		            <td><%=item.getTrv_ccnt() %></td>
		   		</tr>
      	   </tbody>
	   </table>

        <input type="submit" class="btn btn--primary sign-up" value="결제" >
      </form>
      <form action="tempRefundServlet" method="POST">
      <input type="hidden" name="trvidx" value="<%=item.getTrv_idx() %>" >
      	<input type="submit" class="btn" value="취소">
      </form>
      <input type="button" class="btn btn--primary sign-up" value="목록" onclick="temppaylist()" >
<c:import url="../footer.jsp"></c:import>
</body>
</html>