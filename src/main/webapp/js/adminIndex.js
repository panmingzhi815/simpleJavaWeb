var _menus = [{
			"menuId": "1",
			"icon": "icon-promession",
			"menuName": "系统权限",
			"child": [{
				"menuId": "11",
				"menuName": "用户管理",
				"icon": "icon-user",
				"url": "/admin/user/userManager"
			},{
				"menuId": "12",
				"menuName": "角色管理",
				"icon": "icon-role",
				"url": "/admin/role/roleManager"
			},{
				"menuId": "13",
				"menuName": "菜单管理",
				"icon": "icon-menu",
				"url": "/admin/menu/menuManager"
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

        addTab(menuName,url,icon);
        //唯独给当前选中添加样式
        var divArr = $('li div');
        $.each(divArr,function(i,n){
            $(n).removeClass("li_focus")
        });
        $(this).addClass("li_focus");
	}).hover(function(){
		$(this).addClass("li_hover");
	},function(){
		$(this).removeClass("li_hover");
	});
}

function addTab(subtitle,url,icon){
    if ($('#tabs').tabs('exists', subtitle)){
        $('#tabs').tabs('select', subtitle);
    } else {
        var content = '<div style="width: 100%;height: 100%; overflow: hidden"><iframe scrolling="auto" frameborder="0" style="width: 100%;height: 100%;" src="'+url+'"></iframe></div>';
        $('#tabs').tabs('add',{
            title:subtitle,
            content:content,
            closable:true,
            icon:icon
        });
    }
}

// error,info,question,warning
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}