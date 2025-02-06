<%@page import="vo.UserAccountVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>HOME</h3>
	<hr />
	<%
	//Object 리턴을 UserAccountVO 타입으로 변경 (캐스팅)
	UserAccountVO vo = (UserAccountVO) session.getAttribute("user");
	%>
	<h3>2JSTJ 프로젝트의 메뉴</h3>
	<ul>
		<li><a href="/1start/4_register.jsp">회원 가입</a></li>
		<!--로그인일 경우에만-->
		<li><a href="5_login.jsp">로그인</a></li>
		<!--로그인이 아닐때에만-->
		<li><a href="5_logout.jsp">로그아웃</a></li>
		<!-- 로그인 상태일때만 나오는 메뉴 -->
	</ul>
	<%
	if (vo != null) { // 로그인 상태가 아니면 vo가 NULL 임으로 getXXX 메서드 실행시 오류 고로 NULL이 아닐때만 실행 
	%>
	<h2>로그인 사용자</h2>
	<ul>
		<li>로그인 사용자 아이디 : <%=vo.getUserid()%>
		</li>
		<li>로그인 사용자 아이디 : <%=vo.getUsername()%>
		</li>
		<li>로그인 사용자 아이디 : <%=vo.getEmail()%>
		</li>
	</ul>

	<%
	}
	%>
</body>
</html>