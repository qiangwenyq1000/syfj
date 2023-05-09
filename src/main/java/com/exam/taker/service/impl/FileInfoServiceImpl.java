package com.exam.taker.service.impl;

import com.exam.taker.domain.FileInfo;
import com.exam.taker.dao.FileInfoMapper;
import com.exam.taker.service.FileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

}
