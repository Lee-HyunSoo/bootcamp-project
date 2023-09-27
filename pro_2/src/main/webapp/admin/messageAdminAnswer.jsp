<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.travel.vo.*"%>

<%
AdminVO admin = (AdminVO) session.getAttribute("admin");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 - 글쓰기</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
	<c:import url="adminHeader.jsp"></c:import>
	<br />
	<br />
	<form action="adminMsgAnswer" method="POST">
		제목: <input type="text" placeholder="제목" name="msgTitle" /><br />
		문의자: <input type="text" value="${param.msgName}" name="userName"readonly /> 
		문의자 ID: <input type="text" value="${param.msgSender}"name="msgReceiver" readonly />
		문의일: <input type="text" value="${param.msgAskDate}" name="msgAskDate" readonly />
		작성자: <input type="text" value="${admin.getAdminName() }" name="msgName" readonly /><br />
		<input type="hidden" value="${admin.getAdminId()}" name="msgSender" />
		<input type="hidden" value="${param.msgIDX}" name="msgIDX" /> 답변내용:
		<textarea cols="100" rows="10" placeholder="문의답변내용" name="msgContent"></textarea>
		<br /> <input type="reset" class="btn" value="초기화" /> 
		       <input type="submit" class="btn btn--primary sign-up" value="보내기" />
	</form>
	<c:import url="adminFooter.jsp"></c:import>
</body>
</html>