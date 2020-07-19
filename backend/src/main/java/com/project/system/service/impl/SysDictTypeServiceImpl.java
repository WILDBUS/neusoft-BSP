package com.project.system.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;

import com.common.constant.UserConstants;
import com.common.exception.CustomException;
import com.common.utils.DictUtils;
import com.common.utils.StringUtils;
import com.project.system.domain.SysDictData;
import com.project.system.domain.SysDictType;
import com.project.system.mapper.SysDictDataMapper;
import com.project.system.mapper.SysDictTypeMapper;
import com.project.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典 业务层处理
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService
{
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @PostConstruct
    public void init()
    {
        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (SysDictType dictType : dictTypeList)
        {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
            DictUtils.setDictCache(dictType.getDictType(), dictDatas);
        }
    }

    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType)
    {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public List<SysDictType> selectDictTypeAll()
    {
        return dictTypeMapper.selectDictTypeAll();
    }

    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotNull(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotNull(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }

    @Override
    public SysDictType selectDictTypeById(Long dictId)
    {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    public SysDictType selectDictTypeByType(String dictType)
    {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    public int deleteDictTypeByIds(Long[] dictIds)
    {
        for (Long dictId : dictIds)
        {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0)
            {
                throw new CustomException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        int count = dictTypeMapper.deleteDictTypeByIds(dictIds);
        if (count > 0)
        {
            DictUtils.clearDictCache();
        }
        return count;
    }

    public void clearCache()
    {
        DictUtils.clearDictCache();
    }

    @Override
    public int insertDictType(SysDictType dictType)
    {
        int row = dictTypeMapper.insertDictType(dictType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    @Override
    @Transactional
    public int updateDictType(SysDictType dictType)
    {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row = dictTypeMapper.updateDictType(dictType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    @Override
    public String checkDictTypeUnique(SysDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
