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
		   var accordion = new Ext.Panel({    
		        title: "功能分组",    
		        layout: "accordion",    
		        layoutConfig: {    
		            animate: true    
		        },    
		        width: 250,    
		        minWidth: 100,    
		        region: "west",    
		        split: false,    
		        collapsible: true,  //如果为true，panel是可收缩的，并且有一个收起/展开按钮自动被渲染到它的头部工具区域， 否则panel的大小是固定的，没有按钮(默认值为 false)。  
		        items: [    
		            {title:"嵌套面板一", html:"嵌套面板一", iconCls:"save",collapsed:true},    
		            {title:"嵌套面板二", html:"嵌套面板二", iconCls:"search",collapsed:true},    
		            {title:"嵌套面板三", html:"嵌套面板三", iconCls:"back",collapsed:true}    
		        ]    
		    }); 
		
		   new Ext.Viewport({    
		        title: "Viewport",    
		        layout: "border",      
		        defaults: {     
		            frame: true    
		        },    
		        items: [    
		            accordion,    
		            {region:"north", height:60},    
		            {region:"center"},  
		            {region:"south", height:30}, 
		        ]    
		    });    
		

	});
</script>
</head>
<body>

</body>
</html>
