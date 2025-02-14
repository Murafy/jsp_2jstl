<%@page import="vo.UserAccountVO"%>
<%@page import="dao.UserAccountDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>5_loginProc.jsp</title>
</head>
<body>
<%
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	
	// SQL 실행을 위해 UserAccountDao 객체 가져오기 (싱글톤 패턴)
	UserAccountDao dao = UserAccountDao.getInstance();
	
	//로그인 SQL 실행을 위한 메서드 호출
	UserAccountVO vo = dao.selectForLogin(userid,password);
	
	if(vo != null){
		session.setAttribute("user", vo); // 세션에 필요한 데이터 저장. - 로그인 사용자 정보
		response.sendRedirect("/2JSTL/");
	}else{
		response.sendRedirect("5_login.jsp?fail=y");
		// 'fail' 파라미터에 'y' 값을 전달하면서 5_login.jsp 페이지로 리다이렉트
	
	}
%>
</body>
</html>
