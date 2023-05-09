package com.exam.taker.controller;

import com.exam.taker.importUtil.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "模版管理")
@Controller
@RequestMapping("/template")
public class TemplateController {
    @ApiOperation("下载中考学生基础信息导入模版")
    @RequestMapping(value="/download/base",method= RequestMethod.GET)
    public void downloadBase(HttpServletRequest request, HttpServletResponse resp){
        List<FiledEnum> filedEnums= ImportTools.getFiledList(ModelEnum.STUDENT_GRADUATION.getModelKey());
        HSSFWorkbook book= ExportTools.createWorkBook(null,filedEnums);
        ExportTools.downloadFile(request,resp,book,"学生基础信息模版");
    }

    @ApiOperation("下载中考学生成绩信息导入模版")
    @RequestMapping(value="/download/make",method= RequestMethod.GET)
    public void downloadMake(HttpServletRequest request, HttpServletResponse resp){
        List<FiledEnum> filedEnums= ImportTools.getFiledList(ModelEnum.STUDENT_MAKE.getModelKey());
        HSSFWorkbook book= ExportTools.createWorkBook(null,filedEnums);
        ExportTools.downloadFile(request,resp,book,"学生成绩信息模版");
    }

    @ApiOperation("下载中考学生普高录取信息导入模版")
    @RequestMapping(value="/download/enroll",method= RequestMethod.GET)
    public void downloadEnroll(HttpServletRequest request, HttpServletResponse resp){
        List<FiledEnum> filedEnums= ImportTools.getFiledList(ModelEnum.STUDENT_ENROLL.getModelKey());
        HSSFWorkbook book= ExportTools.createWorkBook(null,filedEnums);
        ExportTools.downloadFile(request,resp,book,"学生普高录取信息模版");
    }

    @ApiOperation("下载中考学生毕业去向信息导入模版")
    @RequestMapping(value="/download/enrollQX",method= RequestMethod.GET)
    public void downloadEnrollQX(HttpServletRequest request, HttpServletResponse resp){
        List<FiledEnum> filedEnums= ImportTools.getFiledList(ModelEnum.STUDENT_ENROLL_ZZ.getModelKey());
        HSSFWorkbook book= ExportTools.createWorkBook(null,filedEnums);
        ExportTools.downloadFile(request,resp,book,"学生毕业去向信息模版");
    }

}
