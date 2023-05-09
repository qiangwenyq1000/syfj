package com.exam.taker.util;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.exam.taker.constant.ImportConst;
import com.exam.taker.constant.ReturnConst;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CommonUtils {
    public static String getRootPath() {
        return  ImportConst.BATCH_IMPORT_RECORD_PATH;
    }

    public static String getCellValue(Row row, int len) {
        if(null == row.getCell(len) || "".equals(row.getCell(len))){
            return "";
        }
        int cellType=row.getCell(len).getCellType();
        //数字的时候
        if(cellType == Cell.CELL_TYPE_NUMERIC){
            if (!DateUtil.isCellDateFormatted(row.getCell(len))) {
                DecimalFormat df = new DecimalFormat("#.##");
                return df.format(row.getCell(len).getNumericCellValue());
            } else {// 日期
                DateFormat formater = new SimpleDateFormat("yyyyMMdd");
                return formater.format(row.getCell(len).getDateCellValue());
            }
        }
        //字符串的时候
        if(cellType == Cell.CELL_TYPE_STRING){
            return row.getCell(len).getStringCellValue().trim();
        }
        //公式
        if(cellType == Cell.CELL_TYPE_FORMULA){
            return "";
        }
        //空白
        if(cellType == Cell.CELL_TYPE_BLANK){
            return "";
        }
        //Boolean
        if(cellType == Cell.CELL_TYPE_BOOLEAN){
            return String.valueOf(row.getCell(len).getBooleanCellValue());
        }
        //ERROR
        if(cellType == Cell.CELL_TYPE_ERROR){
            return String.valueOf(row.getCell(len).getErrorCellValue());
        }
        return "";
    }

    /**
     * 取EXL的值 里面多个种类型 取值不一样   所以写成统一的方法
     *
     * @param row
     * @return Integer
     */
    public static String getTimeCellValue(Row row, int len) {
        if(null == row.getCell(len) || "".equals(row.getCell(len))){
            return "";
        }
        //数字的时候
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_NUMERIC){
            if (DateUtil.isCellDateFormatted(row.getCell(len))) {// 日期
                DateFormat formater = new SimpleDateFormat("HH:mm:ss");
                return formater.format(row.getCell(len).getDateCellValue());
            } else {
                DecimalFormat df = new DecimalFormat("0");
                return df.format(row.getCell(len).getNumericCellValue());
            }
        }
        //字符串的时候
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_STRING){
            return row.getCell(len).getStringCellValue().trim();
        }
        //公式
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_FORMULA){
            return "";
        }
        //空白
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_BLANK){
            return "";
        }
        //Boolean
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_BOOLEAN){
            return String.valueOf(row.getCell(len).getBooleanCellValue());
        }
        //ERROR
        if(row.getCell(len).getCellType() == Cell.CELL_TYPE_ERROR){
            return String.valueOf(row.getCell(len).getErrorCellValue());
        }
        return "";
    }

    public static int getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }

    public static String getPhoneCode(){
        String s="";
        for (int i=0;i<6;i++){
            s+=String.valueOf(getRandNum(0,9));
        }
        return s;
    }

    public static String getMarkCode(){
        String s="";
        for (int i=0;i<6;i++){
            s+=String.valueOf(getRandNum(0,9));
        }
        return s;
    }

    public static String date2Str(Date inDate, String format) {
        if(inDate==null){
            return "";
        }
        if(format==null||format.equals("")){
            format="yyyy-MM-dd";
        }
        SimpleDateFormat dFormat = new SimpleDateFormat(format);
        return dFormat.format(inDate);
    }

    public static Date getDateSimple(String dateStr, String format) {
        if(dateStr ==null || dateStr.equals("")){
            return null;
        }
        Date retDate = null;
        SimpleDateFormat dt = new SimpleDateFormat();
        try {
            dt.applyPattern(format);
            retDate = dt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retDate;
    }

    public static String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        int month = date.get(Calendar.MONTH);
        if(month<7){
            year = String.valueOf(date.get(Calendar.YEAR)-1);
        }
        return year;
    }


    public static void markRedisInfo(String key, String con,String status, RedisTemplate redisTemplate){
        String conRedis="";
        Date time = new Date();
        SimpleDateFormat ymdsfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ymdsfmFormat = ymdsfm.format(time);
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        String ymdFormat = ymd.format(time);
        if(ObjectUtils.isEmpty(key)){
            return ;
        }

        String keyRedis=ymdFormat+key;
        //根据key 取值
        Object objectRedis=redisTemplate.opsForValue().get(keyRedis);
        conRedis=con+","+ymdsfmFormat+","+status;
        if(ObjectUtils.isEmpty(objectRedis)){
            //缓存不存在的时候 初始化
            redisTemplate.opsForValue().set(keyRedis,conRedis);
        }else{
            conRedis=objectRedis.toString()+"|"+conRedis;
            redisTemplate.opsForValue().set(keyRedis,conRedis);
        }
        markRedisInfoTotal(keyRedis,redisTemplate);
    }

    //总数
    public static void markRedisInfoTotal(String key,RedisTemplate redisTemplate){
        String keyRedis=key+"Total";
        Object objectRedis=redisTemplate.opsForValue().get(keyRedis);
        if(ObjectUtils.isEmpty(objectRedis)){
            //缓存不存在的时候 初始化
            redisTemplate.opsForValue().set(keyRedis,1);
        }else{
            String o=objectRedis.toString();
            int intObject=Integer.parseInt(o)+1;
            redisTemplate.opsForValue().set(keyRedis,intObject);
        }
    }

    /**
     * 动作字段
     */
    public static final Map<String, String> ACT_TYPE_NAME = new HashMap<>();

    static {
        ACT_TYPE_NAME.put("queryMark","中考成绩查询");
        ACT_TYPE_NAME.put("login","登陆");
        ACT_TYPE_NAME.put("logout","退出");
        ACT_TYPE_NAME.put("importGraduation","导入毕业生信息");
        ACT_TYPE_NAME.put("exportGraduation","导出毕业生信息");
        ACT_TYPE_NAME.put("importMark","导入成绩信息");
        ACT_TYPE_NAME.put("exportMark","导出成绩信息");
        ACT_TYPE_NAME.put("importEnroll","导入高中录取信息");
        ACT_TYPE_NAME.put("exportEnroll","导出高中录取信息");
        ACT_TYPE_NAME.put("importAbouts","导入中职或者毕业去向信息");
        ACT_TYPE_NAME.put("exportAbouts","导出中职或者毕业去向信息");
        ACT_TYPE_NAME.put("querySupervise","查看督办信息");
        ACT_TYPE_NAME.put("queryGraduation","查看毕业生信息");
        ACT_TYPE_NAME.put("queryMark","查看成绩信息");
        ACT_TYPE_NAME.put("queryEnroll","查看高中录取信息");
        ACT_TYPE_NAME.put("queryAbouts","查看毕业去向信息");
        ACT_TYPE_NAME.put("removeGraduation","删除毕业生信息");
        ACT_TYPE_NAME.put("removeMark","删除成绩信息");
        ACT_TYPE_NAME.put("removeEnroll","删除高中录取信息");
        ACT_TYPE_NAME.put("removeAbouts","删除毕业去向信息");
        ACT_TYPE_NAME.put("queryZzMark","查看中职成绩信息");
        ACT_TYPE_NAME.put("exportSchoolSubjectAverageNum","导出各学校各科平均分总览信息");
        ACT_TYPE_NAME.put("exportCitySchoolAverageNum","导出各师域内各校总平均分信息");
        ACT_TYPE_NAME.put("exportBTWholeStatisticsSL","导出兵团整体各学科三率数据信息");
        ACT_TYPE_NAME.put("exportCityAverageSL","导出各师各学科三率总览（学科）信息");
        ACT_TYPE_NAME.put("exportCitiesSubjectAverageNum","导出各师各学科平均分总览信息");
        ACT_TYPE_NAME.put("exportRankingCitiesSubjectAverageNum","导出各师平均分排名信息");
        ACT_TYPE_NAME.put("exportAreaAverageNum","导出各地域各师平均分排名信息");
        ACT_TYPE_NAME.put("SubjectsSLEveryCity","导出各师各学科三率总览（师）信息");
        ACT_TYPE_NAME.put("exportstatisticsmakenum","导出各分数段学生人数信息");
        ACT_TYPE_NAME.put("exportstudent500","导出前500名学生信息信息");
        ACT_TYPE_NAME.put("exportBtMakeNum","导出兵团整体各分数段人数统计信息");
        ACT_TYPE_NAME.put("exportCityMakeNum","导出各师各分数段人数统计信息");
        ACT_TYPE_NAME.put("exportstudent500ZB","导出前500名学生各校占比信息");
        ACT_TYPE_NAME.put("exportStudentAgeExcept","导出学生年龄异常数据信息");

    }

    public static final SortedMap<String, Integer> CITY_NAME_SORT =new TreeMap<>();

    static {
        CITY_NAME_SORT.put("第一师",1);
        CITY_NAME_SORT.put("第二师",2);
        CITY_NAME_SORT.put("第三师",3);
        CITY_NAME_SORT.put("第四师",4);
        CITY_NAME_SORT.put("第五师",5);
        CITY_NAME_SORT.put("第六师",6);
        CITY_NAME_SORT.put("第七师",7);
        CITY_NAME_SORT.put("第八师",8);
        CITY_NAME_SORT.put("第九师",9);
        CITY_NAME_SORT.put("第十师",10);
        CITY_NAME_SORT.put("第十一师",11);
        CITY_NAME_SORT.put("第十二师",12);
        CITY_NAME_SORT.put("第十三师",13);
        CITY_NAME_SORT.put("第十四师",14);
    }
}
