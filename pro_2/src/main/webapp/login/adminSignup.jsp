<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminSignup</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
<c:import url="/admin/adminHeader.jsp"></c:import>
	<div style="margin: auto; width: 220px;">
		<form action="adminSignup" method="post" style="margin-top: 20px;">
			<input class="btn" type="text" name="id" maxlength="20" required="required" autofocus="autofocus" placeholder="아이디 입력"> <br><br/>
			<input class="btn" type="password" name = "pw" maxlength="20" required="required" placeholder="비밀번호 입력"> <br><br/>
			<input class="btn" type="text" name = "name" maxlength="20" required="required" placeholder="이름 입력"> <br/><br>
			<input class="btn" type="email" name = "email" maxlength="20" required="required" placeholder="이메일 입력"> <br/><br>
			<input class="btn" type="tel" name = "tel" maxlength="20" required="required" placeholder="전화번호 입력"> <br/><br>
			<input type="submit" class="btn" value="회원가입">
		</form>
	</div>
	<c:import url="/admin/adminFooter.jsp"></c:import>
</body>
</html>