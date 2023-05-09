package com.exam.taker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.BatchImportRecord;
import com.exam.taker.dao.BatchImportRecordMapper;
import com.exam.taker.dto.BatchImportRecordDto;
import com.exam.taker.service.BatchImportRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.taker.vo.BatchImportRecordVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Service
public class BatchImportRecordServiceImpl extends ServiceImpl<BatchImportRecordMapper, BatchImportRecord> implements BatchImportRecordService {

    @Override
    public boolean isRecordExist(BatchImportRecord batchImportRecord, String uuid) {
        QueryWrapper<BatchImportRecord> queryWrapper=new QueryWrapper<BatchImportRecord>(batchImportRecord);
        List<BatchImportRecord> list=baseMapper.selectList(queryWrapper);
        if (list==null || list.size()<=0){
            return  false;
        }
        if (uuid!=null&&uuid.equals(list.get(0).getUuid())){
            return false;
        }
        return true;
    }

    @Override
    public List<BatchImportRecordVo> getList(BatchImportRecordDto record) {
        return baseMapper.getList(record);
    }

    @Override
    public Page<BatchImportRecordVo> getPage(BatchImportRecordDto record) {
        Page<BatchImportRecordVo> page=new Page<BatchImportRecordVo>(record.getPageNum(),record.getPageSize());
        return page.setRecords(baseMapper.getList(page,record));
    }

}
