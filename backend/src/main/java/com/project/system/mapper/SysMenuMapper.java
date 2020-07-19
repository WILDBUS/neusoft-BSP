package com.project.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.project.system.domain.SysMenu;

/**
 * 菜单表 数据层
 */
public interface SysMenuMapper
{

    public List<SysMenu> selectMenuList(SysMenu menu);

    public List<String> selectMenuPerms();

    public List<SysMenu> selectMenuListByUserId(SysMenu menu);

    public List<String> selectMenuPermsByUserId(Long userId);

    public List<SysMenu> selectMenuTreeAll();

    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    public List<Integer> selectMenuListByRoleId(Long roleId);

    public SysMenu selectMenuById(Long menuId);

    public int hasChildByMenuId(Long menuId);

    public int insertMenu(SysMenu menu);

    public int updateMenu(SysMenu menu);

    public int deleteMenuById(Long menuId);

    public SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}
