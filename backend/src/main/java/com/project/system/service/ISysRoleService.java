package com.project.system.service;

import java.util.List;
import java.util.Set;

import com.project.system.domain.SysRole;

/**
 * 角色业务层
 */
public interface ISysRoleService
{
    public List<SysRole> selectRoleList(SysRole role);

    public Set<String> selectRolePermissionByUserId(Long userId);

    public List<SysRole> selectRoleAll();

    public List<Integer> selectRoleListByUserId(Long userId);

    public SysRole selectRoleById(Long roleId);

    public String checkRoleNameUnique(SysRole role);

    public String checkRoleKeyUnique(SysRole role);

    public void checkRoleAllowed(SysRole role);

    public int countUserRoleByRoleId(Long roleId);

    public int insertRole(SysRole role);

    public int updateRole(SysRole role);

    public int updateRoleStatus(SysRole role);

    public int authDataScope(SysRole role);

    public int deleteRoleById(Long roleId);

    public int deleteRoleByIds(Long[] roleIds);
}
