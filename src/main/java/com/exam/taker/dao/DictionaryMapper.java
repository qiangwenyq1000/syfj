package com.exam.taker.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.taker.dto.DictionaryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    List<Dictionary> getList(@Param("params") DictionaryDto params);

    List<Dictionary> getList(Page<Dictionary> page, @Param("params") DictionaryDto params);

}
