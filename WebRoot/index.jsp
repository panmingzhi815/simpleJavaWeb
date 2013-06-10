<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="WEB-INF/jsp/common-include/ext.jsp"%>
<script type="text/javascript">
	Ext.onReady(function() {
		var body = new Ext.Viewport({
			layout : "border",
			items : [ {
				region : "north",
				height : 60
			}, {
				region : "south",
				height : 30
			}, {
				region : "center"
			}, {
				region : "west",
				width : 200,
				collapsible: true,
				title : "菜单"
			} ]
		});
	});
</script>
</head>
<body>

</body>
</html>
