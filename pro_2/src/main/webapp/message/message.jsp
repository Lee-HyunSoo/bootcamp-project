<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.travel.vo.*"%>

<!-- LoadMSGServlet에서 넘겨주는 값 : request.setAttribute("msgs", msgs); -->
<!-- LoadMSGServlet에서 넘겨주는 값 : request.setAttribute("admins", admins); -->
<%
UserVO user = (UserVO) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 - 목록</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<table>
		<th>번호</th>
		<th>제목</th>
		<th>날짜</th>
		<th>작성자</th>


		<c:forEach var="msg" items="${ msgs }" >
			<tr>
				<td>${ msg.getMsgIDX() }</td>
				<td><a href="msgDetail?msgIDX=${ msg.getMsgIDX() }">
<!-- 				메세지 발송인지 admin리스트의 아이디와 일치하는 것 && 유저가 아직 읽지않은것 => 읽지않은메세지라고 노출한다. -->
				<c:if test='${ msg.isMsgUserIsRead() == "false"}'>
					<label style="font-weight: bold; color: RED;">[읽지않은메세지] </label>
				</c:if>

				${ msg.getMsgTitle() }</a></td>
<!-- 				admin이 답변한 경우에는 날짜에 answerdate를 노출하고, 그렇지 않은 경우(본인이 작성한 글의 경우) askdate를 노출 -->
				<c:choose>
						<c:when test='${ admin.getAdminId().equals(msg.getMsgSender()) }'>
							<td>${ msg.getMsgAnswerDate() }</td>
						</c:when>
						<c:otherwise>
							<td>${ msg.getMsgAskDate() }</td>
						</c:otherwise>
				</c:choose>
				<td>${ msg.getMsgName() }</td>
			</tr>
	</c:forEach>
	</table>

	<input type="button" class="btn" value="문의하기" onclick="location.href='msgToAsk'" />
	<c:import url="../footer.jsp"></c:import>
</body>
</html>