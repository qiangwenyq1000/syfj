package com.exam.taker.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.taker.dto.DictionaryDto;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
public interface DictionaryService extends IService<Dictionary> {

    List<Dictionary> getList(DictionaryDto record);

    Page<Dictionary> getPage(DictionaryDto record);

}
