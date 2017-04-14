<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

		$(function(){
			var token ="${token}";
			$("#login").click(function(){
				$.get(
					'v1/login',
					{token:token},
					function(data){
				
					}
				);
			});
		});
</script>
</head>
<body>
	${userList.size()}
	<input type="button" value="login" id="login"/>
</body>
</html>