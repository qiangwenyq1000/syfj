package com.exam.taker.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.Dictionary;
import com.exam.taker.dao.DictionaryMapper;
import com.exam.taker.dto.DictionaryDto;
import com.exam.taker.service.DictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {


    @Override
    public List<Dictionary> getList(DictionaryDto record) {
        return baseMapper.getList(record);
    }

    @Override
    public Page<Dictionary> getPage(DictionaryDto record) {
        Page<Dictionary> page=new Page<Dictionary>(record.getPageNum(),record.getPageSize());
        return page.setRecords(baseMapper.getList(page,record));
    }

}
