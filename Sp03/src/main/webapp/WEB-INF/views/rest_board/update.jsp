<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title> Spring Board </title>
	<meta charset="utf-8">
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
						$("#seq").val(data.seq);
						$("#writer").val(data.writer);
						$("#email").val(data.email);
						$("#subject").val(data.subject);
						$("#content").val(data.content);
					
					}
				});
				$("#wr").focus();
				$("#btn").on("click", function(){
					let jsObj = {seq:$("#seq").val(), writer:$("#writer").val(), email:$("#email").val(), subject:$("#subject").val(), content:$("#content").val()};
					let jsonData = JSON.stringify(jsObj);
					$.ajax({
						url: "update.json", 
						method: "PUT",
						contentType: "application/json;charset=utf-8",
						data: jsonData,
						success: function(data){
							alert("ok:"+data);
							location.href='board';
						},
						error: function(error){
							alert("error:"+error);
						}
					});
				});
			});
			function update()
			   {
				let jsObj = {seq:$("#seq").val(), writer:$("#writer").val(), email:$("#email").val(), subject:$("#subject").val(), content:$("#content").val()};
				let jsonData = JSON.stringify(jsObj);
				$.ajax({
					url: "update.json", 
					type: "",
					contentType: "application/json;charset=utf-8",
					data: jsonData,
					success: function(data){
						alert("ok:"+data);
						location.href='board';
					},
					error: function(error){
						alert("error:"+error);
					}
				});
		       }
		</script>
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> Spring Board ( Spring5 + MyBatis )</h3>
</font>
<font color='gray' size='4' face='휴먼편지체'>
<a href='list.do?ps=${ps}'>목록</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='write.do'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>
</center>

<form name='input' method='post' action='update.do'>
<input id="seq" type="hidden" name="seq"/>
<table border='0' width='600' align='center' cellpadding='3' cellspacing='1' bordercolor='gray'>	
<tr>
   <td width='20%' align='center' >WRITER</td>
   <td>
      <input id="writer" name='writer' readonly/>
   </td>
</tr>

<tr>
	<td align='center'>EMAIL</td>
	<td ><input id="email" name='email'/></td>
</tr>

<tr>
	<td align='center'>SUBJECT</td>
	<td ><input id="subject" name='subject'/></td>
</tr>
			
<tr>
	<td align='center'>CONTENT</td>
	<td ><textarea id="content" id='ta' name='content' rows='15' cols='70'></textarea></td>
</tr>
<tr>
	 <td colspan='2' align='center'>
		<input type='button' value='수정' onclick="update()">
		<input type="button" value="다시입력" onclick="f()"> 
	 </td>
</tr>

</table>
<hr width="600" size="2" color="gray" noshade>
</form>
</body>
</html>

