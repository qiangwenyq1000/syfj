package com.exam.taker.controller;


import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.ReturnConst;
import com.exam.taker.domain.Dictionary;
import com.exam.taker.dto.DictionaryDto;
import com.exam.taker.dto.DictionaryListDto;
import com.exam.taker.service.DictionaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author lijiaxing
 * @since 2022-06-13
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation(value = "获取字典列表", notes = "传入ID")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseInfo<List<Dictionary>> getList(@RequestBody DictionaryListDto record) {
        ResponseInfo<List<Dictionary>> result = new ResponseInfo<List<Dictionary>>();
        try {
            DictionaryDto params = new DictionaryDto();
            BeanUtils.copyProperties(record, params);
            List<Dictionary> list = dictionaryService.getList(params);
            result.setData(list);
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}

