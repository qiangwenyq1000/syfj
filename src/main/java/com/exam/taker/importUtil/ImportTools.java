package com.exam.taker.importUtil;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.exam.taker.constant.ImportConst;
import com.exam.taker.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.jni.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImportTools {
    public static List<FiledEnum>  getFiledList(String model){
        List<FiledEnum> list= Arrays.stream(FiledEnum.values()).filter(filedEnum -> model.equals(filedEnum.getModel())).collect(Collectors.toList());
        return list;
    }

    public static boolean confirmTemp(Row header, List<FiledEnum> list){
        int cellNum=header.getLastCellNum();
        if(cellNum<list.size()){
            return false;
        }
        for(FiledEnum item:list){
            String cellName= CommonUtils.getCellValue(header,item.getSortNum());
            if(StringUtils.isEmpty(cellName)||!cellName.contains(item.getFiledName())){
                return false;
            }
        }
        return true;
    }

    public static Object rowToData(Row row,List<FiledEnum> list,String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class c=Class.forName(className);
        Object obj=c.newInstance();
        Integer filedTipCode=1;
        String filedTip="";
        for (FiledEnum item:list){
            Field field=c.getDeclaredField(item.getFiledKey());
            String filedType=field.getType().getSimpleName();
            String cellValue=CommonUtils.getCellValue(row,item.getSortNum());
            if(item.isRequired()&&StringUtils.isEmpty(cellValue)){
                filedTipCode=0;
                filedTip="【"+item.getFiledName()+"】不能为空";
                continue;
            }
            if(item.isSecurity()){

            }
            if(StringUtils.isEmpty(cellValue)){
                continue;
            }
            field.setAccessible(true);
            if(filedType.equals("String")){
                field.set(obj,cellValue);
            }else if(filedType.equals("Integer") || filedType.equals("int")){
                field.set(obj,Integer.valueOf(cellValue));
            }else if(filedType.equals("Double") || filedType.equals("Double")){
                field.set(obj,Double.valueOf(cellValue));
            }else if(filedType.equals("Long")||filedType.equals("long")){
                field.set(obj,Long.valueOf(cellValue));
            }else if(filedType.equals("Date")){
                field.set(obj,CommonUtils.getDateSimple(cellValue,ImportConst.DAY_FORMAT));
            }
        }
        Field tipCodeField=c.getDeclaredField("filedTipCode");
        tipCodeField.setAccessible(true);
        tipCodeField.set(obj,String.valueOf(filedTipCode));
        Field tipField=c.getDeclaredField("filedTip");
        tipField.setAccessible(true);
        tipField.set(obj,filedTip);
        return obj;
    }

    public static List<Object> readData(Sheet sheet, List<FiledEnum> list,String className) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Object> objs=new ArrayList<>();
        int rowNumber=sheet.getLastRowNum();
        for (int j = 1; j <= rowNumber; j++) {
            Row row = sheet.getRow(j);
            Object record=rowToData(row,list,className);
            /*if(record !=null){
               objs.add(record);
            }*/
            if(ObjectUtils.isNotEmpty(record)){
                objs.add(record);
            }
        }
        return objs;
    }

    public static Workbook confirmFile(MultipartFile file) throws IOException {
        Workbook book;
        String fileName = file.getOriginalFilename();
        String suff =fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        InputStream is=file.getInputStream();
        // 如果是office 2003及以下版本
        if (suff.equalsIgnoreCase("xls")) {
            book = new HSSFWorkbook(is);
        } else if (suff.equalsIgnoreCase("xlsx")) {
            // 如果是office 2007及以上版本
            book = new XSSFWorkbook(is);
        }else{
            return null;
        }
        return book;
    }

    public static Sheet confirmData(Workbook book){
        int sheetNum = book.getNumberOfSheets();
        if(sheetNum<1){
            return null;
        }
        Sheet sheet = book.getSheetAt(0);
        int rowNumber = sheet.getLastRowNum(); // 获得sheet页最大行数
        if (rowNumber < 1) {
            return null;
        }
        return sheet;
    }

}
