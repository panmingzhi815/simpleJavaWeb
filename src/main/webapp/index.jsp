<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'hello.jsp' starting page</title>
		<%@include file="WEB-INF/jsp/common-include/easyui.jsp"%>
	</head>
	
	<body class="easyui-layout" style="margin: 2px">
		<div data-options="region:'north',split:true" style="height: 50px"></div>
		<div data-options="region:'south',split:true" style="height: 50px;"></div>
		<div data-options="region:'east',split:true,title:'辅助'" style="width: 180px;"></div>
		<div data-options="region:'west',split:true,title:'功能菜单'" style="width: 180px;"></div>
		<div data-options="region:'center'"></div>
	</body>
</html>
