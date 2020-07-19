package com.project.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.system.domain.SysUser;

/**
 * 用户表 数据层
 */
public interface SysUserMapper
{
    public List<SysUser> selectUserList(SysUser sysUser);

    public SysUser selectUserByUserName(String userName);

    public SysUser selectUserById(Long userId);

    public int insertUser(SysUser user);

    public int updateUser(SysUser user);

    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    public int deleteUserById(Long userId);

    public int deleteUserByIds(Long[] userIds);

    public int checkUserNameUnique(String userName);

    public SysUser checkPhoneUnique(String phonenumber);

    public SysUser checkEmailUnique(String email);
}
