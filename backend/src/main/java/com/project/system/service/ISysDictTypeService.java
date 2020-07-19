package com.project.system.service;

import java.util.List;

import com.project.system.domain.SysDictData;
import com.project.system.domain.SysDictType;

/**
 * 字典 业务层
 */
public interface ISysDictTypeService
{

    public List<SysDictType> selectDictTypeList(SysDictType dictType);

    public List<SysDictType> selectDictTypeAll();

    public List<SysDictData> selectDictDataByType(String dictType);

    public SysDictType selectDictTypeById(Long dictId);

    public SysDictType selectDictTypeByType(String dictType);

    public int deleteDictTypeByIds(Long[] dictIds);

    public void clearCache();

    public int insertDictType(SysDictType dictType);

    public int updateDictType(SysDictType dictType);

    public String checkDictTypeUnique(SysDictType dictType);
}
