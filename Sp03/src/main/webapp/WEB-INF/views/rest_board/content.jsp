<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<style>
	table, th, td {
	   border: 1px solid black;
	   border-collapse: collapse;
	}
	th, td {
	   padding: 5px;
	}
	a { text-decoration:none }
</style>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(function(){
		var para = document.location.href.split("?");
		var seq = para[1].substring(para[1].lastIndexOf("=")+1)
		$.ajax({
			url: "read/"+seq+".json",
			type: "GET",
			success: function(data){
				if(!data){
                    alert("존재하지 않는 data");
                    return false;
                 }
				var html = "";
				$("#seq").append(data.seq);
				$("#writer").append(data.writer);
				$("#email").append(data.email);
				$("#subject").append(data.subject);
				$("#content").append(data.content);
				html += "<form id='del' action='delete/"+data.seq+"' method='GET'>"; 
				html += "</form>";		    	
		    	$("#form").append(html);
			
			}
		});
	});
</script>
<center id="form">
	<font color='gray' size='4' face='휴먼편지체'>
	<hr width='600' size='2' color='gray' noshade>
	<h3>Spring Board ( Spring5 + MyBatis )</h3>
	<font color='gray' size='4' face='휴먼편지체'>
	<a href='write.do'>글쓰기</a>
	</font>
	<hr width='600' size='2' color='gray' noshade>
	</font>
	
	<table border='2' width='600' align='center' noshade>
	<tr>
	<td width='20%' align='center'>No</td>
	<td id="seq"></td>
	</tr>
	<tr>
	<td width='20%' align='center'>Writer</td>
	<td id="writer"></td>
	</tr>
	<tr>
	<td align='center'>E-mail</td>
	<td id="email"></td>
	</tr>
	<tr>
	<td align='center'>Subject</td>
	<td id="subject"></td>
	</tr>
	<tr>
	<td align='center'>Contents</td>
	<td id="content"></td>
	</tr>
	</table>
	
	<hr width='600' size='2' color='gray' noshade>
	<font color='gray' size='4' face='휴먼편지체'>
	<a href='update.do?seq=${board.seq}'>수정</a>
	 &nbsp;&nbsp;| 
	<a href="javascript:$('#del').submit()" >삭제</a>
	 &nbsp;&nbsp;| 
	<a href='board'>목록</a>
	</font>
	<hr width='600' size='2' color='gray' noshade>
</center>
