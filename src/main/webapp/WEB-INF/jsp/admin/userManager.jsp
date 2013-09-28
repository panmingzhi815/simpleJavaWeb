<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/default.css" />
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="padding: 10px 10px 10px 10px">
			<label>用户名</label> <input id="search_any_loginName" class="form-control"/> <label>昵称</label> <input id="search_any_nickName"  class="form-control"/> <a id="searchBtn" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
	</div>
	<div region="center" border="false" style="padding: 1px 1px 1px 1px">
		<table id="dg" idField="id" title="用户管理" class="easyui-datagrid" fit="true" data-options="singleSelect:true,url:'/admin/userJSONPage'" border="true" toolbar="#toolbar" pagination="true" rownumbers="true" singleSelect="true">
			<thead>
				<tr>
					<th field="loginName" width="150">用户名</th>
					<th field="nickName" width="150">昵称</th>
					<th field="phone" width="200">手机</th>
					<th field="email" width="300">邮件</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"
				plain="true" onclick="destroyUser()">删除</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
		</div>

		<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
			<div class="formTitle">用户信息</div>
			<form id="fm" method="post" class="form">
                <input type="hidden" id="id" name="id">
				<div class="formDiv">
					<label>用户名:</label> <input name="loginName" class="easyui-validatebox" required="true">
				</div>
				<div class="formDiv">
					<label>昵称:</label> <input name="nickName" class="easyui-validatebox" required="true">
				</div>
				<div class="formDiv">
					<label>手机:</label> <input name="phone">
				</div>
				<div class="formDiv">
					<label>邮件:</label> <input name="email" class="easyui-validatebox" validType="email">
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function(){
		$("#searchBtn").click(function(){
			 $('#dg').datagrid('load',{  
		        'search_any_loginName': $('#search_any_loginName').val(),  
	        	'search_any_nickName': $('#search_any_nickName').val()  
		    });  
		});
	})
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', '新建用户');
		$('#fm').form('clear');
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
			$('#fm').form('load', row);
		}
	}
	function saveUser() {
		$('#fm').form('submit', {
			url : "/admin/saveSysUser",
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				if (result != 0) {
					$('#dlg').dialog('close');
					$('#dg').datagrid('reload');
				} else {
					$.messager.show({
						title : 'Error',
						msg : "添加失败!"
					});
				}
			}
		});
	}
	function destroyUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认',
					'你确定要删除这条 记录吗?', function(r) {
						if (r) {
							$.post('/admin/deleteSysUser', {
								id : row.id
							}, function(result) {
								if (result) {
									$('#dg').datagrid('reload'); 
								} else {
									$.messager.show({
										title : 'Error',
										msg : '删除失败,该记录可能与其他模块己关联'
									});
								}
							},'json');
						}
					});
		}
	}
</script>