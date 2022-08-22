<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Sp01 Index</title>
</head>
<body style="text-align:center">
	<h1>
		Sp01 for Spring MVC Model 홈 한글
	</h1>
	
	<a href="/address/list.do">주소록</a><br/>
	<a href="/board/list.do?ps=${ps}&cp=1">게시판</a>
	<a href="/board2/list.do">게시판2</a><br/>
	<a href="/file/form.do">파일폼</a><br/>
	<a href="/file/list.do">파일리스트</a>
</body>
</html>
