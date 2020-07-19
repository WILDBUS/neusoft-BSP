package com.project.system.service;

import java.util.List;

import com.project.system.domain.SysDictData;

/**
 * 字典 业务层
 */
public interface ISysDictDataService
{
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    public String selectDictLabel(String dictType, String dictValue);

    public SysDictData selectDictDataById(Long dictCode);

    public int deleteDictDataByIds(Long[] dictCodes);

    public int insertDictData(SysDictData dictData);

    public int updateDictData(SysDictData dictData);
}
