package com.snake19870227.stiger.admin.entity.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author buhuayang
 * @since 2020-03-16
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 菜单流水号
     */
    @TableId(value = "menu_flow", type = IdType.ASSIGN_UUID)
    private String menuFlow;

    /**
     * 父级菜单流水号
     */
    private String parentMenuFlow;

    /**
     * 菜单层级
     */
    private Integer menuLevel;

    /**
     * 菜单代码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单地址
     */
    private String menuPath;

    /**
     * 排序码
     */
    private Integer menuOrder;


    public String getMenuFlow() {
        return menuFlow;
    }

    public SysMenu setMenuFlow(String menuFlow) {
        this.menuFlow = menuFlow;
        return this;
    }

    public String getParentMenuFlow() {
        return parentMenuFlow;
    }

    public SysMenu setParentMenuFlow(String parentMenuFlow) {
        this.parentMenuFlow = parentMenuFlow;
        return this;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public SysMenu setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
        return this;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public SysMenu setMenuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public SysMenu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public SysMenu setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        return this;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public SysMenu setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
        return this;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        "menuFlow=" + menuFlow +
        ", parentMenuFlow=" + parentMenuFlow +
        ", menuLevel=" + menuLevel +
        ", menuCode=" + menuCode +
        ", menuName=" + menuName +
        ", menuPath=" + menuPath +
        ", menuOrder=" + menuOrder +
        "}";
    }
}
