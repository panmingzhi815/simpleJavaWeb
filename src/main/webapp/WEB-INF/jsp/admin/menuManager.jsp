<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
    <%@include file="/include/easyui.jsp" %>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css"/>
    <script src="<%=basePath%>plugin/tagsInput/jquery.tagsinput.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>plugin/tagsInput/jquery.tagsinput.css"/>
</head>
<body class="easyui-layout">
<div region="center" border="false" style="padding: 1px 1px 1px 1px;">
    <table id="tg" idField="id" treeField="display" title="菜单管理" class="easyui-treegrid"
           fit="true" border="true" toolbar="#toolbar" singleSelect="true">
        <thead>
        <tr>
            <th field="display" width="150">名称</th>
            <th field="text" width="150" hidden="true"></th>
            <th field="iconCls" width="100">图标</th>
            <th field="ordinal" width="50">排序</th>
            <th field="url" width="200">链接</th>
            <th field="descript" width="200">描述</th>
        </tr>
        </thead>
    </table>
</div>
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="destroyUser()">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">控件权限</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
       onclick="editUser()">数据权限</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-sum" plain="true"
       onclick="expandAll()">全部展开</a>
</div>
<div id="dlg" class="easyui-dialog" style="width: 500px; height: 350px; padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="formTitle">基本信息</div>
    <form id="fm" method="post" class="form">
        <input type="hidden" id="id" name="id">
        <input type="hidden" id="parentId" name="parentId">

        <div style="margin-bottom: 5px">
            <label>名称:</label>
            <input name="text" class="easyui-validatebox" required="true">

            <label>图标:</label>
            <input id="input_icon" name="iconCls" class="easyui-validatebox" required="true">
        </div>
        <div>
            <label>排序:</label>
            <input name="ordinal" class="easyui-numberspinner" required="required"
                   data-options="min:1,max:1000,editable:true">

            <label>链接:</label>
            <input name="url" class="easyui-validatebox" required="true" validType="url">
        </div>
    </form>
    <br/>

    <div class="formTitle">控件权限</div>
    <input name="controllerTags" id="controllerTags" value="添加,修改,删除,查询"/>
    <br/>

    <div class="formTitle">数据权限</div>
    <div style="margin-bottom: 10px">
        <label>名称:</label> <input id="expressionName" name="expressionName">
        <label>表达式:</label> <input id="expressionContent" name="expressionContent">
        <a id="addExpressionBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add">添加</a>
    </div>
    <table id="expressionTable" class="easyui-datagrid" style="height:150px;width:400px"
           data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
        <thead>
        <tr>
            <th data-options="field:'expressionName',width:100">名称</th>
            <th data-options="field:'expressionContent',width:240">表达式</th>
            <th data-options="field:'expressionOperate',width:50">操作</th>
        </tr>
        </thead>
    </table>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="saveUser()">保存</a>
</div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        $('#tg').treegrid({
            url: '/admin/menu/getSysMenuList',
            loadFilter: function (rows) {
                return convert(rows);
            }
        });

        $('#controllerTags').tagsInput({width: 385});

        $("#addExpressionBtn").click(function () {
            var expressionName = $("#expressionName").val();
            var expressionContent = $("#expressionContent").val();
            if (expressionName.length == 0 || expressionContent.length == 0) return;
            $("#expressionTable").datagrid('appendRow', {
                expressionName: expressionName,
                expressionContent: expressionContent,
                expressionOperate: "<a href='javascript:void(0)' onclick='deleteExpression()'>删除</a>"
            });
        });
    });

    function newUser() {
        $('#fm').form('clear');

        var title = "新建菜单";
        var node = $('#tg').treegrid('getSelected');
        if (node != null) {
            title = "新建[" + node.text + "]子菜单"
            $('#parentId').val(node.id);
        }
        $('#dlg').dialog('open').dialog('setTitle', title);
    }
    function deleteExpression() {
        var row = $("#expressionTable").datagrid("getSelected");
        if (row == null) return;
        var index = $("#expressionTable").datagrid("getRowIndex", row);
        $("#expressionTable").datagrid("deleteRow", index);
    }
    function destroyUser() {
        var node = $('#tg').treegrid('getSelected');
        if (node) {
            $.messager.confirm('确认',
                    '你确定要删除这条记录吗?', function (r) {
                        if (r) {
                            $.post('/admin/menu/deleteSysMenu', {
                                id: node.id
                            }, function (result) {
                                if (result) {
                                    $('#tg').treegrid('remove', node.id);
                                } else {
                                    $.messager.show({
                                        title: 'Error',
                                        msg: '删除失败,该记录可能与其他模块己关联'
                                    });
                                }
                            }, 'json');
                        }
                    });
        }
    }

    function editUser() {
        var row = $('#tg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '编辑菜单');
            $('#fm').form('load', row);
        }
    }

    function expandAll() {
        $('#tg').treegrid('expandAll');
    }

    function saveUser() {
        $('#fm').form('submit', {
            url: "/admin/menu/saveSysMenu",
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                if (result.id != 0) {
                    $('#dlg').dialog('close');
                    var parentId = $('#parentId').val();
                    if (parentId == '') {
                        $('#tg').treegrid('reload');
                    } else {
                        $('#tg').treegrid('update', {
                            id: parentId,
                            row: {
                                state: 'closed'
                            }
                        });
                        $('#tg').treegrid('reload', parentId);
                    }
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: "添加失败!"
                    });
                }
            }
        });
    }

    function convert(row) {
        $.each(row, function (i, n) {
            n["display"] = "<span class='" + n.iconCls + "'> </span>" + n.text;
        });
        return row;
    }
</script>