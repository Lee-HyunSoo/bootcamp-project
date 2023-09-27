<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류가 발생했을 때 이동한 페이지. 예외처리</title>
</head>
<body>
	오류 발생 : <%= exception.toString() %>
</body>
</html>