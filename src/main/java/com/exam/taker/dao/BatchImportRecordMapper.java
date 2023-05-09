package com.exam.taker.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.BatchImportRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.taker.dto.BatchImportRecordDto;
import com.exam.taker.vo.BatchImportRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Mapper
public interface BatchImportRecordMapper extends BaseMapper<BatchImportRecord> {

    List<BatchImportRecordVo> getList(@Param("params") BatchImportRecordDto params);

    List<BatchImportRecordVo> getList(Page<BatchImportRecordVo> page, @Param("params") BatchImportRecordDto params);
}
