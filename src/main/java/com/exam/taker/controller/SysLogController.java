package com.exam.taker.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.common.PageInfo;
import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.ReturnConst;
import com.exam.taker.domain.SysLog;
import com.exam.taker.dto.SysLogDto;
import com.exam.taker.dto.SysLogListDto;
import com.exam.taker.service.SysLogService;
import com.exam.taker.util.CommonUtils;
import com.exam.taker.util.customize.AseUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Controller
@EnableScheduling
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${xjbt.query.score}")
    private String queryMark;

    @Value("${xjbt.secretkey}")
    private String secretKey;

    @ApiOperation(value = "添加数据", notes = "传入对象")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    ResponseInfo<String> addRecord(@RequestBody SysLog record) {
        ResponseInfo<String> result = new ResponseInfo<String>();
        try {
            if (StringUtils.isBlank(record.getActType())) {
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                result.setMessage("请选择动作。");
                return result;
            }
            SysLog nameCheck = new SysLog();
            nameCheck.setActType(record.getActType());
            nameCheck.setCreatorDate(record.getCreatorDate());
            nameCheck.setActPar(record.getActPar());
            nameCheck.setUserUuid(record.getUserUuid());
            if (sysLogService.isRecordExist(nameCheck, null)) {
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                result.setMessage("日志记录不能重复添加。");
                return result;
            }
            sysLogService.save(record);
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_ADD_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "删除", notes = "传入数组")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseInfo<String> removeRecord(String[] ids) {
        ResponseInfo<String> result = new ResponseInfo<String>();
        try {
            if (ids == null || ids.length <= 0) {
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                result.setMessage("请选择机构");
                return result;
            }
            sysLogService.removeByIds(Arrays.asList(ids));
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "获取日志信息", notes = "传入ID")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseInfo<SysLog> getRecord(String uuid) {
        ResponseInfo<SysLog> result = new ResponseInfo<SysLog>();
        try {
            SysLog record = sysLogService.getById(uuid);
            result.setData(record);
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "获取日志列表", notes = "传入ID")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseInfo<List<SysLog>> getList(@RequestBody SysLogListDto record) {
        ResponseInfo<List<SysLog>> result = new ResponseInfo<List<SysLog>>();
        try {
            SysLogDto params = new SysLogDto();
            BeanUtils.copyProperties(record, params);
            List<SysLog> list = sysLogService.getList(params);
            result.setData(list);
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "获取日志分页列表", notes = "传入ID")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public @ResponseBody
    ResponseInfo<PageInfo<SysLog>> getPage(@RequestBody SysLogDto record) {
        ResponseInfo<PageInfo<SysLog>> result = new ResponseInfo<PageInfo<SysLog>>();
        try {
            Page<SysLog> page = sysLogService.getPage(record);
            result.setData(new PageInfo(page));
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }



    @Scheduled(cron = "0 0 23 * * ?")
    @ApiOperation(value = "每天23点记录查询缓存中的学生查询记录", notes = "传入ID")
    @RequestMapping(value = "/getQueryMarkRedisInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseInfo<String> getQueryMarkRedisInfo() {
        ResponseInfo<String> result = new ResponseInfo<String>();
        try {
            Date time = new Date();
            //SimpleDateFormat ymdsfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
            String ymdFormat = ymd.format(time);
            String keyRedis=ymdFormat+queryMark;
            //String keyRedis="2022-07-05"+queryMark;
            //根据key 取值
            Object objectRedis=redisTemplate.opsForValue().get(keyRedis);
            if(ObjectUtils.isEmpty(objectRedis)){
                result.setCode(ReturnConst.CODE_BAD_REQUEST);
                result.setMessage(ReturnConst.MSG_BAD_REQUEST);
                return result;
            }
            String conObjectRedis=objectRedis.toString();
            //先取全部学校信息
            String[] conObjectRedissz=conObjectRedis.split("\\|");

            for(int i=0;i<conObjectRedissz.length;i++){
                try {
                    String sb = conObjectRedissz[i];
                    String[] sbsz = sb.split(",");
                    /*for(int j=0;j<sbsz.length;j++){

                    }*/
                    String actPar = sbsz[0];//学号
                    String ctime = sbsz[1];//动作时间
                    String actStatus = sbsz[2];//动作状态 1 成功 0 失败
                    //时间需要装换
                    Date creatorDate = DateUtils.parseDate(ctime, "yyyy-MM-dd HH:mm:ss");
                    String actName = CommonUtils.ACT_TYPE_NAME.get(queryMark);
                    SysLog record = new SysLog();
                    record.setActType(queryMark);
                    record.setActName(actName);
                    record.setCreatorDate(creatorDate);
                    record.setActStatus(actStatus);
                    record.setActPar(actPar);
                    SysLog nameCheck = new SysLog();
                    nameCheck.setActType(record.getActType());
                    nameCheck.setCreatorDate(record.getCreatorDate());
                    nameCheck.setActPar(record.getActPar());
                    nameCheck.setUserUuid(record.getUserUuid());
                    if (sysLogService.isRecordExist(nameCheck, null)) {
                        System.out.println(actPar+"日志记录不能重复添加");
                    }else{
                        if(ObjectUtils.isNotEmpty(objectRedis)) {
                            //密文加密
                            record.setActParAse(AseUtil.encrypt(record.getActPar(), secretKey));
                        }
                        sysLogService.save(record);
                    }
                }catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            result.setData("成功");
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}

