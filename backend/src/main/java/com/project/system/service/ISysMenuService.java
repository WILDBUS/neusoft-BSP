package com.project.system.service;

import java.util.List;
import java.util.Set;

import com.framework.web.domain.TreeSelect;
import com.project.system.domain.SysMenu;
import com.project.system.domain.vo.RouterVo;

/**
 * 菜单 业务层
 */
public interface ISysMenuService
{
    public List<SysMenu> selectMenuList(Long userId);

    public List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    public Set<String> selectMenuPermsByUserId(Long userId);

    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    public List<Integer> selectMenuListByRoleId(Long roleId);

    public List<RouterVo> buildMenus(List<SysMenu> menus);

    public List<SysMenu> buildMenuTree(List<SysMenu> menus);

    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    public SysMenu selectMenuById(Long menuId);

    public boolean hasChildByMenuId(Long menuId);

    public boolean checkMenuExistRole(Long menuId);

    public int insertMenu(SysMenu menu);

    public int updateMenu(SysMenu menu);

    public int deleteMenuById(Long menuId);

    public String checkMenuNameUnique(SysMenu menu);
}
