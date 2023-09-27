<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 상품 등록</title>
  <script type="text/javascript">
     function itemlist(){
    	 document.location = "itemListadmin"
     }
  
  </script>
  <c:import url="../css.jsp"></c:import>
</head>
<body>
    <c:import url="adminHeader.jsp"></c:import>
      <form action="itemWrite" method="POST">
      <table border="1" summary="상품 상세보기">
	   <caption>상품 등록</caption>
	   
	   <thead>
			<tr>
				<th>  </th>
				<th><div style="width: 500px;">Value</div></th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>상품명</td>
		            <td><input type="text" name="trvname" required="required" /></td>
		   		</tr>
		   		<tr>
					<td>여행 출발지</td>
		            <td><input type="text" name="trvdepart" required="required" /></td>
		   		</tr>
		   		<tr>
					<td>여행지</td>
		            <td><input type="text" name="trvdest" required="required" /></td>
		   		</tr>
		   		<tr>
					<td>여행 가격</td>
		            <td><input type="number" name="trvprice" required="required" /></td>
		   		</tr>
		   		<tr>
					<td>여행 출발 시각</td>
		            <td><input type = "datetime-local" name="trvdepdate" required="required" /></td>
		   		</tr>
		   		<tr>
					<td>여행 도착 시각</td>
		            <td><input type = "datetime-local" name="trvdestdate" required="required" /></td>
		   		</tr>
		   		<tr>
					<td>신청 가능 총 인원</td>
		            <td><input type="number" name="trvtcnt" required="required" /></td>
		   		</tr>
		   		<tr>
					<td>현재 인원</td>
		            <td><input type="number" name="trvccnt" required="required" /></td>
		   		</tr>
              </tbody>
	    </table>
        
        <input type="submit" class="btn btn--primary sign-up" value="등록" >
      </form>
      <input type="button" class="btn" value="목록" onclick="itemlist()" >
      <c:import url="adminFooter.jsp"></c:import>
</body>
</html>