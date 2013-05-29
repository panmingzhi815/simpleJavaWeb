<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
	<meta charset="UTF-8">
	<%@include file="WEB-INF/common-include/easyui.jsp" %>
  </head>
  <body class="easyui-layout">
        <div data-options="region:'north',border:false" style="height:70px"></div>  
        <div data-options="region:'south',border:false" style="height:50px;"></div>   
        <div data-options="region:'west',split:true" title="菜单" style="width:200px;"></div>  
        <div data-options="region:'center'"></div>  
  </body>
</html>
