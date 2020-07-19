package com.project.system.mapper;

import java.util.List;

import com.project.system.domain.SysConfig;

/**
 * 参数配置 数据层
 */
public interface SysConfigMapper
{

    public SysConfig selectConfig(SysConfig config);

    public List<SysConfig> selectConfigList(SysConfig config);

    public SysConfig checkConfigKeyUnique(String configKey);

    public int insertConfig(SysConfig config);

    public int updateConfig(SysConfig config);

    public int deleteConfigById(Long configId);

    public int deleteConfigByIds(Long[] configIds);
}