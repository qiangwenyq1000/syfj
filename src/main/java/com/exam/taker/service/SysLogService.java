package com.exam.taker.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.taker.dto.SysLogDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
public interface SysLogService extends IService<SysLog> {

    boolean isRecordExist(SysLog sysLog, String uuid);

    List<SysLog> getList(SysLogDto record);

    Page<SysLog> getPage(SysLogDto record);

    boolean saveManage(String actType, String status, String actPer);

    boolean saveManage(String actType, String status, String actPer, String actDescription);
}
