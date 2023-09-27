<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.travel.vo.*" %>
<%
ItemVO item = (ItemVO)request.getAttribute("item");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 보기</title>
  <script type="text/javascript">
      function itemmodify(trvidx) {
    	  document.location = "itemModify?trvidx=" + trvidx;
      }
      
      function itemlist(){
    	  document.location = "itemListadmin";
      }
      
      function itemDelete(trvidx) {
          if (confirm("정말로 삭제하시겠습니까?")) {
              document.location = "itemDelete?trvidx=" + trvidx;
          }
      function itempay() {
    	  document.location = "itemPay";
          }
      function itemadd() {
    	  document.location = "itemAdd";
          }          
              
      }
  
  </script>
  <c:import url="../css.jsp"></c:import>
</head>
<body>
	<c:import url="adminHeader.jsp"></c:import>
	<form action="itemDetailadmin">
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
					<td>상품명</td>
		            <td><%= item.getTrv_name() %></td>
		   		</tr>
		   		<tr>
					<td>여행 출발지</td>
		            <td><%= item.getTrv_depart() %></td>
		   		</tr>
		   		<tr>
					<td>여행 도착지</td>
		            <td><%= item.getTrv_dest() %></td>
		   		</tr>
		   		<tr>
					<td>여행 가격</td>
		            <td><%= item.getTrv_price() %></td>
		   		</tr>
		   		<tr>
					<td>신청 가능 총 인원</td>
		            <td><%= item.getTrv_tcnt() %></td>
		   		</tr>
		   		<tr>
					<td>신청 현재 인원</td>
		            <td><%= item.getTrv_ccnt()  %></td>
		   		</tr>
		   		<tr>
					<td>여행 출발 시각</td>
		            <td><%= item.getTrv_deptdate() %></td>
		   		</tr>
		   		<tr>
					<td>여행 도착 시각</td>
		            <td><%= item.getTrv_destdate() %></td>
		   		</tr>
	  	    </tbody>
	   </table>
	</form>   
   
   <input type="button" class="btn btn--primary sign-up" value="목록" onclick="itemlist()" >
   <input type="button" class="btn" value="수정" onclick="itemmodify(<%=item.getTrv_idx()%>)" >
   <input type="button" class="btn btn--primary sign-up" value="삭제" onclick="itemDelete(<%=item.getTrv_idx()%>)" > <br />
 
 
 <!-- 사용자 기능 -->
<!--    <form action="itemPay"> -->
<!-- 	   <input type="submit" value="결제" onclick="itempay()"> -->
<%-- 	   <input type="hidden" name="trvidx" value="<%=item.getTrvidx() %>"> --%>
<!--    </form> -->
   
<!--    <form action="itemAdd"> -->
<!-- 	   <input type="submit" value="장바구니" onclick="itemadd()"> -->
<%-- 	   <input type="hidden" name="trvidx" value="<%=item.getTrvidx() %>"> --%>
   </form>
   	<c:import url="adminFooter.jsp"></c:import>
</body>
</html>