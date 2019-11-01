
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<div class="title">Search Page</div><br><br>
	<form method="post" action="/task4/Servelt" style="text-align:center">
		<input type="text" name="search" placeholder="input keyword"><br><br>
		<button type="submit" >Search</button><br><br>
		<style type="text/css">
		.title {
		   text-align:center;
		   font-size: 24px;
		   color: #FF8C69;
		   font-style: italic;
		}
		input, textarea {
		    padding: 4px;
		    border: solid 1px #FFFAFA;
		    outline: 0;
		    font: normal 13px/100% Verdana, Tahoma, sans-serif;
		    width: 200px;
		    background: #FFFAFA;
		    box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
		    -moz-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
		    -webkit-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
		    }
        input:hover, textarea:hover, input:focus, textarea:focus { 
        	border-color: #C9C9C9; 
        }
        </style>	
	</form>
</body>
</html>