package com.exam.taker.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.taker.common.PageInfo;
import com.exam.taker.common.ResponseInfo;
import com.exam.taker.constant.ReturnConst;
import com.exam.taker.domain.BatchImportRecord;
import com.exam.taker.dto.BatchImportRecordDto;
import com.exam.taker.dto.BatchImportRecordListDto;
import com.exam.taker.service.BatchImportRecordService;
import com.exam.taker.vo.BatchImportRecordVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
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
@RequestMapping("/batchImportRecord")
public class BatchImportRecordController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BatchImportRecordController.class);

    @Autowired
    private BatchImportRecordService batchImportRecordService;

    @ApiOperation(value = "删除", notes = "传入数组")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseInfo<String> removeRecord(String[] ids) {
        ResponseInfo<String> result = new ResponseInfo<String>();
        try {
            if (ids == null || ids.length <= 0) {
                result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
                result.setMessage("请选择记录");
                return result;
            }
            batchImportRecordService.removeByIds(Arrays.asList(ids));
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "获取导入列表", notes = "传入ID")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    ResponseInfo<List<BatchImportRecordVo>> getList(@RequestBody BatchImportRecordListDto record) {
        ResponseInfo<List<BatchImportRecordVo>> result = new ResponseInfo<List<BatchImportRecordVo>>();
        try {
            BatchImportRecordDto params = new BatchImportRecordDto();
            BeanUtils.copyProperties(record, params);
            List<BatchImportRecordVo> list = batchImportRecordService.getList(params);
            result.setData(list);
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "获取导入列表", notes = "传入ID")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public @ResponseBody
    ResponseInfo<PageInfo<BatchImportRecordVo>> getPage(@RequestBody BatchImportRecordDto record) {
        ResponseInfo<PageInfo<BatchImportRecordVo>> result = new ResponseInfo<PageInfo<BatchImportRecordVo>>();
        try {
            Page<BatchImportRecordVo> page = batchImportRecordService.getPage(record);
            result.setData(new PageInfo(page));
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_OK);
        } catch (Exception e) {
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    public static final String PARAM8 = "UTF-8";

    @ApiOperation(value = "下载附件", notes = "传入ID")
    @RequestMapping(value = "/downloadIo",method = RequestMethod.GET)
    public void downloadIo(String uuid, HttpServletResponse response, HttpServletRequest request) {
        BatchImportRecord bir=batchImportRecordService.getById(uuid);
        try{
            OutputStream out =null;
            if(ObjectUtils.isNotEmpty(bir)) {
                String filePath = URLDecoder.decode(bir.getFilePath(), PARAM8);
                String fileName = URLDecoder.decode(bir.getFileName(), PARAM8);
                File file = new File(filePath);
                if (!file.exists() || file.isDirectory()) {
                    output(response, "您请求的文件不存在或者路径有误！");
                    return;
                }
                InputStream fis = null;
                fis = new FileInputStream(filePath);
                request.setCharacterEncoding("UTF-8");
                String agent = request.getHeader("User-Agent").toUpperCase();
                if ((agent.indexOf("MSIE") > 0) || ((agent.indexOf("RV") != -1) && (agent.indexOf("FIREFOX") == -1)))
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                else {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                }
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Pragma", "public");
                response.setHeader("Cache-Control", "public");
                out = response.getOutputStream();
                byte[] b = new byte[1024];
                int len;
                while ((len = fis.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                response.flushBuffer();
                fis.close();
            }else{
                output(response, "文件不存在或已被删除！");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @ApiOperation(value = "下载附件", notes = "传入ID")
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(String uuid, HttpServletResponse response, HttpServletRequest request) {
        BatchImportRecord bir=batchImportRecordService.getById(uuid);
        try{
            if(ObjectUtils.isNotEmpty(bir)) {
                String filePath = URLDecoder.decode(bir.getFilePath(), PARAM8);
                String fileName = URLDecoder.decode(bir.getFileName(), PARAM8);
                if (filePath == null || filePath.trim().equals("") || response == null) {
                    LOGGER.error("请求参数有误！");
                    return;
                }
                downloadFile(filePath,fileName,response);
            }else{
                output(response, "文件不存在或已被删除！");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }


    private static void downloadFile(String filePath,String fileName,HttpServletResponse response){
        OutputStream out =null;
        try {
            if (filePath == null || filePath.trim().equals("") || response == null) {
                output(response, "请求参数有误！");
                return;
            }

            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                output(response, "您请求的文件不存在或者路径有误！");
                return;
            }
            response.setHeader("Content-disposition", "attachment; filename=\""
                    + new String(fileName.getBytes("GBK"), "ISO-8859-1")+"\"");

            FileChannel fileChannel = new FileInputStream(file).getChannel();

            out = response.getOutputStream();
            WritableByteChannel outChannel = Channels.newChannel(out);

            fileChannel.transferTo(0, file.length(), outChannel);
            out.flush();
            out.close();

        } catch (FileNotFoundException notFound) {
            output(response, "您所请求的文件不存在！");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            output(response, "您所请求的文件出现异常！");
        } catch (Exception e) {
            e.printStackTrace();
            output(response, "您所请求的文件出现异常！");
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

    private static void output(HttpServletResponse response, String message) {
        try {
            response.setContentType("text/html; charset=UTF-8");
            Writer out = response.getWriter();
            out.write(message);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

