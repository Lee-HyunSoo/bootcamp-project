<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.travel.vo.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
  <script type="text/javascript">
   function itemlist(){
	   document.location="itemListadmin";
   }
  </script>
  <c:import url="../css.jsp"></c:import>
</head>
<body>
    <c:import url="adminHeader.jsp"></c:import>
     <form action="itemModify" method="post">
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
		            <td><input type="text" name="trvname" value='${item.trv_name}'></td>
		   		</tr>
		   		<tr>
					<td>여행 출발지</td>
		            <td><input type="text" name="trvdest" value="${item.trv_depart}"></td>
		   		</tr>
		   		<tr>
					<td>여행 도착지</td>
		            <td><input type="text" name="trvdepart" value="${item.trv_dest}"></td>
		   		</tr>
		   		<tr>
					<td>여행 가격</td>
		            <td><input type="number" name="trvprice" value="${item.trv_price}"></td>
		   		</tr>
		   		<tr>
					<td>여행 출발 시각</td>
		            <td><input type="datetime-local" name="trvdepdate" value="${item.trv_deptdate}"></td>
		   		</tr>
		   		<tr>
					<td>여행 도착 시각</td>
		            <td><input type="datetime-local" name="trvdestdate" value="${item.trv_destdate}"></td>
		   		</tr>
		   		<tr>
					<td>신청 가능 총 인원</td>
		            <td><input type="number" name="trvtcnt" value="${item.trv_tcnt}"></td>
		   		</tr>
              </tbody>
	    </table>
        <input type="hidden" name="trvidx" value="${item.trv_idx}">
        <input type="submit" class="btn btn--primary sign-up" value="저장">
     </form>
     <input type="button" class="btn" value="목록" onclick="itemlist()">
     <c:import url="adminFooter.jsp"></c:import>
</body>
</html>