<%
String bootstrap3_path = request.getContextPath();
String bootstrap3_basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + bootstrap3_path + "/";
%>


<link rel="stylesheet" type="text/css" href="<%=bootstrap3_basePath %>plugin/bootstrap3/css/bootstrap.min.css">