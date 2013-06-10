<%
String ext_path = request.getContextPath();
String ext_basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + ext_path + "/";
%>
	<link rel="stylesheet" type="text/css" href="<%=ext_basePath %>plugin/ext-4.2.1.883/resources/css/ext-all.css" />
	<script type="text/javascript" src="<%=ext_basePath %>plugin/ext-4.2.1.883/ext-all-debug.js"></script>
