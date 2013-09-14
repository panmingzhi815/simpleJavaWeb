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
	<div region="north" border="false" style="height: 60px;">
		<fieldset>
			<legend>查询</legend>
			<label>角色名称</label> <input id="search_any_name" /> <a id="searchBtn" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</fieldset>
	</div>
	<div region="center" border="false">
		<table id="dg" idField="id" title="角色管理" class="easyui-datagrid" fit="true" data-options="singleSelect:true,url:'/admin/roleJSONPage'" border="true" toolbar="#toolbar" pagination="true" rownumbers="true" singleSelect="true">
			<thead>
				<tr>
					<th field="name" width="150">角色名称</th>
					<th field="note" width="300">角色说明</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRole()">新建</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"
				plain="true" onclick="destroyRole()">删除</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">修改</a>
		</div>

		<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
			<div class="formTitle">角色信息</div>
			<form id="fm" method="post" class="form">
                <input type="hidden" id="id" name="id">
				<div class="formDiv">
					<label>角色名称:</label>
                    <input name="name" class="easyui-validatebox" required="true">
				</div>
				<div class="formDiv">
					<label>角色说明:</label>
                    <textarea name="note" rows="4"></textarea>
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">Save</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function(){
		$("#searchBtn").click(function(){
			 $('#dg').datagrid('load',{  
		        'search_any_name': $('#search_any_name').val()
		    });  
		});
	})
	function newRole() {
		$('#dlg').dialog('open').dialog('setTitle', '新建角色');
		$('#fm').form('clear');
	}
	function editRole() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑角色');
			$('#fm').form('load', row);
		}
	}
	function saveRole() {
		$('#fm').form('submit', {
			url : "/admin/saveSysRole",
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
	function destroyRole() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认',
					'你确定要删除这条 记录吗?', function(r) {
						if (r) {
							$.post('/admin/deleteSysRole', {
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