<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>后台管理系统</title>
<%@include file="/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css">
<script type="text/javascript" src="<%=basePath%>js/adminIndex.js"></script>
</head>
<body>
 <body class="easyui-layout" style="margin: 2px">
	<div data-options="region:'north',split:true" style="height: 50px"></div>
	<div data-options="region:'south',split:true" style="height: 50px;"></div>
	<div data-options="region:'east',split:true,title:'辅助'"
		style="width: 180px;"></div>
	<div data-options="region:'west',split:true,title:'功能菜单'"
		style="width: 180px;">
		<div id="nav" class="easyui-accordion"
			data-options="fit:true,border:false,animate:false"></div>
	</div>
	<div data-options="region:'center',border:false">
		<div id="tabs" class="easyui-tabs" data-options="fit:true,border:true"></div>
	</div>
</body>
</html>
