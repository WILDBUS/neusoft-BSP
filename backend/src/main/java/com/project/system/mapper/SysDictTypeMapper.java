package com.project.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.project.system.domain.SysDictType;

/**
 * 字典表 数据层
 */
@Mapper
public interface SysDictTypeMapper
{

    public List<SysDictType> selectDictTypeList(SysDictType dictType);

    public List<SysDictType> selectDictTypeAll();

    public SysDictType selectDictTypeById(Long dictId);

    public SysDictType selectDictTypeByType(String dictType);

    public int deleteDictTypeById(Long dictId);

    public int deleteDictTypeByIds(Long[] dictIds);

    public int insertDictType(SysDictType dictType);

    public int updateDictType(SysDictType dictType);

    public SysDictType checkDictTypeUnique(String dictType);
}
