package com.exam.taker.importUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.taker.util.CommonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ExportTools {
    public static  boolean importRecordFile(JSONArray list,  String fileName, List<FiledEnum> filedEnumList){
        try {
            String path= CommonUtils.getRootPath();
            HSSFWorkbook wbss = new HSSFWorkbook();
            Sheet ss1 = wbss.createSheet("sheet1");
            Row header=ss1.createRow(0);
            HSSFCellStyle headerStyle=createHeaderStyle(wbss);
            for (FiledEnum item:filedEnumList){
                Cell cell=header.createCell(item.getSortNum());
                cell.setCellValue(item.getFiledName());
                cell.setCellStyle(headerStyle);
            }
            Cell headerTipCell=header.createCell(filedEnumList.size());
            headerTipCell.setCellValue("异常说明");
            headerTipCell.setCellStyle(headerStyle);
            if(list !=null && list.size()>0){
                HSSFCellStyle contentStyle=createContentStyle(wbss);
                for(int i=0;i<list.size();i++){
                    JSONObject object=list.getJSONObject(i);
                    Row row=ss1.createRow(i+1);
                    for (FiledEnum item:filedEnumList){
                        if(object.containsKey(item.getFiledKey())){
                            Cell cell=row.createCell(item.getSortNum());
                            cell.setCellValue(object.get(item.getFiledKey()).toString());
                            cell.setCellStyle(contentStyle);
                        }
                    }
                }
            }
            File dir=new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(path+fileName);
            wbss.write(fileOut);
            fileOut.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean saveOldFile(Workbook wb, String fileName){
        try {
            String path= CommonUtils.getRootPath();
            File dir=new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(path+fileName);
            wb.write(fileOut);
            fileOut.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static HSSFCellStyle createHeaderStyle(HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont fontHeader = wb.createFont();
        fontHeader.setFontHeightInPoints((short) 11);
        fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(fontHeader);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        return style;
    }

    public static HSSFCellStyle createContentStyle(HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");//字体类型
        font.setFontHeightInPoints((short) 10);//字体大小
        style.setFont(font);
        return style;
    }

    public static HSSFWorkbook createWorkBook(JSONArray list, List<FiledEnum> filedEnumList){
        try {
            HSSFWorkbook wbss = new HSSFWorkbook();
            Sheet ss1 = wbss.createSheet("sheet1");
            Row header=ss1.createRow(0);
            HSSFCellStyle headerStyle=createHeaderStyle(wbss);
            for (FiledEnum item:filedEnumList){
                Cell cell=header.createCell(item.getSortNum());
                cell.setCellStyle(headerStyle);
                cell.setCellValue(item.getFiledName());

            }
            if(list !=null && list.size()>0){
                HSSFCellStyle contentStyle=createContentStyle(wbss);
                for(int i=0;i<list.size();i++){
                    JSONObject object=list.getJSONObject(i);
                    Row row=ss1.createRow(i+1);
                    for (FiledEnum item:filedEnumList){
                        if(object.containsKey(item.getFiledKey())){
                            Cell cell=row.createCell(item.getSortNum());
                            cell.setCellStyle(contentStyle);
                            cell.setCellValue(object.get(item.getFiledKey()).toString());
                        }else{
                            Cell cell=row.createCell(item.getSortNum());
                            cell.setCellStyle(contentStyle);
                            cell.setCellValue("");
                        }
                    }
                }
            }
           return wbss;
        }catch (Exception e){
            return null;
        }
    }

    public static  void downloadZip(HttpServletResponse resp, Map<String,HSSFWorkbook> books,String zipName){
        ZipOutputStream zos = null;
        try {
            resp.setContentType("application/x-download");
            resp.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(zipName, "UTF-8"));
            zos = new ZipOutputStream(resp.getOutputStream());
            downloadTolocal(zos, books);
            resp.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadTolocal(ZipOutputStream zos, Map<String,HSSFWorkbook> books) throws IOException {
        for(String key:books.keySet()){
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            HSSFWorkbook book=books.get(key);
            book.write(bos);
            byte[] bytes=bos.toByteArray();
            InputStream input=new ByteArrayInputStream(bytes);
            String fileName=key;
            if(key.equals("")){
                fileName="未知班级学生信息";
            }
            if(fileName.indexOf("班")<0){
                fileName=fileName+"班";
            }
            ZipEntry zipEntry = new ZipEntry(fileName+".xls");
            zos.putNextEntry(zipEntry);
            byte[] buffer=new byte[1024];
            int r=0;
            while((r=input.read(buffer))!=-1){
                zos.write(buffer,0,r);
            }
            input.close();
        }
        zos.close();
    }

    public static void downloadZip2(HttpServletResponse resp, Map<String,HSSFWorkbook> books,String zipName) {
        ZipOutputStream zos = null;
        try {
            resp.setContentType("application/x-download");
            resp.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(zipName, "UTF-8"));
            zos = new ZipOutputStream(resp.getOutputStream());
            for(String key:books.keySet()){
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                HSSFWorkbook book=books.get(key);
                book.write(bos);
                byte[] bytes=bos.toByteArray();
                InputStream input=new ByteArrayInputStream(bytes);
                String fileName=key;
                ZipEntry zipEntry = new ZipEntry(fileName+".xls");
                zos.putNextEntry(zipEntry);
                byte[] buffer=new byte[1024];
                int r=0;
                while((r=input.read(buffer))!=-1){
                    zos.write(buffer,0,r);
                }
                input.close();
            }
            zos.close();
            resp.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb, String fileName){

        OutputStream ouputStream = null;
        try {

            String encoding = "UTF-8";
            /** 获取浏览器相关的信息 */
            String userAgent = request.getHeader("user-agent");
            /** 判断是否为msie浏览器 */
            if (userAgent.toLowerCase().indexOf("msie") != -1){
                encoding = "gbk";
            }

            response.setCharacterEncoding(encoding);
            response.setContentType("application/vnd.ms-excel");
           // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
            fileName += ".xls";
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, encoding));
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(ouputStream!=null) {
                    ouputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
