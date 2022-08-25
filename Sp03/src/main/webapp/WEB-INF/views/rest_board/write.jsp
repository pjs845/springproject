<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Restful Form</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script>
			$(function(){
				$("#wr").focus();
				$("#btn").on("click", function(){
					/*$.ajax({
						url:"create1.json", 
						type:"POST",
						data:{name:$("#na").val(), addr:$("#ad").val()},
						success: function(data){
							alert(data);
						},
						error: function(error){
							alert(error);
						}
					});*/
					let jsObj = {writer:$("#wr").val(), email:$("#em").val(), subject:$("#sub").val(), content:$("#con").val()};
					let jsonData = JSON.stringify(jsObj);
					$.ajax({
						url: "create2.json", 
						type: "POST",
						contentType: "application/json;charset=utf-8",
						data: jsonData,
						success: function(data){
							alert(data);
							location.href='board';
						},
						error: function(error){
							alert(error);
						}
					});
				});
			});
		</script>
	</head>
	<body onload="input.writer.focus()">
	<font color="gray" size='4' face="휴먼편지체">
    <center>
	   <hr width="600" size='2' color="gray" noshade>
	      <h3> Spring Board ( Spring5 + MyBatis )</h3>
		  	<font color="gray" size="3" face="휴먼편지체">
			<a href='list.do?ps=${ps}'>리스트</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href='../'>인덱스</a>
			</font>
	   <hr width="600" size="2" color="gray" noshade>
	</center>

	<form name="input" method="post" action="write.do">
	   <table border="0" width="600" align="center"  cellpadding="3" cellspacing="1" bordercolor="gray">
	      <tr>
		     <td width="30%" align="center">WRITER</td>
			 <td><input id="wr" type="text" name="writer" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">EMAIL</td>
			 <td><input id="em" type="text" name="email" size="60"></td>
		  </tr>
          <tr>
		     <td align="center">SUBJECT</td>
			 <td><input id="sub" type="text" name="subject" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">CONTENT</td>
			 <td><textarea id="con"  name="content" rows="15" cols="70"></textarea></td>
		  </tr>
		  <tr>
		     <td colspan="2" align="center">
			    <input type="button" value="전송" id="btn">
				<input type="reset" value="다시입력" onclick="input.writer.focus()">
			 </td>
		  </tr>
	   </table>
	   <hr width="600" size="2" color="gray" noshade>
	</form>
	</font>
  </body>
</html>