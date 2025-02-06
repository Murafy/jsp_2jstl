<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!--  html 출력 없이 자바코드만 실행할 때에는 html 관련 태그 없어도 됨 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.invalidate(); // 기존 세션 소멸
	response.sendRedirect("/2JSTL/"); // 새로운 요청을 보내며 리다이렉트. 새로운 세션 ID가 발급됨
  									  // 절대경로 ("/1start/")는 도메인 루트에서 시작하는 경로를 의미함
  									 // 상대경로를 사용하려면 슬래시 없이 경로를 작성해야 함
    								// 파일 확장자를 생략한 이유: 서버 설정에 따라 기본 매핑이 적용되어 확장자가 자동 처리될 수 있음

%>

</body>
</html>