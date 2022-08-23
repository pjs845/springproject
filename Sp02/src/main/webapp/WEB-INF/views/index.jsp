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
	<a href="/file/list.do">파일리스트</a><br/><br/>
	<a href="ajax/test01.do">ajax01</a>&nbsp;&nbsp;&nbsp;
	<a href="ajax/test02.do">ajax02</a>&nbsp;&nbsp;&nbsp;
	<a href="ajax/test03.do">ajax03</a>&nbsp;&nbsp;&nbsp;
	<a href="ajax/test04.do">ajax04</a><br/><br/>
	<br/><br/>
	<a href="ajax2/test01.do">ajax2-01</a>&nbsp;&nbsp;&nbsp;
	<a href="ajax2/test02.do">ajax2-02</a>&nbsp;&nbsp;&nbsp;
	<a href="ajax2/test03.do">ajax2-03</a>&nbsp;&nbsp;&nbsp;
	<a href="ajax2/test04.do">ajax2-04</a><br/><br/>
</body>
</html>
