<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 1번 내용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1_jstlTest.jsp</title>
</head>
<body>
	<h1>JSTL</h1>
	<P> 1. JSTL 을 사용하려면 TAGLIB 설정을 해야함 
		prefix 속성은 태그라이브러리 사용할 때, 태그 앞에 붙는 접두사
		c 접두사는 if, for 등 기본문법에 사용함
		<strong> uri 속성 </strong>은 c 접두어로 사용할 태그를 해석할 uri 주소임   
	</P>
	<H3>C: 으로 시작하는 CORE 태그 사용</H3>
	<!-- 1. 변수에 값 저장하기 -->
	<c:set var="age" value="23"/>
	<%-- 2. 변수값 출력하기. value 속성에 출력할 값 지정
			변수를 사용할 때에는 ${} EL 기호안에 작성 --%>
	<h4><c:out value="${age}"></c:out></h4>
	<!-- EL 기호에 작성한 변수가 없을때 오류발생안함 -->
	<h4><c:out value="${name}"></c:out></h4>
	
	<!-- 3. if -->
	<c:if test="${age>=20}">
		<div style="color:red">성인 입니다.</div>
	</c:if>
	<!-- else 형식이 없으므로 한번더 if  -->
	<c:if test="${age<20}">
		<div style="color:green">미자 입니다.</div>
	</c:if>
	
	<!-- 4. switch -->
	<h4>switch 연습</h4>
	<c:set var="age" value="25"/> <!-- age 변수값을 변경 -->
	<c:choose>
	<%-- 조건을 여러개 검사해야 할때 when 여러번 작성 가능 --%>
		<c:when test="${age<20 }">
			<div style="color:green">*미자* 입니다.</div>
		</c:when>
		<c:otherwise>
			<div style="color:red">*성인* 입니다.</div>
		</c:otherwise>
	</c:choose>
	<!-- 5. for -->
	<c:forEach begin="10" end="20" var="i">
		<h4>i = <c:out value="${i }"/> </h4>
	</c:forEach>
	
		<ul>
		<li>아이디 : <c:out value="${vo.customID}"/> </li>
		<li>성명 : <c:out value = "${vo.name }"/> </li>
		<li>이메일 : <c:out value = "${vo.email }"/> </li>
		<li>나이 : <c:out value = "${vo.age }"/> </li>
	
	</ul>
</body>
</html>