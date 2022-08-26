$(function(){
				$("#na").focus();
				$("#btn").on("click", function(){
					/*$.ajax({
						url:"create1.json", 
						type:"post", 
						data: {name:$("#na").val(), addr:$("#ad").val()}, 
						success: function(data){
							alert(data);
						}, 
						error: function(error){
							alert(error);
						}
					});*/
					
					let jsObj = {name:$("#na").val(), addr:$("#ad").val()};
					let jsonData = JSON.stringify(jsObj);
					$.ajax({
						url:"create2.json", 
						type:"post", 
						contentType: "application/json;charset=utf-8", 
						data: jsonData , 
						success: function(data){
							alert(data);
						}, 
						error: function(error){
							alert(error);
						}
					});
				});
			});