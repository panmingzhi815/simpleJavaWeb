package org.opensource.webapp.framework.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/admin")
public class adminAction {

    public final String ADMIN_EASYUI_THEME = "easyui-theme";
	
	@RequestMapping(value="/adminIndex")
	public String adminIndex(HttpServletRequest req){
        processEasyuiTheme(req);
		return "admin/adminIndex";	
	}
	
	@RequestMapping(value="/adminLogin")
	public String adminLogin(Map<String, String> map){
		return "admin/adminLogin";	
	}

    //切换easyui主题
    public void processEasyuiTheme(HttpServletRequest req){
        String theme = "default";
        if(req.getSession().getAttribute(ADMIN_EASYUI_THEME) != null){
            theme = req.getParameter(ADMIN_EASYUI_THEME);
        }
        req.getSession().setAttribute(ADMIN_EASYUI_THEME,theme);
    }

}