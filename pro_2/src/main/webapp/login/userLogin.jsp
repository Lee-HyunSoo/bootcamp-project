<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
<c:import url="../header.jsp"></c:import>
	<div style="margin: auto; width: 220px;">
		<form action="userLogin" method="post">
			<br/><br/>
			<input class="btn" type="text" name="id" maxlength="20" required="required" autofocus="autofocus" placeholder="아이디 입력"> <br><br/>		
			<input class="btn" type="password" name="pw" maxlength="20" required="required" placeholder="비밀번호 입력"> <br/><br>
			<input type="submit" class="btn" value="로그인">
		</form>
	</div>
<c:import url="../footer.jsp"></c:import>
</body>
</html>