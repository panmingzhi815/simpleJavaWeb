package org.opensource.webapp.framework.domain;

import org.opensource.webapp.framework.domain.enums.SysExpressionEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-10-26
 * Time: 下午5:42
 * 系统菜单的表达式方式
 * eg:
     * name = "审批小于1000元的订款单"
     * expressionKey = "order.money"
     * sysMenuExpression = sysMenuExpression.小于
     * expressionValue = 1000
 */
@Entity(name = "SysMenuExpression")
public class SysMenuExpression extends BasicDomain{
    //表达式名称,分配数据级别权限时,直接只显示此功能名称给用户
    private String name;
    //数据级别表达式的key,此key应对jpa的数据表达式
    private String expressionKey;
    //表达式的操作方式
    @Enumerated(value = EnumType.STRING)
    private SysExpressionEnum sysExpressionEnum;
    //表达式的对应的控件数据范围
    private String expressionValue;

    public SysMenuExpression() {
    }

    public SysMenuExpression(String name, String expressionKey, SysExpressionEnum sysExpressionEnum, String expressionValue) {
        this.name = name;
        this.expressionKey = expressionKey;
        this.sysExpressionEnum = sysExpressionEnum;
        this.expressionValue = expressionValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpressionKey() {
        return expressionKey;
    }

    public void setExpressionKey(String expressionKey) {
        this.expressionKey = expressionKey;
    }

    public SysExpressionEnum getSysExpressionEnum() {
        return sysExpressionEnum;
    }

    public void setSysExpressionEnum(SysExpressionEnum sysExpressionEnum) {
        this.sysExpressionEnum = sysExpressionEnum;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(String expressionValue) {
        this.expressionValue = expressionValue;
    }
}
