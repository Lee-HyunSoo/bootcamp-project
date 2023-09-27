<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin logout</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
<c:import url="/admin/adminHeader.jsp"></c:import>
	<%session.invalidate();%>
	<script type="text/javascript">
		alert("로그아웃 되었습니다.")
		document.location = "../adminMain";
	</script>	
<c:import url="/admin/adminFooter.jsp"></c:import>
</body>
</html>