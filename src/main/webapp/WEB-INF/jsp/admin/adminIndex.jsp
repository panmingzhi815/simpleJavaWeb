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
        <title>后台管理系统</title>
        <base href="<%=basePath%>">
        <%@include file="/include/easyui.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/default.css">
        <script type="text/javascript" src="js/adminIndex.js"></script>
    </head>
    <body class="easyui-layout" style="margin: 1px 2px 2px 2px">
        //banner制作网址 http://www.55.la/banner/
        <div data-options="region:'north'" border="true" style="height: 70px;overflow: hidden;">
            <img src="images/adminBanner.png" style="width: 110%;height: 110%; z-index: 1;position:absolute ">
            <div style="position: absolute;z-index: 2;height: 90px;width: 100%">
                <table style="width: 100%">
                    <tr>
                        <td valign="middle" rowspan="3">
                            <img src="images/adminLogo.png">
                        </td>
                        <td valign="middle" rowspan="3">
                            <a style="font-size: 17pt; color: #0081C2">simpleJavaWeb 简易至上</a>
                        </td>
                        <td rowspan="3" style="width: 50%">
                        </td>
                        <td>欢迎你：小潘</td>
                    </tr>
                    <tr><td>今天是2013年10月23日</td></tr>
                    <tr>
                        <td>
                            <img src="images/default.png" style="height: 15px;width: 15px;cursor: pointer" onclick="window.location.href='?easyui-theme=default'">
                            <img src="images/bootstrap.png" style="height: 15px;width: 15px;cursor: pointer" onclick="window.location.href='?easyui-theme=bootstrap'">
                            <img src="images/dark-hive.png" style="height: 15px;width: 15px;cursor: pointer" onclick="window.location.href='?easyui-theme=dark-hive'">
                            <img src="images/pepper-grinder.png" style="height: 15px;width: 15px;cursor: pointer" onclick="window.location.href='?easyui-theme=pepper-grinder'">
                            <img src="images/sunny.png" style="height: 15px;width: 15px;cursor: pointer" onclick="window.location.href='?easyui-theme=sunny'">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div data-options="region:'south',split:true" style="height: 50px;"></div>
        <div data-options="region:'east',split:true,title:'辅助'"
             style="width: 180px;"></div>
        <div data-options="region:'west',split:true,title:'功能菜单'" style="width: 180px">
            <div id="nav" class="easyui-accordion" data-options="fit:true,border:false,animate:false"></div>
        </div>
        <div data-options="region:'center',border:false">
            <div id="tabs" class="easyui-tabs" data-options="fit:true,border:true"></div>
        </div>
    </body>
</html>
