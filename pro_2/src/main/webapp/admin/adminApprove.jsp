<%@page import="com.travel.vo.ApproveVO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin approve</title>

<c:import url="../css.jsp"></c:import>
</head>

<body>
	<c:import url="adminHeader.jsp"></c:import>
	<section class="section section--feature">
		<table>
			<thead>
				<tr>
					<th>여행번호</th>
					<th>여행이름</th>
					<th>여행출발지</th>
					<th>여행도착지</th>
					<th>여행가격</th>
					<th>총 인원수</th>
					<th>현재 인원수</th>
					<th>출발시간</th>
					<th>도착시간</th>
					<th>신청자번호</th>
					<th>신청자이름</th>
					<th>승인</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="approve" items="${requestScope.approves}"
					varStatus="status">
					<tr>
						<td><c:out value="${approve.trvIdx}"></c:out></td>
						<td><c:out value="${approve.trvName}"></c:out></td>
						<td><c:out value="${approve.trvDepart}"></c:out></td>
						<td><c:out value="${approve.trvDest}"></c:out></td>
						<td><c:out value="${approve.trvPrice}"></c:out></td>
						<td><c:out value="${approve.trvTcnt}"></c:out></td>
						<td><c:out value="${approve.trvCcnt}"></c:out></td>
						<td><c:out value="${approve.trvDepDate}"></c:out></td>
						<td><c:out value="${approve.trvDestDate}"></c:out></td>
						<td><c:out value="${approve.userIdx}"></c:out></td>
						<td><c:out value="${approve.userName}"></c:out></td>
						<td>
							<form action="adminApprove" method="post">
								<input type="hidden" name="trvIdx" value="${approve.trvIdx}">
								<input type="hidden" name="userIdx" value="${approve.userIdx}">
								<input type="submit" value="승인">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<c:import url="adminFooter.jsp"></c:import>
</body>
</html>