package com.exam.taker.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.common.PageInfo;
import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.AuthConstants;
import com.exam.taker.constant.RequestCodeEnum;
import com.exam.taker.constant.ReturnConst;
import com.exam.taker.domain.User;
import com.exam.taker.dto.QueryUserDto;
import com.exam.taker.security.SelfUserEntity;
import com.exam.taker.service.SysLogService;
import com.exam.taker.service.UserService;
import com.exam.taker.util.CommonUtils;
import com.exam.taker.util.DateUtil;
import com.exam.taker.util.MobileUtil;
import com.exam.taker.util.customize.DesensitizedUtils;
import com.exam.taker.vo.UserDetailVo;
import com.exam.taker.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody ResponseInfo<String> addRecord(User record){
        ResponseInfo<String> result=new ResponseInfo<String>();
        try {
            if (record.getPhone()==null || record.getPhone().equals("")){
                result.setMessage("请填写手机号");
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                return result;
            }

            if (userService.isRecordExist(record.getPhone(),null)){
                result.setMessage("该手机号已生成账号，请直接登录");
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                return result;
            }
            userService.save(record);
            result.setMessage("添加成功。");
            result.setCode(RequestCodeEnum.CODE_OK.getCode());
        }catch (Exception e){
            System.out.println(e.toString());
            result.setCode(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getCode());
            result.setMessage(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public @ResponseBody ResponseInfo<String> editRecord(User record){
        ResponseInfo<String> result=new ResponseInfo<String>();
        try {
            if (record.getPhone()==null || record.getPhone().equals("")){
                result.setMessage("请填写手机号");
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                return result;
            }

            if (userService.isRecordExist(record.getPhone(),record.getUuid())){
                result.setMessage("该手机号已生成账号");
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                return result;
            }
            userService.updateById(record);
            result.setMessage("更新成功。");
            result.setCode(RequestCodeEnum.CODE_OK.getCode());
        }catch (Exception e){
            System.out.println(e.toString());
            result.setCode(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getCode());
            result.setMessage(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getMessage());
        }
        return result;
    }



    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    public @ResponseBody ResponseInfo<String> getCode(String phone,String markCode){
        ResponseInfo<String> result=new ResponseInfo<String>();
        try {
            //1.判断验证码是否正确
            if(ObjectUtils.isEmpty(markCode)){
                result.setCode(ReturnConst.CODE_BAD_REQUEST);
                result.setMessage(ReturnConst.MSG_CODE_NULL_ERROR);
                return result;
            }
            Object object=redisTemplate.opsForValue().get(markCode.toLowerCase());
            //如果能查询到该验证码存在 说明有效
            if(ObjectUtils.isEmpty(object)){
                result.setCode(ReturnConst.CODE_BAD_REQUEST);
                result.setMessage(ReturnConst.MSG_CODE_ERROR);
                return result;
            }
            //使用一次删除
            redisTemplate.delete(markCode.toLowerCase());
             //2.判断手机号码是否正确
            if(ObjectUtils.isEmpty(phone)){
                result.setCode(ReturnConst.CODE_BAD_REQUEST);
                result.setMessage(ReturnConst.MSG_PHONE_NULL_ERROR);
                return result;
            }
            //查询手号码是否存在于数据库中
            if (!userService.isRecordExist(phone,null)){
                result.setCode(ReturnConst.CODE_BAD_REQUEST);
                result.setMessage(ReturnConst.MSG_PHONE_QUERTNULL_ERROR);
                return result;
            }
            //3.手机号码发送验证频率限制
            Boolean flag=verify(phone,60);
            if(flag){
                //能发送
            }else{
                //不能发送 发送太频繁 请60S以后在试
                result.setCode(ReturnConst.CODE_BAD_REQUEST);
                result.setMessage("服务器忙，请60秒以后在试");
                return result;
            }

            Boolean flagCount=verifyCount(phone,6000,5);
            //4.手机号码10分钟 最多发送5次
            if(flagCount){
                //能发送
            }else{
                //不能发送 发送太频繁 请60S以后在试
                result.setCode(ReturnConst.CODE_BAD_REQUEST);
                result.setMessage("服务器忙，10分钟最多发送5次");
                return result;
            }

            String phoneCode= CommonUtils.getPhoneCode();
            redisTemplate.opsForValue().set(phone,phoneCode,300,TimeUnit.SECONDS);
            String message="验证码："+phoneCode+"，该验证码5分钟内有效，如非本人操作，请忽略本短信。";
            MobileUtil.sendMobileMsg(phone,message);
            result.setMessage("验证码发送成功");
            result.setCode(ReturnConst.CODE_OK);
        }catch (Exception e){
            System.out.println(e.toString());
            result.setCode(ReturnConst.CODE_BAD_REQUEST);
            result.setMessage("服务异常");
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取当前登录用户")
    @GetMapping("/currentUser")
    public @ResponseBody ResponseInfo<UserDetailVo> currentUser() {
        ResponseInfo<UserDetailVo> result=new ResponseInfo<>();
        try {
            Object pricipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SelfUserEntity user;
            if("anonymousUser".equals(pricipal)) {
                user = null;
            }else {
                user = (SelfUserEntity) pricipal;
            }
            if(user==null){
                result.setCode(RequestCodeEnum.USER_NOT_FOUND.getCode());
                result.setMessage("未发现当前登录用户信息");
                //logService.log("user","user","获取当前登录用户","失败:当前用户不存在","", "");
                return result;
            }

            User userInfo=userService.getById(user.getUserId());
            UserDetailVo userDetailVo=new UserDetailVo();
            BeanUtils.copyProperties(userInfo,userDetailVo);
            result.setCode(RequestCodeEnum.CODE_OK.getCode());
            result.setData(userDetailVo);
            result.setMessage("查询成功");
        }catch (Exception e){
            result.setCode(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getCode());
            result.setMessage(RequestCodeEnum.CODE_INTERNAL_SERVER_ERROR.getMessage());
        }
        return  result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody ResponseInfo<String> logout(HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        sysLogService.saveManage("logout","1",null);
        ResponseInfo<String> result=new ResponseInfo<String>();
        String token=request.getHeader(AuthConstants.AUTHORIZATION_KEY);
        Object obj = redisTemplate.boundSetOps(AuthConstants.TOKEN_BLACK_MAP).add(token);
        result.setMessage("退出成功");
        result.setCode(ReturnConst.CODE_OK);
        return result;
    }


    @ApiOperation(value = "获取用户信息", notes = "传入ID")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ResponseInfo<Object> getRecord(String uuid) {
        ResponseInfo<Object> result = new ResponseInfo<Object>();
        try {

            User record = userService.getById(uuid);
            result.setData(DesensitizedUtils.getJson(record));
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "获取用户分页列表", notes = "传入ID")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public @ResponseBody String getPage(@RequestBody QueryUserDto record) {
        ResponseInfo<PageInfo<UserVo>> result = new ResponseInfo<PageInfo<UserVo>>();
        try {
            Page<UserVo> page = userService.getPage(record);
            result.setData(new PageInfo(page));
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return DesensitizedUtils.getJson(result);
    }

    public boolean verify(String phoneNumber,int time) {
        if(ObjectUtils.isEmpty(phoneNumber)){
            //手机号码为空
            return false;
        }
        if(ObjectUtils.isEmpty(time)){
            //限制时间
            return false;
        }
        if(time<0){
            //限制时间
            return false;
        }
        String phoneKey=phoneNumber+"time";
        Object object=redisTemplate.opsForValue().get(phoneKey);
        //查询不到记录说明第一次使用 可以发送验证码
        if(ObjectUtils.isEmpty(object)){
            //为空
            //生成对应的时间 有效性值为60S
            redisTemplate.opsForValue().set(phoneKey,DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),60,TimeUnit.SECONDS);
            return true;
        }else{
            //有值 通过时间对比
            if(DateUtil.secondsBetween(DateUtil.parseDate(object.toString(),"yyyy-MM-dd HH:mm:ss"), new Date())>time){
                //覆盖最后一次发送短信时间 有效性值为60S
                redisTemplate.opsForValue().set(phoneKey,DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),60,TimeUnit.SECONDS);
                return true;
            }
        }
        return false;
    }

    //time 时间长度   count 次数
    public boolean verifyCount(String phoneNumber,int time,int count) {

        if(ObjectUtils.isEmpty(phoneNumber)){
            //手机号码为空
            return false;
        }
        if(ObjectUtils.isEmpty(time)){
            //限制时间
            return false;
        }
        if(time<0){
            //限制时间
            return false;
        }
        if(ObjectUtils.isEmpty(count)){
            //限制次数
            return false;
        }
        if(count<0){
            //限制次数
            return false;
        }
        String phoneKey=phoneNumber+"minute";
        Object object=redisTemplate.opsForValue().get(phoneKey);
        //
        //查询不到记录说明第一次使用 可以发送验证码
        if(ObjectUtils.isEmpty(object)){
            //为空
            String conRedis="";
            String sb=DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
            int bcount=1;
            conRedis=sb+","+bcount;
            //生成对应的时间 有效性值为60S
            redisTemplate.opsForValue().set(phoneKey,conRedis,6000,TimeUnit.SECONDS);
            return true;
        }else{
            //存在
            String conRedis="";
            //解析 时间和次数
            String objectMin=object.toString();
            String[] objectMins=objectMin.split(",");
            Date s=DateUtil.parseDate(objectMins[0],"yyyy-MM-dd HH:mm:ss");
            //通过第一次时间对比 超过10分钟
            if(DateUtil.secondsBetween(s, new Date())>time){
                //覆盖最后一次发送短信时间
                //超过十分钟 存储一个新的缓存
                String sb=DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
                conRedis=sb+","+1;
                redisTemplate.opsForValue().set(phoneKey,conRedis,6000,TimeUnit.SECONDS);
                return true;
            }else{
                //十分钟以内  判断发送了几次
                int bcount=Integer.parseInt(objectMins[1].toString());
                if(bcount<=count){
                    conRedis=objectMins[0]+","+(bcount+1);
                    //在可以发送的范围
                    redisTemplate.opsForValue().set(phoneKey,conRedis,6000,TimeUnit.SECONDS);
                    return true;
                }
            }
        }
        return false;
    }

}

