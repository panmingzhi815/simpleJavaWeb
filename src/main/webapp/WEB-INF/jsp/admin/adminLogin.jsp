<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>后台管理系统</title>
    <base href="<%=basePath%>">
    <%@include file="/include/easyui.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/default.css">

</head>
<body>
    <div id="w" class="easyui-window" title="登陆" data-options="modal:true,iconCls:'icon-role'" style="width:500px;height:300px;">
        <img src="">
    </div>
</body>
</html>