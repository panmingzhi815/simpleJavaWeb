package org.opensource.webapp.framework.domain;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-10-26
 * Time: 下午5:33
 * 界面控件按钮叵其它可视级别的功能名称
 * eg:
 * name = "审核" or name = "删除" or name = "打印"
 */
@Entity(name = "SysMenuController")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysMenuController extends BasicDomain{
    //控制名称
    private String name;
}
