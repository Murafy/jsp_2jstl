<%@page import="vo.Customer"%>
<%@page import="dao.TblCustomerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2_EL.jsp</title>
</head>
<body>
	<h2>EL 은 Expression Language 의 약어. 해석하면 표현언어</h2>
	<p>자바 객체에 접근 할 때 매우 유용하게 사용됨. 기호</p>
	<%
		TblCustomerDao dao = TblCustomerDao.getInstance();
		Customer vo = dao.selectByPk("SYS");
	 	application.setAttribute("vo", vo); 
	%>
<!-- 이전 방식  -->
<%-- 	<ul>
		<li>아이디 : <%= vo.getCustomID() %></li>
		<li>성명 : <%= vo.getName() %></li>
		<li>이메일 : <%= vo.getEmail() %></li>
		<li>나이 : <%= vo.getAge() %></li>
	</ul> --%>
	<h3>EL로 바꾸기</h3>
	<ul>
		<li>아이디 : <c:out value="${vo.customID}"/> </li>
		<li>성명 : <c:out value = "${vo.name }"/> </li>
		<li>이메일 : <c:out value = "${vo.email }"/> </li>
		<li>나이 : <c:out value = "${vo.age }"/> </li>
	
	</ul>
	
	
</body>
</html>