$(function(){
	initLeftMenu(admin_fix_menus,"navigator");
})

var admin_fix_menus = [{
	"menuId": "1",
	"icon": "icon-sys",
	"menuName": "权限管理",
	"children": [{
		"menuId": "11",
		"menuName": "菜单管理",
		"icon": "icon-add",
		"url": "/sysMenuManage"
	},
	{
		"menuId": "12",
		"menuName": "用户管理",
		"icon": "icon-add",
		"url": "/sysUserManage"
	},
	{
		"menuId": "13",
		"menuName": "角色管理",
		"icon": "icon-add",
		"url": "/sysRoleManage"
	}]
}];

function initLeftMenu(menuArray,navigatorId){
	$.each(menuArray,function(i,n){
		var menuList = "";
		
		var children = n.children;
		if(children != undefined){
			$.each(children,function(k,m){
				menuList += "<li><span class='icon " + m.icon +"'>&nbsp;</span><a>" + m.menuName +"</a></li>";
			});
			menuList = "<ul id='nav'>" + menuList +"</ul>";
		}
		
		$("#"+navigatorId).accordion("add", {
            title: n.menuName,
            content: menuList,
			border:false,
            iconCls: "icon " + n.icon
        });
	});
}


