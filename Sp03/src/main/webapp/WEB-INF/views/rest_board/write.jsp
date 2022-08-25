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
	<body style="text-align:center">
		<h1>
			Rest Address Create
		</h1>
		작성자 : <input id="wr"><br/> 
		이메일 : <input id="em"><br/>
		제목 : <input id="sub"><br/>
		내용 : <input id="con"><br/>
		<input type="button" value="주소록 추가" id="btn">
	</body>
</html>