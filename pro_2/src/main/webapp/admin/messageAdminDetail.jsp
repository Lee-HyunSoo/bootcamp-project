<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.travel.vo.*" %>

<!-- AdminMsgDetailServlet으로부터 전달받은 값: request.setAttribute("msgDetail", msgDetail); -->
<!-- AdminMsgDetailServlet으로부터 전달받은 값: request.setAttribute("admins", admins); -->

<%
MsgVO msgDetail = (MsgVO) request.getAttribute("msgDetail");
	AdminVO admin = (AdminVO) session.getAttribute("admin");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 - 상세보기</title>
<c:import url="../css.jsp"></c:import>
</head>
<script type="text/javascript">
	function messageAdminAnswer(){
		document.location = 'adminMsgDetailToAnswer?msgIDX=${ msgDetail.getMsgIDX() }&msgName=${ msgDetail.getMsgName() }&msgSender=${ msgDetail.getMsgSender() }&msgAskDate=${msgDetail.getMsgAskDate()}'
	}
	
</script>
<body>
	<c:import url="adminHeader.jsp"></c:import>
	<table>
		<tr>
			<td>제목:</td><td>${ msgDetail.getMsgTitle() }</td><td>  </td>
		</tr>
		<tr>
			<td>문의일:</td><td>${ msgDetail.getMsgAskDate() }</td><td>답변일:</td><td>${ msgDetail.getMsgAnswerDate() }</td>
		</tr>
		<tr>
			<td style="height: 100px;">문의내용</td>
			<td style="height: 100px;">${ msgDetail.getMsgContent() }</td><td>  </td>
		</tr>
	</table>
	
<!-- 	관리자id가 작성한 문의답변 글에는 문의답변 버튼 숨김 -->
	<c:if test="${ !admin.getAdminId().equals(msgDetail.getMsgSender()) }">
		<input type="button" class="btn" value="문의답변" onclick="messageAdminAnswer()" />	
	</c:if>
	
	<input type="button" class="btn btn--primary sign-up" value="목록" onclick="location.href = 'loadAdminMsg'">
	<c:import url="adminFooter.jsp"></c:import>
</body>
</html>