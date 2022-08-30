<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Sp01 Index</title>
</head>
<body style="text-align:center">
	<h1>
		Sp01 for Spring MVC Controller
	</h1>
	
	<!--<P> Welcome Spring's world with Tomcat9 </P>  -->
	
	<p>
		<a href="test/">m01</a>
		<a href="test/base1">m02</a>
		<a href="test/base2">m03</a>
		<a href="test/form">Form</a>
	</p>
	<p>
		<a href="test/param1?name=홍길동">m04</a>
		<a href="test/param2?na=이순신&age=33">m05</a>
		<a href="test/param3?name=강감찬&age=45">m06</a>
	</p>
	<p>
		<a href="test/param4?names=홍길동&names=이순신&names=강감찬">m07</a>
		<a href="test/param5?names=유관순&names=안중근&names=신사임당">m08</a>
		<a href="test/param6?ns=유관순&ns=안중근&ns=신사임당">m09</a>
	</p>
	<p>
		<!-- 
		<a href="test/param7?list[0].name=이순신&list[0].age=40&list[1].name=홍길동&list[1].age=30">m10</a>
		[ -> %5B
		] -> %5D 
		 -->
		 <a href="test/param7?list%5B0%5D.name=이순신&list%5B0%5D.age=40&list%5B1%5D.name=홍길동&list%5B1%5D.age=30&dump=KOSMO">m10</a>
		 <a href="test/param8?name=강감찬&age=27&dump=장군&grade=3">m11</a>
		 <a href="test/param9?subject=크리스마스&cdate=2022/12/24 18:30:50">m12</a>
	</p>
</body>
</html>
