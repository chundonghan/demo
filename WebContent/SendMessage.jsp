<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Send Message</title>

<script type="text/javascript" src="./js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/SendPushService.js"></script>
<script type="text/javascript">  
    $(function(){  
        dwr.engine.setActiveReverseAjax(true);  
        $("#but").click(function(){  
        	SendPushService.send($("#msg").val());  
        });  
    });  
</script>  
<title>Insert title here</title>  
</head>    
<body>  
    <input type="text" id="msg"  />  
    <input type="button" value="发送" id="but" />  
</body>
</html>