<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8"/>
		<title>Ajax Test01</title>
		<script type="text/javascript" language="javascript" 
		     src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script>
			$(function(){
				$("#seq").on("keyup", function(){
					$.ajax({
						url: "../ajax02/search2_01.do",
						type: "GET",
						data: {seq: $("#seq").val()},
						success: function(data){
							if(!data){
								alert("존재하지않는 SEQ");
								return false;
							}
							var html = "";
							html += "<form id='ajax'>";
							html += "번호 <input name='seq' value='"+data.seq+"'>";
							html += "이름 <input name='name' value='"+data.name+"'>";
							html += "주소 <input name='addr' value='"+data.addr+"'>";
							html += "날짜 <input name='rdate' value='"+data.rdate+"'>";
							html += "</form>";
							$("#name").val("");
							$("#container").html(html);
						}
					});
				});
			});
		</script>
	</head>
	<body>
	    <center>
	    <h2>(방법1) response객체에 JSON문자열 담기</h2>
	    
	    번호: <input type="text" name="seq" id="seq"/>
	    <input type="button" value="번호 검색" id="searchOk01"/>
	    <br/>
	    
	    이름: <input type="text" name="name" id="name"/>
	    <input type="button" value="이름 검색" id="searchOk02"/>
	 
        <br/><br/>
		<div id="container"></div>
		<br/><br/>
		
		<a href="../">인덱스</a><br/><br/>
		</center>
	</body>
</html>