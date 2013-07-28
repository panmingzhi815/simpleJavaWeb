<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/common-include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/default.css">
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="height: 60px;">
		<fieldset>
			<legend>查询</legend>
			<label>用户名</label> <input /> <label>昵称</label> <input /> <a class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</fieldset>
	</div>
	<div region="center" border="false">
		<table id="dg" title="用户管理" class="easyui-datagrid" fit="true" url="get_users.php" border="true" toolbar="#toolbar" pagination="true" rownumbers="true" singleSelect="true">
			<thead>
				<tr>
					<th field="firstname" width="150">用户名</th>
					<th field="lastname" width="150">昵称</th>
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
			<div class="formTitle">User Information</div>
			<form id="fm" method="post" class="form" novalidate>
				<div class="formDiv">
					<label>First Name:</label> <input name="firstname" class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label>Last Name:</label> <input name="lastname" class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label>Phone:</label> <input name="phone">
				</div>
				<div class="fitem">
					<label>Email:</label> <input name="email" class="easyui-validatebox" validType="email">
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
	var url;
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', 'New User');
		$('#fm').form('clear');
		url = 'save_user.php';
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('load', row);
			url = 'update_user.php?id=' + row.id;
		}
	}
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.errorMsg) {
					$.messager.show({
						title : 'Error',
						msg : result.errorMsg
					});
				} else {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			}
		});
	}
	function destroyUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to destroy this user?', function(r) {
						if (r) {
							$.post('destroy_user.php', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
										msg : result.errorMsg
									});
								}
							}, 'json');
						}
					});
		}
	}
</script>