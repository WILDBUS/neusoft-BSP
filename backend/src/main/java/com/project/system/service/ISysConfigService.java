package com.project.system.service;

import java.util.List;

import com.project.system.domain.SysConfig;

/**
 * 参数配置 服务层
 */
public interface ISysConfigService
{
    public SysConfig selectConfigById(Long configId);

    public String selectConfigByKey(String configKey);

    public List<SysConfig> selectConfigList(SysConfig config);

    public int insertConfig(SysConfig config);

    public int updateConfig(SysConfig config);

    public int deleteConfigByIds(Long[] configIds);

    public void clearCache();

    public String checkConfigKeyUnique(SysConfig config);
}
