<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style>
		button{
			margin-top:10px;
		}
	</style>
  </head>
  
  <body>
    <button id="responseRedirect">重定向</button><br />
    
    <button id="responseRefresh">三秒后跳转</button>
  </body>
  
  <script type="text/javascript">
  	var button_redirect = document.getElementById("responseRedirect");
  	var button_refresh = document.getElementById("responseRefresh");
  	
  	
  	
  	button_redirect.onclick = function(){
  		//location.href 可以是uri
  		window.location.href = "/WebTestUnit/response/redirect";	
  	}
  	
  	
  	button_refresh.onclick = function(){
  		window.location.href = "/WebTestUnit/response/refresh";	
  	}
  </script>
</html>
