<%
String easyui_path = request.getContextPath();
String easyui_basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + easyui_path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=easyui_basePath %>plugin/jquery-easyui-1.3.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=easyui_basePath %>plugin/jquery-easyui-1.3.4/themes/icon.css">
<script type="text/javascript" src="<%=easyui_basePath %>plugin/jquery-easyui-1.3.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=easyui_basePath %>plugin/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=easyui_basePath %>plugin/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=easyui_basePath %>js/jquery.tabs.extend.js"></script>