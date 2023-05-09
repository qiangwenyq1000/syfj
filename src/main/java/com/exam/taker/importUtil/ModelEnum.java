package com.exam.taker.importUtil;

public enum ModelEnum {
    STUDENT_GRADUATION("学生基础信息","StudentGraduation","com.exam.taker.importUtil.StudentGraduationImportVo"),
    STUDENT_MAKE("学生成绩信息","StudentMake","com.exam.taker.importUtil.StudentMakeImportVo"),
    STUDENT_MAKE_EXPORT("学生成绩导出信息","StudentMakeExport","com.exam.taker.importUtil.StudentMakeImportVo"),
    STUDENT_ENROLL("学生普高录取信息","StudentEnroll","com.exam.taker.importUtil.StudentsEnrollImportVo"),
    STUDENT_ENROLL_ZZ("学生中职录取信息","StudentEnrollZZ","com.exam.taker.importUtil.StudentsEnrollImportVo"),
    STUDENT_SCHOOL_AVG("学校平均成绩情况", "StudentSchoolAvg", "com.exam.taker.importUtil.SchoolPJFVo"),

    CITY_SCHOOL_AVG("师域内学校平均情况", "CitySchoolAvg", "com.exam.taker.importUtil.SchoolPJFVo"),
    BT_WHOLE_STATISTICS("兵团整体各学科三率数据","BTWholeStatisticsSL","com.exam.taker.dto.BTWholeStatisticsSLExcelVo"),
    CITY_STATISTICS_SL("各师三率对比", "CityStatisticsSL", "com.exam.taker.importUtil.CityStatisticsSLExcelVo"),
    CITIES_SUBJECT_AVERAGE_NUM("各师各学科平均分一览","CitiesSubjectAverageNum", "com.exam.taker.importUtil.CitiesSubjectAverageNumExcelVo"),

    STATISTICS_MAKE_NUM("各分数段人数信息","StatisticsMakeNum", "com.exam.taker.importUtil.StatisticsMakeNumImportVo"),
    STUDENT_500("前500名学生信息","Student500Vo","com.exam.taker.importUtil.Student500Vo"),
    BT_MAKE_NUM("兵团整体各分数段人数统计","BtMakeNumVo","com.exam.taker.importUtil.BtMakeNumVo"),
    CITY_MAKE_NUM("各师各分数段人数统计","CityMakeNumVo","com.exam.taker.importUtil.CityMakeNumVo"),
    STUDENT_500_ZB("前500名学生信息各校占比","Student500ZBVo","com.exam.taker.importUtil.Student500ZBVo"),
    STUDENT_AGE_EXCEPT("导出年龄段异常数据","StudentAgeExceptVo","com.exam.taker.importUtil.StudentAgeExceptVo")
    ;

    private String modelName;
    private String modelKey;
    private String className;
    ModelEnum(String modelName,String modelKey,String className){
        this.modelName=modelName;
        this.modelKey=modelKey;
        this.className=className;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelKey() {
        return modelKey;
    }

    public String getClassName() {
        return className;
    }
}
