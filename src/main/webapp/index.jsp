<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'hello.jsp' starting page</title>
<%@include file="WEB-INF/jsp/common-include/easyui.jsp"%>
</head>

<body>
	<h2>Basic Layout</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>The layout contains north,south,west,east and center regions.</div>
	</div>
	<div style="margin: 10px 0;"></div>
	<div class="easyui-layout" style="width: 700px; height: 350px;">
		<div data-options="region:'north'" style="height: 50px"></div>
		<div data-options="region:'south',split:true" style="height: 50px;"></div>
		<div data-options="region:'east',split:true" title="East" style="width: 180px;"></div>
		<div data-options="region:'west',split:true" title="West" style="width: 100px;"></div>
		<div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
			<table class="easyui-datagrid"
				data-options="url:'../layout/datagrid_data1.json',border:false,singleSelect:true,fit:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'itemid'" width="80">Item ID</th>
						<th data-options="field:'productid'" width="100">Product ID</th>
						<th data-options="field:'listprice',align:'right'" width="80">ListPrice</th>
						<th data-options="field:'unitcost',align:'right'" width="80">UnitCost</th>
						<th data-options="field:'attr'" width="150">Attribute</th>
						<th data-options="field:'status',align:'center'" width="50">Status</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

</body>
</html>
