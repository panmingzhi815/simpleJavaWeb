<%
String kindeditor_path = request.getContextPath();
String kindeditor_basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+kindeditor_path+"/";
%>
<script type="text/javascript" src="<%=kindeditor_basePath %>/plugin/kindeditor-4.1.7/kindeditor-min.js"></script>
<script type="text/javascript" src="<%=kindeditor_basePath %>/plugin/kindeditor-4.1.7/lang/zh_CN.js"></script>
<link rel="stylesheet"d type="text/css" href="<%=kindeditor_basePath %>/plugin/kindeditor-4.1.7/themes/default/default.css" />	