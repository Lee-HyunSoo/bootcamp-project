<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.travel.vo.*"%>

<%
UserVO user = (UserVO) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 - 글쓰기</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
	<c:import url="../header.jsp"></c:import>
<br />
<br />
	<form action="msgAsk" method="POST">
		제목: <input type="text" placeholder="제목" name = "msgTitle" /><br />
		작성자: <input type="text" value="${user.getUserName()}" name="msgName" readonly /><br />
		<input type="hidden" value="${user.getUserId()}" name="msgSender" />
		문의내용: <textarea cols="100" rows="10" placeholder="문의내용" name = "msgContent" ></textarea><br />
		<input type="reset" class="btn" value="초기화" />
		<input type="submit" class="btn btn--primary sign-up" value="보내기" /> 
	</form>
		<c:import url="../footer.jsp"></c:import>
</body>
</html>