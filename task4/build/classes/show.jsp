<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%String keyword = (String)request.getAttribute("keyword"); 
  String result = (String)request.getAttribute("result");
%>
<body>
	<div class="title">Result for MapReuce Searching</div><br><br>
	<div class="keyword">keyword:<%=keyword %></div><br>
	<div style="color:#9AC0CD">
		<p><%=result %></p>
	</div>
	<a href="index.jsp"><button style="text-align:center">Back</button></a>
	<style type="text/css">
	.title {
		   text-align:center;
		   font-size: 24px;
		   color: #FF8C69;
		   font-style: italic;
		}
	.keyword {
		   text-align:center;
		   font-size: 18px;
		   color: #FF82AB;
		   font-style: italic;
		}
	</style>
</body>
</html>