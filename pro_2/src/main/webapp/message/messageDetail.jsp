<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.travel.vo.*" %>

<!-- MsgDetailServlet으로 부터 전달받은 값 : request.setAttribute("msgDetail", msgDetail); -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 - 상세보기</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<table>
		<tr>
			<td>제목:</td><td><c:out value="${ msgDetail.getMsgTitle() }" /></td>
		</tr>
		<tr>
			<td>문의일:</td>
			<td><c:out value="${ msgDetail.getMsgAskDate() }" /></td>
			<td>답변일:</td><td><c:out value="${ msgDetail.getMsgAnswerDate() }" /></td>
		</tr>
		<tr>
			<td style="height: 100px;">문의내용</td>

			<td style="height: 100px;"><c:out value="${ msgDetail.getMsgContent() }" /></td>
		</tr>
	</table>
	
	<input type="button" class="btn" value="목록" onclick="location.href = 'loadMsg'">
	<c:import url="../footer.jsp"></c:import>
</body>
</html>