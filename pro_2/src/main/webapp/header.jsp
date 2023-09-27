<%@page import="com.travel.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
UserVO user = (UserVO)session.getAttribute("user");
%>

<!-- 전체 구조의 header 부분 -->
<header class="section">
	<!-- header 내부의 좌측, 우측 요소들을 관리하기 위해 -->
	<div class="inner clearfix">
		<!-- 좌측 메뉴그룹 -->
		<div class="menu-group float--left">
			<!-- 2. 좌측 메뉴 -->
			<ul class="main-menu">
				
				<li><a href="userMain"><img src="imgs/logo__github.png" width="105px" height="105px"/></a></li>
				<li style="margin-top:47px; "><a href="itemListuser">여행목록</a></li>
				<li style="margin-top:47px; "><a href="tempPayList">장바구니</a></li>
				<li style="margin-top:47px; "><a href="payList">결제완료함</a></li>
				<li style="margin-top:47px; "><a href="loadMsg">문의사항</a></li>
			</ul>
		</div>

		<!-- 우측 메뉴그룹 -->
		<div class="sign-group float--right">
			<!-- 1. 버튼 -->
			<div class="btn-group">
				<% if (session.getAttribute("user") == null) { %>
				<a href="userLogin" class="btn sign-in" style="margin-right: 15px;">로그인</a>
				<a href="userSignup" class="btn btn--primary sign-up">회원가입</a>
				<%} else { %>
				<button class="btn sign-in" disabled="disabled">
					<%=user.getUserName()%>님이 접속되었습니다.
				</button>
				<a href="login/userLogout.jsp" class="btn btn--primary sign-up">로그아웃</a>
				<%}%>
			</div>
		</div>
	</div>
</header>
