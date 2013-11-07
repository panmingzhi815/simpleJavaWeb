<%
String bootstrap3_path = request.getContextPath();
String bootstrap3_basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + bootstrap3_path + "/";
%>
<link rel="stylesheet" href="<%=bootstrap3_basePath %>plugin/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=bootstrap3_basePath %>plugin/bootstrap3/css/bootstrap-theme.min.css">
<script src="<%=bootstrap3_basePath %>plugin/bootstrap3/js/bootstrap.min.js"></script>

