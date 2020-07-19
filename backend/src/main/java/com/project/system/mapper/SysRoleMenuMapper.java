package com.project.system.mapper;

import java.util.List;

import com.project.system.domain.SysRoleMenu;

/**
 * 角色与菜单关联表 数据层
 */
public interface SysRoleMenuMapper
{
    public int checkMenuExistRole(Long menuId);

    public int deleteRoleMenuByRoleId(Long roleId);

    public int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
