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
	<script type="text/javascript" language="javascript" 
		     src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script>
		$(function(){
		 	$("#resetBtn").on("click", function(){
		 		location.href="list.do?cp=1&searchModeStr=F";
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

<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
	<th bgcolor='AliceBlue'>no</th>
	<th color='gray'>writer</th>
	<th color='gray'>e-mail</th>
	<th color='gray'>subject</th>
	<th color='gray'>date</th>
</TR>
<c:if test="${empty listResult}">
	<tr align="center" noshade>
	   <td colspan="5">데이터가 하나도 없음</td>
	</tr>
</c:if>
<c:forEach items="${listResult.list}" var="board">
	<TR align='center' noshade>
		<TD>${board.seq}</TD>
		<TD>${board.writer}</TD>
		<TD>${board.email}</TD>
	    <TD>
	      <a href="content.do?seq=${board.seq}">
		    ${board.subject}
		  </a>
		</TD>
		<TD>${board.rdate}</TD>
	   </TR> 
</c:forEach>      
     
</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>
    (총페이지수 : ${listResult.totalPageCount})
    &nbsp;&nbsp;&nbsp;
    <c:forEach begin="1" end="${listResult.totalPageCount}" var="i">
        <a href="list.do?cp=${i}">
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
    ( TOTAL : ${listResult.totalCount} )
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
    	<c:choose>
    		<c:when test="${listResult.ps == 3}">
    		   <option value="3" selected>3</option>
		       <option value="5">5</option>
		       <option value="10">10</option>
    		</c:when>
    		<c:when test="${listResult.ps == 5}">
    		   <option value="3">3</option>
		       <option value="5" selected>5</option>
		       <option value="10">10</option>
    		</c:when>
    		<c:when test="${listResult.ps == 10}">
    		   <option value="3">3</option>
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
           location.href="list.do?ps="+ps;
       }
    </script>
    
</font>
<hr width='600' size='2' color='gray' noshade>
     

	

<form action="list.do">
  
	<select name="catgo">
		<c:choose>
			<c:when test="${!empty searchModeStr && searchModeStr eq 'T'}">
				<c:choose>
					<c:when test="${catgo eq 'subject'}">
						<option value="subject" selected>제목</option>
						<option value="writer">글쓴이</option>
						<option value="content">내용</option>
					</c:when>
					<c:when test="${catgo eq 'writer'}">
						<option value="subject">제목</option>
						<option value="writer" selected>글쓴이</option>
						<option value="content">내용</option>
					</c:when>
					<c:when test="${catgo eq 'content'}">
						<option value="subject">제목</option>
						<option value="writer">글쓴이</option>
						<option value="content" selected>내용</option>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<option value="subject" selected>제목(S)</option>
				<option value="writer">글쓴이(W)</option>
				<option value="content">내용(C)</option>
			</c:otherwise>
		</c:choose>
	</select>
	 
	<c:choose>  
		<c:when test="${!empty searchModeStr && searchModeStr eq 'T'}"> 
			<input type="text" name="keyword" size="40" required="required" id="keywordId" 
				value="${keyword}" disabled/> 
		</c:when>
		<c:otherwise>
			<input type="text" name="keyword" size="40" required="required" id="keywordId"/>
		</c:otherwise>
	</c:choose> 
	
	<input type="hidden" name="cp" value="1"/>
	<input type="hidden" name="searchModeStr" value="T"/>
	<input type="submit" value="검색" id="submitBtn"/>
	<input type="button" value="초기화" id="resetBtn"/>
	
</form>  
    
 
</center>
</body>
</html>