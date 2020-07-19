package com.project.system.service;

import java.util.List;

import com.project.system.domain.SysUser;

/**
 * 用户 业务层
 */
public interface ISysUserService
{
    public List<SysUser> selectUserList(SysUser user);

    public SysUser selectUserByUserName(String userName);

    public SysUser selectUserById(Long userId);

    public String selectUserRoleGroup(String userName);

    public String selectUserPostGroup(String userName);

    public String checkUserNameUnique(String userName);

    public String checkPhoneUnique(SysUser user);

    public String checkEmailUnique(SysUser user);

    public void checkUserAllowed(SysUser user);

    public int insertUser(SysUser user);

    public int updateUser(SysUser user);

    public int updateUserStatus(SysUser user);

    public int updateUserProfile(SysUser user);

    public boolean updateUserAvatar(String userName, String avatar);

    public int resetPwd(SysUser user);

    public int resetUserPwd(String userName, String password);

    public int deleteUserById(Long userId);

    public int deleteUserByIds(Long[] userIds);

    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);
}
