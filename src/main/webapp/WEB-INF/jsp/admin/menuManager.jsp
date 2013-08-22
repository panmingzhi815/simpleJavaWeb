<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/default.css" />
</head>
<body class="easyui-layout">
	<div region="center" border="false">
		<table id="dg" idField="id" title="菜单管理" class="easyui-datagrid"
			fit="true" data-options="singleSelect:true,url:'/user/userJSONPage.user'" border="true" toolbar="#toolbar" pagination="true" rownumbers="true"
			singleSelect="true">
			<thead>
				<tr>
					<th field="display" width="150">显示</th>
					<th field="text" width="150">名称</th>
					<th field="iconCls" width="200">图标</th>
					<th field="ordinal" width="200">排序</th>
					<th field="url" width="300">链接</th>
					<th field="descript" width="300">描述</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">权限设置</a>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {

	})
</script>