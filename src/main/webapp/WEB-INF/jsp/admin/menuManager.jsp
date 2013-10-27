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
	<div region="center" border="false" style="padding: 1px 1px 1px 1px">
		<table id="tg" idField="id" treeField="display" title="菜单管理" class="easyui-treegrid"
			fit="true" border="true" toolbar="#toolbar" singleSelect="true">
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-sum" plain="true" onclick="expandAll()">全部展开</a>
    </div>
    <div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
        <div class="formTitle">菜单信息</div>
        <form id="fm" method="post" class="form">
            <input type="hidden" id="id" name="id">
            <input type="hidden" id="parentId" name="parentId">
            <div class="formDiv">
                <label>名称:</label> <input name="text" class="easyui-validatebox" required="true">
            </div>
            <div class="formDiv">
                <label>图标:</label> <input name="iconCls" class="easyui-validatebox" required="true">
            </div>
            <div class="formDiv">
                <label>排序:</label> <input name="ordinal" class="easyui-validatebox" required="true">
            </div>
            <div class="formDiv">
                <label>链接:</label> <input name="url" class="easyui-validatebox" required="true">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
    </div>
</body>
</html>
<script type="text/javascript">
	$(function() {
        $(function(){
            $('#tg').treegrid({
                url: '/admin/getSysMenuList',
                loadFilter: function(rows){
                    return convert(rows);
                }
            });
        });

	})

    function newUser(){
        $('#fm').form('clear');

        var title = "新建菜单";
        var node = $('#tg').treegrid('getSelected');
        if(node != null){
            title = "新建["+node.text+"]子菜单"
            $('#parentId').val(node.id);
        }
        $('#dlg').dialog('open').dialog('setTitle', title);
    }

    function destroyUser(){
        var node = $('#tg').treegrid('getSelected');
        if(node){
            $.messager.confirm('确认',
                    '你确定要删除这条记录吗?', function(r) {
                        if (r) {
                            $.post('/admin/deleteSysMenu', {
                                id : node.id
                            }, function(result) {
                                if (result) {
                                    $('#tg').treegrid('remove',node.id);
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

    function editUser(){
        var row = $('#tg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '编辑菜单');
            $('#fm').form('load', row);
        }
    }

    function expandAll(){
        $('#tg').treegrid('expandAll');
    }

    function saveUser() {
        $('#fm').form('submit', {
            url : "/admin/saveSysMenu",
            onSubmit : function() {
                return $(this).form('validate');
            },
            success : function(result) {
                if (result.id != 0) {
                    $('#dlg').dialog('close');
                    var parentId = $('#parentId').val();
                    if(parentId == ''){
                        $('#tg').treegrid('reload');
                    }else{
                        $('#tg').treegrid('update',{
                            id: parentId,
                            row: {
                                state: 'closed'
                            }
                        });
                        $('#tg').treegrid('reload',parentId);
                    }
                } else {
                    $.messager.show({
                        title : 'Error',
                        msg : "添加失败!"
                    });
                }
            }
        });
    }

    function convert(row){
        $.each(row,function(i,n){
            n["display"] = "<span class='"+ n.iconCls+"'> </span>"+ n.text;
        });
        return row;
    }
</script>