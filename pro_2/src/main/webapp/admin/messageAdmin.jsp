<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.travel.vo.*"%>

<!-- LoadMSGServlet에서 넘겨주는 값 : request.setAttribute("msgs", msgs); -->
<!-- LoadMSGServlet에서 넘겨주는 값 : request.setAttribute("admins", admins); -->
<%
	ArrayList<MsgVO> msgs = (ArrayList<MsgVO>) request.getAttribute("msgs");
	AdminVO admin = (AdminVO) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 - 목록</title>
<c:import url="../css.jsp"></c:import>
</head>
<body>
	<c:import url="adminHeader.jsp"></c:import>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>응답</th>
				<th>제목</th>
				<th>날짜</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="msg" items="${ msgs }">
				<tr>
					<td>${ msg.getMsgIDX() }</td>
					<td>
						<!-- 				응답한 경우 응답이라고 뜨고 그렇지 않은 경우 빨간 글씨로 미응답이라고 뜸 --> <c:choose>
							<c:when test="${ msg.isMsgAdminAnswer() == 'false' }">
								<label style="font-weight: bold; color: RED;">[미응답]</label>
							</c:when>
							<c:otherwise>
								<label style="font-weight: bold;">[응답]</label>
							</c:otherwise>
						</c:choose>
					</td>
					<!-- a태그를 통해 adminMsgDetailServlet으로 이동 -->
					<td><a href="adminMsgDetail?msgIDX=${ msg.getMsgIDX() }">
							<!-- 				admin리스트의 아이디와 일치하는 것 && 유저가 아직 읽지않은것 => 읽지않은메세지 라고 노출한다. -->
							<%-- 				<c:forEach var="admin" items="${ admins }" > --%> <label
							style="font-weight: bold; color: RED;"> <c:if
									test="${ msg.isMsgAdminIsRead() == 'false' &&  admin.getAdminId().equals(msg.getMsgReceiver()) }">
							[읽지않은메세지] 
						</c:if>
						</label> <%-- 				</c:forEach> --%> ${ msg.getMsgTitle() }
					</a></td>
					<!-- 				admin이 답변한 경우에는 날짜에 answerdate를 노출하고, 그렇지 않은 경우(유저가 작성한 글의 경우) askdate를 노출 -->
					<c:choose>
						<c:when test='${ admin.getAdminId().equals(msg.getMsgSender()) }'>
							<td>${ msg.getMsgAnswerDate() }</td>
						</c:when>
						<c:when
							test="${not admin.getAdminId().equals(msg.getMsgSender())}">
							<td>${ msg.getMsgAskDate() }</td>
						</c:when>
					</c:choose>

					<td>${ msg.getMsgName() }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<input type="button" class="btn" value="새로고침" onclick="location.href = 'loadAdminMsg'" />
	<c:import url="adminFooter.jsp"></c:import>
</body>
</html>