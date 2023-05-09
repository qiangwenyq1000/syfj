package com.exam.taker.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.BatchImportRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.taker.dto.BatchImportRecordDto;
import com.exam.taker.vo.BatchImportRecordVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
public interface BatchImportRecordService extends IService<BatchImportRecord> {

    boolean isRecordExist(BatchImportRecord batchImportRecord, String uuid);

    List<BatchImportRecordVo> getList(BatchImportRecordDto record);

    Page<BatchImportRecordVo> getPage(BatchImportRecordDto record);

}
