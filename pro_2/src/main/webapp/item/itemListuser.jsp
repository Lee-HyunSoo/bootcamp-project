<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.vo.*" %>
<%
ArrayList<ItemVO> items = (ArrayList<ItemVO>)request.getAttribute("items");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
<c:import url="../header.jsp"></c:import>
   <form name="Item" action="itemListuser">
   
   <table border="1" summary="상품 목록">
		<caption>상품 목록</caption>
	
		<thead>
			<tr>
				<th>상품번호</th>
				<th>상품명</th>
				<th>목적지</th>
				<th>남은자리</th>
				<th>출발일</th>
			</tr>
		</thead>
		<tbody>
     	 <%
    	  if (items == null) {
     	 %>
      		<tr>
				<td align="center" colspan="5">현재 등록된 상품이 없습니다.</td>
			</tr>
     	 <%
    	  }else{
     	 %>
         <%
         for(ItemVO item : items) {
         %>
         	<tr>
               <td><%=item.getTrv_idx() %></td>
               <td><a href="itemDetailuser?trvidx=<%=item.getTrv_idx()%>"><%=item.getTrv_name() %></a></td>
               <td><%=item.getTrv_dest() %></td>
               <td><% if(item.getTrv_tcnt() - item.getTrv_ccnt()<=0) {%> 
               마감 
 			   <%} else{%>
 			   <%=item.getTrv_tcnt() - item.getTrv_ccnt() %>
 			   <%} %></td>
               <td><%=item.getTrv_deptdate() %></td>
    		</tr>
          <%} %>
       <%}  %>   
       
       </table>      
   </form>
<c:import url="../footer.jsp"></c:import>
</body>
</html>