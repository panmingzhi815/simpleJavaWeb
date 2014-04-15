package org.opensource.webapp.framework.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-10-26
 * Time: 下午5:42
 * 系统菜单的表达式方式
 * eg:
     * name = "审批小于1000元的订款单"
     * expression = "order.money <= 1000"
 */
@Entity(name = "SysMenuExpression")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysMenuExpression extends BasicDomain{
    //表达式名称,分配数据级别权限时,直接只显示此功能名称给用户
    private String name;
    //数据级别表达式
    private String expression;

    public SysMenuExpression() {
    }

    public SysMenuExpression(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
