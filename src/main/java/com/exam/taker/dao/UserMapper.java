package com.exam.taker.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.taker.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.exam.taker.dto.QueryUserDto;

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
public interface UserMapper extends BaseMapper<User> {

    User getUserByHone(String phone);

    List<UserVo> queryList(@Param("params") QueryUserDto queryUserDto);

    List<UserVo> queryList(Page<UserVo> page, @Param("params") QueryUserDto queryUserDto);
}
