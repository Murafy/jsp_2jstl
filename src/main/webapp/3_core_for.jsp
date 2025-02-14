
<%@page import="java.net.URI"%>
<%@page import="vo.Product"%>
<%@page import="java.util.List"%>
<%@page import="dao.TblProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/table.css">
<title>3_core_for.jsp</title>
</head>
<h2>상품 카테고리</h2>
<!-- 3_parameter.jsp 의 forEach는 items 속성을 사용합니다.
	 자바의 향상 for문과 동작 방식이 같음
	 여기서 기존 for문의 index를 사용함 core 태그 속성 -->
<ul>
	<li><a href="3_Parameter.jsp?category=A1">국내산 과일</a></li>
	<li><a href="3_Parameter.jsp?category=A2">수입 과일</a></li>
	<li><a href="3_Parameter.jsp?category=B1">인스턴트 식품</a></li>
	<li><a href="3_Parameter.jsp?category=B2">선물세트</a></li>
	<li><a href="3_Parameter.jsp?category=C1">과자류</a></li>
</ul>
<body>
	<%
		String category = request.getParameter("category");
		//if (category == null) category = "A1";// 파라미터가 없는 url일때 ex:3_Parameter.jsp 
		// 파라미터가 없는 url 일 때, 전체 상품 출력하기
		TblProductDao dao = TblProductDao.getInstance();
		List<Product> list = dao.selectByCategory(category); // 카테고리값 변경 직접 문자열값 변경
		if(category == null)
			list = dao.selectAllProduct();
		else
			list = dao.selectByCategory(category);
		// 결과 리스트를 브라우저로 출력 --> table tag
		// dao 잘 실행되는지 테스트
		//out.print(list);
		pageContext.setAttribute("list", list);
	%>
	<h2>카테고리별 상품 목록</h2>
	<table>
	  <tr>
		<th>번호</th>
		<th>카테고리</th>
		<th>상품명</th>
		<th>상품코드</th>
		<th>가격</th>
	  </tr>	
	  
	 <!--  for (Product temp : list) -->
	 <!-- varStatus 속성에 변수 이름을 지정하면 index 값을 가져올 수 있음. -->
	<c:forEach begin="0" end="${list.size()}" var="i">
	<!-- i 번째 항목을 가져와서 (list.get(i)) temp 변수에 저장함 -->
	  <c:set var = "temp" value="${list[i]}"/>
	  <tr id="Product">
	  	<td style="width:100px">${i+1 }</td>
	  	<td>${temp.category }</td>
	  	<td>${temp.pname }</td> <!-- 왼쪽 정렬 -->
	  	<td>${temp.pcode }</td>
	  	<td>${temp.price }</td> <!-- 오른쪽 정렬 -->
	  </tr>
	</c:forEach>
	</table>
</body>
</html>