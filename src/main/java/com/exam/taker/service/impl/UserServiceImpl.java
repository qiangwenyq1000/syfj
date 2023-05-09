package com.exam.taker.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.User;
import com.exam.taker.dao.UserMapper;
import com.exam.taker.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.taker.vo.UserVo;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import com.exam.taker.dto.QueryUserDto;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getById(Serializable uuid){
        return baseMapper.selectById(uuid);
    }

    @Override
    public User getUserByHone(String phone) {
        return baseMapper.getUserByHone(phone);
    }

    @Override
    public boolean isRecordExist(String phone, String uuid) {
        User user=baseMapper.getUserByHone(phone);
        if(user==null){
            return false;
        }
        if(uuid !=null && !uuid.equals(user.getUuid())){
            return false;
        }
        return true;
    }

    @Override
    public List<UserVo> getList(QueryUserDto queryUserDto) {
        return baseMapper.queryList(queryUserDto);
    }

    @Override
    public Page<UserVo> getPage(QueryUserDto queryUserDto) {
        Page<UserVo> page=new Page<UserVo>(queryUserDto.getPageNum(),queryUserDto.getPageSize());
        return page.setRecords(baseMapper.queryList(page,queryUserDto));
    }
}
