package com.exam.taker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.domain.SysLog;
import com.exam.taker.dao.SysLogMapper;
import com.exam.taker.dto.SysLogDto;
import com.exam.taker.security.SelfUserEntity;
import com.exam.taker.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.taker.service.UserService;
import com.exam.taker.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private UserService userService;

    @Override
    public boolean isRecordExist(SysLog sysLog, String uuid) {

        QueryWrapper<SysLog> queryWrapper=new QueryWrapper<SysLog>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(ObjectUtils.isNotEmpty(sysLog.getCreatorDate())){
            String format = sdf.format(sysLog.getCreatorDate());
            queryWrapper.apply("date_format(creator_date, '%Y-%m-%d %H:%i:%S') = '"+format+"'");
        }
        if(ObjectUtils.isNotEmpty(sysLog.getActType())){
            queryWrapper.lambda().eq(SysLog::getActType,sysLog.getActType());
        }
        if(ObjectUtils.isNotEmpty(sysLog.getActPar())){
            queryWrapper.lambda().eq(SysLog::getActPar,sysLog.getActPar());
        }
        if(ObjectUtils.isNotEmpty(sysLog.getUserUuid())){
            queryWrapper.lambda().eq(SysLog::getUserUuid,sysLog.getUserUuid());
        }
        /*QueryWrapper<SysLog> queryWrapper=new QueryWrapper<SysLog>();
        queryWrapper.lambda().eq(SysLog::getActType,sysLog.getActType()).
                eq(SysLog::getCreatorDate,sysLog.getCreatorDate())
                .eq(SysLog::getActPar,sysLog.getActPar())
                .eq(SysLog::getUserUuid,sysLog.getUserUuid());*/
        List<SysLog> list=baseMapper.selectList(queryWrapper);
        if (list==null || list.size()<=0){
            return  false;
        }
        if (uuid!=null&&uuid.equals(list.get(0).getUuid())){
            return false;
        }
        return true;
    }

    @Override
    public List<SysLog> getList(SysLogDto record) {
        return baseMapper.getList(record);
    }

    @Override
    public Page<SysLog> getPage(SysLogDto record) {
        Page<SysLog> page=new Page<SysLog>(record.getPageNum(),record.getPageSize());
        return page.setRecords(baseMapper.getList(page,record));
    }


    private String per="login";
    @Override
    public boolean saveManage(String actType,String status,String actPer){
        Object pricipal =null;
        String userUuid="";
        if(SecurityContextHolder.getContext().getAuthentication()!=null&&!SecurityContextHolder.getContext().getAuthentication().equals("")
                &&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null&&!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("")) {
            pricipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        SelfUserEntity user;
        if("anonymousUser".equals(pricipal)) {
            user = null;
            //return false;
        }else {
            user = (SelfUserEntity) pricipal;
            if(ObjectUtils.isNotEmpty(user)) {
                userUuid = user.getUserId();
            }
        }
        if(ObjectUtils.isEmpty(status)){
            status="1";
        }
        if(ObjectUtils.isEmpty(actType)){
            return false;
        }
        SysLog record = new SysLog();
        record.setActType(actType);
        record.setActName(CommonUtils.ACT_TYPE_NAME.get(actType));
        record.setCreatorDate(new Date());
        record.setActStatus(status);
        record.setUserUuid(userUuid);
        record.setActPar(actPer);
        return baseMapper.insert(record)>0?true:false;
    }


    @Override
    public boolean saveManage(String actType,String status,String actPer,String actDescription){
        Object pricipal =null;
        String userUuid="";
        if(SecurityContextHolder.getContext().getAuthentication()!=null&&!SecurityContextHolder.getContext().getAuthentication().equals("")
                &&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null&&!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("")) {
            pricipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        SelfUserEntity user;
        if("anonymousUser".equals(pricipal)) {
            user = null;
            //return false;
        }else {
            user = (SelfUserEntity) pricipal;
            if(ObjectUtils.isNotEmpty(user)) {
                userUuid = user.getUserId();
            }
        }
        if(ObjectUtils.isEmpty(status)){
            status="1";
        }
        if(ObjectUtils.isEmpty(actType)){
            return false;
        }
        SysLog record = new SysLog();
        record.setActType(actType);
        record.setActName(CommonUtils.ACT_TYPE_NAME.get(actType));
        record.setCreatorDate(new Date());
        record.setActStatus(status);
        record.setUserUuid(userUuid);
        record.setActPar(actPer);
        record.setActDescription(actDescription);
        return baseMapper.insert(record)>0?true:false;
    }
}
