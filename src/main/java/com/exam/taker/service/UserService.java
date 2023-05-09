package com.exam.taker.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.taker.vo.UserVo;
import com.exam.taker.dto.QueryUserDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
public interface UserService extends IService<User> {
    User getUserByHone(String userName);

    boolean isRecordExist(String phone, String uuid);

    List<UserVo> getList(QueryUserDto queryUserDto);

    Page<UserVo> getPage(QueryUserDto queryUserDto);
}
