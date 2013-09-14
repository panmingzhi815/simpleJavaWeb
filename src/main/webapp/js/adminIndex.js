var _menus = [{
			"menuId": "1",
			"icon": "icon-promession",
			"menuName": "系统权限",
			"child": [{
				"menuId": "11",
				"menuName": "用户管理",
				"icon": "icon-user",
				"url": "/admin/userManager"
			},{
				"menuId": "12",
				"menuName": "角色管理",
				"icon": "icon-role",
				"url": "/admin/roleManager"
			},{
				"menuId": "13",
				"menuName": "菜单管理",
				"icon": "icon-menu",
				"url": "/admin/menuManager"
			}]
	}];

$(function(){
	InitLeftMenu();
});

function InitLeftMenu() {
    $.each(_menus, function(i, n) {
		var menulist ="<ul class='nav'>";
        $.each(n.child, function(j, o) {
			menulist += "<li><div url='" + o.url + "' menuName='" + o.menuName +"' icon='" + o.icon + "' >"+
							"<span class='"+o.icon+"' >&nbsp;&nbsp;&nbsp;&nbsp;</span><span>" + o.menuName + "</span>"
						+"</div></li>";
        });
		menulist += '</ul>';
		
		$('#nav').accordion('add', {
            title: n.menuName,
            content: menulist,
			border:false,
            iconCls: n.icon
        });
    });

	$('li div').click(function(){
		var menuName = $(this).attr("menuName");
		var url = $(this).attr("url");
		var icon = $(this).attr("icon");
		var exists = $("#tabs").tabs("exists",menuName);
		if(exists){
			$("#tabs").tabs("select",menuName);
			 return;
		}
		addIframeTab(menuName,url,icon);
	}).hover(function(){
		$(this).addClass("li_hover");
	},function(){
		$(this).removeClass("li_hover");
	});
}

function addTab(subtitle,url,icon){
	$('#tabs').tabs('add',{
		title:subtitle,
		content:createFrame(url),
		closable:true,
		icon:icon,
		border:false
	});
}

function addIframeTab(subTitle,url,icon){
	$('#tabs').tabs('addIframeTab',{
		tab:{
            title:subTitle,
            icon:icon,
            closable:true,
            tools:[{
                iconCls:'icon-mini-refresh',
                handler:function(e){
                	var title = $(e.target).parent().parent().text();
                    $('#tabs').tabs('updateIframeTab',{'which':title});
                }
            }]
        },
		iframe:{src:url}
	});
    $('#tabs').tabs('addEventParam');
}
    
function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

// error,info,question,warning
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
