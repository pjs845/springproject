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
		$.ajax({
			url: "read.json",
			type: "GET",
			success: function(data){
				if(!data){
                    alert("존재하지 않는 data");
                    return false;
                 }
				var html = "";
				if(data.length == 0){
                    html += "<tr>";
                    html += "<td colspan='4' align='center'>DATA가 없음</td>";
                    html += "</tr>";
                 }else{
                    for(let address of data){
                       html += "<tr align='center' noshade>";
                       html += "<td align='center'>"+address.seq+"</td>";
                       html += "<td align='center'>"+address.writer+"</td>";
                       html += "<td align='center'>"+address.email+"</td>";
                       html += "<td align='center'><a href='content.do?seq="+address.seq+"'>"+address.subject+"</a></td>";           		  
                       html += "<td align='center'>"+address.rdate+"</td>";
                       html += "</tr>"
                    }
                 }
				$("#container").append(html);
			}
		});
	});
	</script>
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> Spring Board ( Spring5 + MyBatis )</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='write.do'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>

<TABLE id="container" border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
	<th bgcolor='AliceBlue'>no</th>
	<th color='gray'>writer</th>
	<th color='gray'>e-mail</th>
	<th color='gray'>subject</th>
	<th color='gray'>date</th>
</TR>

</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>
    (총페이지수 : ${listResult.totalPageCount})
    &nbsp;&nbsp;&nbsp;
    <c:if test="${listResult.startpaging != 1 }">
			<a href="list.do?ps=${ps}&cp=${listResult.startpaging - 1 }">&lt;</a>
	</c:if>
    <c:forEach begin="${listResult.startpaging }" end="${listResult.endpaging }" var="i">
        <a href="list.do?ps=${ps}&cp=${i}">
   			<c:choose>
   			    <c:when test="${i==listResult.cp}">
                	<strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    ${i}
                </c:otherwise>
			</c:choose>
    	</a>&nbsp;
    </c:forEach>
    <c:if test="${listResult.endpaging != listResult.totalPageCount}">
			<a href="list.do?ps=${ps}&cp=${listResult.endpaging + 1 }">&gt;</a>
	</c:if>
    ( TOTAL : ${listResult.totalCount} )
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">	
			 <c:choose>
		    	<c:when test="${listResult.ps == 3 }">
			   		<option value="3" selected>3</option>
				       <option value="5">5</option>
				       <option value="10">10</option>
			    </c:when>
			    <c:when test="${listResult.ps == 5 }">
			   		<option value="3" >3</option>
				       <option value="5" selected>5</option>
				       <option value="10">10</option>
			    </c:when>
			    <c:when test="${listResult.ps == 10 }">
			   		<option value="3" >3</option>
				       <option value="5">5</option>
				       <option value="10" selected>10</option>
			    </c:when>
			</c:choose>

    		   
    		
    		
    		
    	
    </select>
    
    <script language="javascript">
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           location.href="list.do?ps="+ps+"&cp=1";
       }
    </script>    
</font>
<hr width='600' size='2' color='gray' noshade>   
    <form action="">
      <select name="catgo">
        <option value="subject">제목</option>
        <option value="writer">글쓴이</option>
        <option value="content">내용</option>
      </select>
      <input type="text" name="keyword" size="40" required="required" /> <button>검색</button>
    </form>  
</center>
</body>
</html>