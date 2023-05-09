package com.exam.taker.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.taker.dto.SysLogDto;
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
public interface SysLogMapper extends BaseMapper<SysLog> {

    List<SysLog> getList(@Param("params") SysLogDto params);

    List<SysLog> getList(Page<SysLog> page, @Param("params") SysLogDto params);

}
