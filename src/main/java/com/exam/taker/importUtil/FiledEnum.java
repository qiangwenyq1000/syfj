package com.exam.taker.importUtil;

public enum FiledEnum {
    /*******************************************************************************************************************/
    /****************************************************学生基础信息导入字段***********************************************/
    STUDENT_YEAR("届次","studentYear",true,false,"StudentGraduation",0),
    REAL_NAME("姓名","realName",true,false,"StudentGraduation",1),
    CERTIFICATES_TYPE("证件类型","certificatesType",true,false,"StudentGraduation",2),
    CERTIFICATES("证件号","certificates",true,false,"StudentGraduation",3),
    STUDENT_NO("学籍号","studentNo",true,false,"StudentGraduation",4),
    EXAM_NO("准考证号","examNo",true,false,"StudentGraduation",5),
    SEX("性别","sex",false,false,"StudentGraduation",6),
    NATION("族别","nation",false,false,"StudentGraduation",7),
    DATE_BIRTH("出生日期","dateBirth",false,false,"StudentGraduation",8),
    SIGN_UP_XJ("是否报名新疆高中班","signUpXj",false,false,"StudentGraduation",9),
    SIGN_UP_XA("是否愿意参加西安八一民族中学录取","signUpXa",false,false,"StudentGraduation",10),
    POLITICAL("政治面貌","political",false,false,"StudentGraduation",11),
    STUDENT_PHONE("考生联系电话","studentPhone",false,false,"StudentGraduation",12),
    STUDENT_SOURCE("生源来源","studentSource",false,false,"StudentGraduation",13),
    CLASS_INFO("班级","classinfo",false,false,"StudentGraduation",14),
    ADDRESS("通讯地址","address",false,false,"StudentGraduation",15),
    HOME_ZIP("邮政编码","homeZip",false,false,"StudentGraduation",16),
    CITY("报名地州","city",true,false,"StudentGraduation",17),
    COUNTY("报名县市","county",false,false,"StudentGraduation",18),
    SCHOOL_NAME("报名学校","schoolName",false,false,"StudentGraduation",19),
    HOME_TYPE("户口所在地","homeType",false,false,"StudentGraduation",20),
    HOME_CITY("户口所居地","homeCity",false,false,"StudentGraduation",21),
    HOME_COUNTY("户口县市","homeCounty",false,false,"StudentGraduation",22),
    NATURE("户口性质","nature",false,false,"StudentGraduation",23),
    USER_SETTLE_TIME("考生落户时间","userSettleTime",true,false,"StudentGraduation",24),
    SIGN_TYPE("报名状态","signType",false,false,"StudentGraduation",25),
    SOURCE_APPROACH("数据来源","sourceApproach",false,false,"StudentGraduation",26),
    FEATURES("考生特征","features",false,false,"StudentGraduation",27),
    FATHER_NAME("父亲姓名","fatherName",false,false,"StudentGraduation",28),
    FATHER_CARD("父亲身份证号","fatherCard",false,false,"StudentGraduation",29),
    FATHER_NATION("父亲族别","fatherNation",false,false,"StudentGraduation",30),
    FATHER_NATURE("父亲户口性质","fatherNature",false,false,"StudentGraduation",31),
    FATHER_SETTLE_TIME("父亲落户时间","fatherSettleTime",false,false,"StudentGraduation",32),
    FATHER_PHONE("父亲联系方式","fatherPhone",false,false,"StudentGraduation",33),
    FATHER_WORK("父亲工作单位","fatherWork",false,false,"StudentGraduation",34),
    MOTHER_NAME("母亲姓名","motherName",false,false,"StudentGraduation",35),
    MOTHER_CARD("母亲身份证号","motherCard",false,false,"StudentGraduation",36),
    MOTHER_NATION("母亲族别","motherNation",false,false,"StudentGraduation",37),
    MOTHER_NATURE("母亲户口性质","motherNature",false,false,"StudentGraduation",38),
    MOTHER_SETTLE_TIME("母亲落户时间","motherSettleTime",false,false,"StudentGraduation",39),
    MOTHER_PHONE("母亲联系方式","motherPhone",false,false,"StudentGraduation",40),
    MOTHER_WORK("母亲工作单位","motherWork",false,false,"StudentGraduation",41),
    TY_SCORE("体育成绩","tyScore",true,false,"StudentGraduation",42),
    YS_SCORE("艺术成绩","ysScore",false,false,"StudentGraduation",43),
    SZ_LEVEL("综合素质评价","szLevel",false,false,"StudentGraduation",44),
    SYK_SCORE("实验课","sykScore",false,false,"StudentGraduation",45),
    /*******************************************************************************************************************/
    /****************************************************学生成绩信息导入字段***********************************************/
    MAKE_STUDENT_YEAR("届次","studentYear",true,false,"StudentMake",0),
    MAKE_REAL_NAME("姓名","realName",true,false,"StudentMake",1),
//    MAKE_CERTIFICATES("证件号","certificates",true,false,"StudentMake",2),
//    MAKE_STUDENT_NO("学籍号","studentNo",true,false,"StudentMake",3),
    MAKE_EXAM_NO("准考证号","examNo",true,false,"StudentMake",2),
    MAKE_CLASS_INFO("班级","classinfo",true,false,"StudentMake",3),
    MAKE_CITY("地市","city",true,false,"StudentMake",4),
    MAKE_SCHOOL_NAME("学校","schoolName",true,false,"StudentMake",5),
    MAKE_YW("语文","yw",true,false,"StudentMake",6),
    MAKE_SX("数学","sx",true,false,"StudentMake",7),
    MAKE_WY("外语","wy",true,false,"StudentMake",8),
    MAKE_WL("物理","wl",true,false,"StudentMake",9),
    MAKE_HX("化学","hx",true,false,"StudentMake",10),
    MAKE_ZZ("政治","zz",true,false,"StudentMake",11),
    MAKE_LS("历史","ls",true,false,"StudentMake",12),
    MAKE_TOTAL("总分","total",true,false,"StudentMake",13),
    /*******************************************************************************************************************/
    /****************************************************学生成绩信息导出字段***********************************************/
    EXPORT_MAKE_STUDENT_YEAR("届次","studentYear",true,false,"StudentMakeExport",0),
    EXPORT_MAKE_REAL_NAME("姓名","realName",true,false,"StudentMakeExport",1),
//    EXPORT_MAKE_CERTIFICATES("证件号","certificates",true,false,"StudentMakeExport",2),
//    EXPORT_MAKE_STUDENT_NO("学籍号","studentNo",true,false,"StudentMakeExport",3),
    EXPORT_MAKE_EXAM_NO("准考证号","examNo",true,false,"StudentMakeExport",2),
    EXPORT_MAKE_CLASS_INFO("班级","classinfo",true,false,"StudentMakeExport",3),
    EXPORT_MAKE_CITY("地市","city",true,false,"StudentMakeExport",4),
    EXPORT_MAKE_SCHOOL_NAME("学校","schoolName",true,false,"StudentMakeExport",5),
    EXPORT_MAKE_YW("语文","yw",true,false,"StudentMakeExport",6),
    EXPORT_MAKE_SX("数学","sx",true,false,"StudentMakeExport",7),
    EXPORT_MAKE_WY("外语","wy",true,false,"StudentMakeExport",8),
    EXPORT_MAKE_WL("物理","wl",true,false,"StudentMakeExport",9),
    EXPORT_MAKE_HX("化学","hx",true,false,"StudentMakeExport",10),
    EXPORT_MAKE_ZZ("政治","zz",true,false,"StudentMakeExport",11),
    EXPORT_MAKE_LS("历史","ls",true,false,"StudentMakeExport",12),
    EXPORT_MAKE_TOTAL("总分","total",true,false,"StudentMakeExport",13),
    EXPORT_MAKE_TY("体育成绩","ty",false,false,"StudentMakeExport",14),
    EXPORT_MAKE_YS("艺术成绩","ys",false,false,"StudentMakeExport",15),
    EXPORT_MAKE_SZ("综合素质评价","zhszpj",false,false,"StudentMakeExport",16),
    EXPORT_MAKE_SY("实验课","syk",false,false,"StudentMakeExport",17),
    /*******************************************************************************************************************/
    /*************************************************学生普高录取信息导入字段***********************************************/
    ENROLL_STUDENT_YEAR("届次","studentYear",true,false,"StudentEnroll",0),
    ENROLL_REAL_NAME("姓名","realName",true,false,"StudentEnroll",1),
    ENROLL_CERTIFICATES("证件号","certificates",true,false,"StudentEnroll",2),
    ENROLL_STUDENT_NO("学籍号","studentNo",true,false,"StudentEnroll",3),
    ENROLL_EXAM_NO("准考证号","examNo",true,false,"StudentEnroll",4),
    ENROLL_CLASS_INFO("班级","classinfo",false,false,"StudentEnroll",5),
    ENROLL_CITY("报名地州","city",false,false,"StudentEnroll",6),
    ENROLL_SCHOOL_NAME("报名学校","schoolName",true,false,"StudentEnroll",7),
    ENROLL_COMPANY("录取高中","company",true,false,"StudentEnroll",8),
    ENROLL_REMARKS("备注","remarks",false,false,"StudentEnroll",9),
    /*******************************************************************************************************************/
    /*************************************************学生中职录取信息导入字段***********************************************/
    ENROLL_ZZ_STUDENT_YEAR("届次","studentYear",true,false,"StudentEnrollZZ",0),
    ENROLL_ZZ_REAL_NAME("姓名","realName",true,false,"StudentEnrollZZ",1),
    ENROLL_ZZ_CERTIFICATES("证件号","certificates",true,false,"StudentEnrollZZ",2),
    ENROLL_ZZ_STUDENT_NO("学籍号","studentNo",true,false,"StudentEnrollZZ",3),
    ENROLL_ZZ_EXAM_NO("准考证号","examNo",true,false,"StudentEnrollZZ",4),
    ENROLL_ZZ_CLASS_INFO("班级","classinfo",false,false,"StudentEnrollZZ",5),
    ENROLL_ZZ_CITY("报名地州","city",false,false,"StudentEnrollZZ",6),
    ENROLL_ZZ_SCHOOL_NAME("报名学校","schoolName",true,false,"StudentEnrollZZ",7),
    ENROLL_ZZ_ABOUTS("毕业去向","abouts",true,false,"StudentEnrollZZ",8),
    ENROLL_ZZ_COMPANY("录取中职学校名称","company",false,false,"StudentEnrollZZ",9),
    ENROLL_ZZ_MAJOR("录取中职学校专业或入伍兵种等","major",false,false,"StudentEnrollZZ",10),
    ENROLL_ZZ_IS_CURRENT("应届、历届","isCurrentYear",false,false,"StudentEnrollZZ",11),
    ENROLL_ZZ_REMARKS("备注","remarks",false,false,"StudentEnrollZZ",12),

    /*******************************************************************************************************************/
    /*************************************************导出各学校各科平均分总览***********************************************/
    SCHOOL_AVG_SCHOOL_NAME("学校名称","schoolName",true,false,"StudentSchoolAvg",0),
    SCHOOL_AVG_CITY("所在师","city",true,false,"StudentSchoolAvg",1),
    SCHOOL_AVG_ZPJF("总平均分","zpjf",true,false,"StudentSchoolAvg",2),
    SCHOOL_AVG_RANK("兵团排名","proRank",true,false,"StudentSchoolAvg",3),
    SCHOOL_AVG_YW("语文","yw",true,false,"StudentSchoolAvg",4),
    SCHOOL_AVG_SX("数学","sx",true,false,"StudentSchoolAvg",5),
    SCHOOL_AVG_WY("外语","wy",true,false,"StudentSchoolAvg",6),
    SCHOOL_AVG_LZ("理综","lz",true,false,"StudentSchoolAvg",7),
    SCHOOL_AVG_WZ("文综","wz",true,false,"StudentSchoolAvg",8),

    /*******************************************************************************************************************/
    /*************************************************导出各师域各学校平均分***********************************************/
    CITY_AVG_SCHOOL_NAME("第师","schoolName",true,false,"CitySchoolAvg",0),
    CITY_AVG_ZPJF("总平均分","zpjf",true,false,"CitySchoolAvg",1),
    CITY_AVG_CITY_RANK("师域内排名","cityRank",true,false,"CitySchoolAvg",2),
    CITY_AVG_PRO_RANK("兵团排名","proRank",true,false,"CitySchoolAvg",3),
    CITY_AVG_YW("语文","yw",true,false,"CitySchoolAvg",4),
    CITY_AVG_SX("数学","sx",true,false,"CitySchoolAvg",5),
    CITY_AVG_WY("外语","wy",true,false,"CitySchoolAvg",6),
    CITY_AVG_LZ("理综","lz",true,false,"CitySchoolAvg",7),
    CITY_AVG_WZ("文综","wz",true,false,"CitySchoolAvg",8),
    /**********************************************************************************************************************/

    /***********************************************兵团总体各科目三率导出字段**************************************************/
    EXPORT_WHOLE_STATISTICS_CLASSIFY("科目","classify",true,false,"BTWholeStatisticsSL",0),
    EXPORT_WHOLE_STATISTICS_STUDENT_YEAR("总人数","studentNum",true,false,"BTWholeStatisticsSL",1),
    EXPORT_WHOLE_STATISTICS_MF("满分","mf",true,false,"BTWholeStatisticsSL",2),
    EXPORT_WHOLE_STATISTICS_PJF("平均分","pjf",true,false,"BTWholeStatisticsSL",3),
    EXPORT_WHOLE_STATISTICS_ND("难度","nd",true,false,"BTWholeStatisticsSL",4),
    EXPORT_WHOLE_STATISTICS_BZC("标准差","bzc",true,false,"BTWholeStatisticsSL",5),
    EXPORT_WHOLE_STATISTICS_ZGF("最高分","zgf",true,false,"BTWholeStatisticsSL",6),
    EXPORT_WHOLE_STATISTICS_ZDF("最低分","zdf",true,false,"BTWholeStatisticsSL",7),
    EXPORT_WHOLE_STATISTICS_YXL("优秀率","yxl",true,false,"BTWholeStatisticsSL",8),
    EXPORT_WHOLE_STATISTICS_LHL("良好率","lhl",true,false,"BTWholeStatisticsSL",9),
    EXPORT_WHOLE_STATISTICS_JGL("及格率","jgl",true,false,"BTWholeStatisticsSL",10),
    EXPORT_WHOLE_STATISTICS_DFL("低分率","dfl",true,false,"BTWholeStatisticsSL",11),
    /************************************************************************************************************************/

    /****************************************************某届某科目师级三率对比导出字段************************************************/
    EXPORT_CITY_STATISTICS_CITY("师", "city",true,false,"CityStatisticsSL",0),
    EXPORT_CITY_STATISTICS_STUDENT_NUM("学生人数", "studentNum",true,false,"CityStatisticsSL",1),
    EXPORT_CITY_STATISTICS_MF("满分", "mf",true,false,"CityStatisticsSL",2),
    EXPORT_CITY_STATISTICS_PJF("平均分", "pjf",true,false,"CityStatisticsSL",3),
    EXPORT_CITY_STATISTICS_ND("难度", "nd",true,false,"CityStatisticsSL",4),
    EXPORT_CITY_STATISTICS_BZC("标准差", "bzc",true,false,"CityStatisticsSL",5),
    EXPORT_CITY_STATISTICS_ZGF("最高分", "zgf",true,false,"CityStatisticsSL",6),
    EXPORT_CITY_STATISTICS_ZDF("最低分", "zdf",true,false,"CityStatisticsSL",7),
    EXPORT_CITY_STATISTICS_YXL("优秀率", "yxl",true,false,"CityStatisticsSL",8),
    EXPORT_CITY_STATISTICS_LHL("良好率", "lhl",true,false,"CityStatisticsSL",9),
    EXPORT_CITY_STATISTICS_JGL("及格率", "jgl",true,false,"CityStatisticsSL",10),
    EXPORT_CITY_STATISTICS_DFL("低分率", "dfl",true,false,"CityStatisticsSL",11),
    /***********************************************************************************************************************/

    /*****************************************************各师各学科平均分一览表******************************************************************/
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_CITY("师","city",true,false,"CitiesSubjectAverageNum",0),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_TOTAL("总分","zfTotalPjf",true,false,"CitiesSubjectAverageNum",1),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_YW("语文","ywPjf",true,false,"CitiesSubjectAverageNum",2),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_SX("数学","sxPjf",true,false,"CitiesSubjectAverageNum",3),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_WY("外语","wyPjf",true,false,"CitiesSubjectAverageNum",4),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_WL("物理","wlPjf",true,false,"CitiesSubjectAverageNum",5),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_HX("化学","hxPjf",true,false,"CitiesSubjectAverageNum",6),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_ZZ("政治","zzPjf",true,false,"CitiesSubjectAverageNum",7),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_LS("历史","lsPjf",true,false,"CitiesSubjectAverageNum",8),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_LZ("理综","lzPjf",true,false,"CitiesSubjectAverageNum",9),
    EXPORT_CITY_SUBJECT_AVERAGE_NUM_WZ("文综","wzPjf",true,false,"CitiesSubjectAverageNum",10),
    /*********************************************************************************************************************************************/
    /*******************************************************************************************************************/
    /*************************************************各分数段人数信息字段***********************************************/
//    EXPORT_UUID("","",true,false,"",),
//    EXPORT_FRACTION_UUID("分数段编号","fractionUuid",true,false,"StatisticsMakeNum",),
    EXPORT_FRACTION_NAME("分数段","fractionName",true,false,"StatisticsMakeNum",5),
    EXPORT_FRACTION_NUM("分数段人数","fractionNum",true,false,"StatisticsMakeNum",6),
    EXPORT_SCHOOL_NAME("学校名称","schoolName",true,false,"StatisticsMakeNum",3),
    EXPORT_CITY("师","city",true,false,"StatisticsMakeNum",1),
    EXPORT_PROVINCE("兵团","province",true,false,"StatisticsMakeNum",2),
    EXPORT_TYPE("组织架构类型","type",true,false,"StatisticsMakeNum",4),
    EXPORT_STUDENT_YEAR("届次","studentYear",true,false,"StatisticsMakeNum",0),
    EXPORT_CLASSIFY("分数类型","classify",true,false,"StatisticsMakeNum",7),
    EXPORT_SCHOOL_AR("学校区域","schoolArea",true,false,"StatisticsMakeNum",8),
//    EXPORT_SCHOOL_UUID("学校编号","schoolUuid",true,false,"StatisticsMakeNum",3),
//    EXPORT_CREATOR_DATE("创建时间","creatorDate",true,false,"StatisticsMakeNum",),
//    EXPORT_SCHOOL_AREA("学校区域","schoolArea",true,false,"StatisticsMakeNum",),
    /*******************************************************************************************************************/
    /*************************************************前500名学生新***********************************************/
    EXPORT_STUDENT500_CITY("师","city",true,false,"Student500Vo",0),
    EXPORT_STUDENT500_SCHOOL_NAME("学校","schoolName",true,false,"Student500Vo",1),
    EXPORT_STUDENT500_NAME("姓名","name",true,false,"Student500Vo",2),
    EXPORT_STUDENT500_yw("语文","yw",true,false,"Student500Vo",3),
    EXPORT_STUDENT500_sx("数学","sx",true,false,"Student500Vo",4),
    EXPORT_STUDENT500_wy("外语","wy",true,false,"Student500Vo",5),
    EXPORT_STUDENT500_wz("文综","wz",true,false,"Student500Vo",6),
    EXPORT_STUDENT500_lz("理综","lz",true,false,"Student500Vo",7),
    EXPORT_STUDENT500_total("总分","total",true,false,"Student500Vo",8),
    EXPORT_STUDENT500_rank("排名","rank",true,false,"Student500Vo",9),
    /*******************************************************************************************************************/
    /*************************************************兵团整体各分数段人数统计***********************************************/
    EXPORT_TOTAL("总数","dy0",true,false,"BtMakeNumVo",0),
    EXPORT_DY720("720以上","dy720",true,false,"BtMakeNumVo",1),
    EXPORT_DY710("710以上","dy710",true,false,"BtMakeNumVo",3),
    EXPORT_DY700("700以上","dy700",true,false,"BtMakeNumVo",5),
    EXPORT_DY690("690以上","dy690",true,false,"BtMakeNumVo",7),
    EXPORT_DY680("680以上","dy680",true,false,"BtMakeNumVo",9),
    EXPORT_DY670("670以上","dy670",true,false,"BtMakeNumVo",11),
    EXPORT_DY660("660以上","dy660",true,false,"BtMakeNumVo",13),
    EXPORT_DY650("650以上","dy650",true,false,"BtMakeNumVo",15),
    EXPORT_DY640("640以上","dy640",true,false,"BtMakeNumVo",17),
    EXPORT_DY630("630以上","dy630",true,false,"BtMakeNumVo",19),
    EXPORT_DY620("620以上","dy620",true,false,"BtMakeNumVo",21),
    EXPORT_DY610("610以上","dy610",true,false,"BtMakeNumVo",23),
    EXPORT_DY600("600以上","dy600",true,false,"BtMakeNumVo",25),
    EXPORT_DY590("590以上","dy590",true,false,"BtMakeNumVo",27),
    EXPORT_DY580("580以上","dy580",true,false,"BtMakeNumVo",29),
    EXPORT_DY570("570以上","dy570",true,false,"BtMakeNumVo",31),
    EXPORT_DY560("560以上","dy560",true,false,"BtMakeNumVo",33),
    EXPORT_DY550("550以上","dy550",true,false,"BtMakeNumVo",35),
    EXPORT_DY540("540以上","dy540",true,false,"BtMakeNumVo",37),
    EXPORT_DY530("530以上","dy530",true,false,"BtMakeNumVo",39),
    EXPORT_DY520("520以上","dy520",true,false,"BtMakeNumVo",41),
    EXPORT_DY510("510以上","dy510",true,false,"BtMakeNumVo",43),
    EXPORT_DY500("500以上","dy500",true,false,"BtMakeNumVo",45),
    EXPORT_FROM710TO720("710到720","from710To720",true,false,"BtMakeNumVo",2),
    EXPORT_FROM700TO710("700到710","from700To710",true,false,"BtMakeNumVo",4),
    EXPORT_FROM690TO700("690到700","from690To700",true,false,"BtMakeNumVo",6),
    EXPORT_FROM680TO690("680到690","from680To690",true,false,"BtMakeNumVo",8),
    EXPORT_FROM670TO680("670到680","from670To680",true,false,"BtMakeNumVo",10),
    EXPORT_FROM660TO670("660到670","from660To670",true,false,"BtMakeNumVo",12),
    EXPORT_FROM650TO660("650到660","from650To660",true,false,"BtMakeNumVo",14),
    EXPORT_FROM640TO650("640到650","from640To650",true,false,"BtMakeNumVo",16),
    EXPORT_FROM630TO640("630到640","from630To640",true,false,"BtMakeNumVo",18),
    EXPORT_FROM620TO630("620到630","from620To630",true,false,"BtMakeNumVo",20),
    EXPORT_FROM610TO620("610到620","from610To620",true,false,"BtMakeNumVo",22),
    EXPORT_FROM600TO610("600到610","from600To610",true,false,"BtMakeNumVo",24),
    EXPORT_FROM590TO600("590到600","from590To600",true,false,"BtMakeNumVo",26),
    EXPORT_FROM580TO590("580到590","from580To590",true,false,"BtMakeNumVo",28),
    EXPORT_FROM570TO580("570到580","from570To580",true,false,"BtMakeNumVo",30),
    EXPORT_FROM560TO570("560到570","from560To570",true,false,"BtMakeNumVo",32),
    EXPORT_FROM550TO560("550到560","from550To560",true,false,"BtMakeNumVo",34),
    EXPORT_FROM540TO550("540到550","from540To550",true,false,"BtMakeNumVo",36),
    EXPORT_FROM530TO540("530到540","from530To540",true,false,"BtMakeNumVo",38),
    EXPORT_FROM520TO530("520到530","from520To530",true,false,"BtMakeNumVo",40),
    EXPORT_FROM510TO520("510到520","from510To520",true,false,"BtMakeNumVo",42),
    EXPORT_FROM500TO510("500到510","from500To510",true,false,"BtMakeNumVo",44),
    /*******************************************************************************************************************/
    /*************************************************各师各分数段人数统计***********************************************/
    EXPORT_CITY_DY720("720以上","dy720",true,false,"CityMakeNumVo",2),
    EXPORT_CITY_DY710("710以上","dy710",true,false,"CityMakeNumVo",4),
    EXPORT_CITY_DY700("700以上","dy700",true,false,"CityMakeNumVo",6),
    EXPORT_CITY_DY690("690以上","dy690",true,false,"CityMakeNumVo",8),
    EXPORT_CITY_DY680("680以上","dy680",true,false,"CityMakeNumVo",10),
    EXPORT_CITY_DY670("670以上","dy670",true,false,"CityMakeNumVo",12),
    EXPORT_CITY_DY660("660以上","dy660",true,false,"CityMakeNumVo",14),
    EXPORT_CITY_DY650("650以上","dy650",true,false,"CityMakeNumVo",16),
    EXPORT_CITY_DY640("640以上","dy640",true,false,"CityMakeNumVo",18),
    EXPORT_CITY_DY630("630以上","dy630",true,false,"CityMakeNumVo",20),
    EXPORT_CITY_DY620("620以上","dy620",true,false,"CityMakeNumVo",22),
    EXPORT_CITY_DY610("610以上","dy610",true,false,"CityMakeNumVo",24),
    EXPORT_CITY_DY600("600以上","dy600",true,false,"CityMakeNumVo",26),
    EXPORT_CITY_DY590("590以上","dy590",true,false,"CityMakeNumVo",28),
    EXPORT_CITY_DY580("580以上","dy580",true,false,"CityMakeNumVo",30),
    EXPORT_CITY_DY570("570以上","dy570",true,false,"CityMakeNumVo",32),
    EXPORT_CITY_DY560("560以上","dy560",true,false,"CityMakeNumVo",34),
    EXPORT_CITY_DY550("550以上","dy550",true,false,"CityMakeNumVo",36),
    EXPORT_CITY_DY540("540以上","dy540",true,false,"CityMakeNumVo",38),
    EXPORT_CITY_DY530("530以上","dy530",true,false,"CityMakeNumVo",40),
    EXPORT_CITY_DY520("520以上","dy520",true,false,"CityMakeNumVo",42),
    EXPORT_CITY_DY510("510以上","dy510",true,false,"CityMakeNumVo",44),
    EXPORT_CITY_DY500("500以上","dy500",true,false,"CityMakeNumVo",46),
    EXPORT_CITY_FROM710TO720("710到720","from710To720",true,false,"CityMakeNumVo",3),
    EXPORT_CITY_FROM700TO710("700到710","from700To710",true,false,"CityMakeNumVo",5),
    EXPORT_CITY_FROM690TO700("690到700","from690To700",true,false,"CityMakeNumVo",7),
    EXPORT_CITY_FROM680TO690("680到690","from680To690",true,false,"CityMakeNumVo",9),
    EXPORT_CITY_FROM670TO680("670到680","from670To680",true,false,"CityMakeNumVo",11),
    EXPORT_CITY_FROM660TO670("660到670","from660To670",true,false,"CityMakeNumVo",13),
    EXPORT_CITY_FROM650TO660("650到660","from650To660",true,false,"CityMakeNumVo",15),
    EXPORT_CITY_FROM640TO650("640到650","from640To650",true,false,"CityMakeNumVo",17),
    EXPORT_CITY_FROM630TO640("630到640","from630To640",true,false,"CityMakeNumVo",19),
    EXPORT_CITY_FROM620TO630("620到630","from620To630",true,false,"CityMakeNumVo",21),
    EXPORT_CITY_FROM610TO620("610到620","from610To620",true,false,"CityMakeNumVo",23),
    EXPORT_CITY_FROM600TO610("600到610","from600To610",true,false,"CityMakeNumVo",25),
    EXPORT_CITY_FROM590TO600("590到600","from590To600",true,false,"CityMakeNumVo",27),
    EXPORT_CITY_FROM580TO590("580到590","from580To590",true,false,"CityMakeNumVo",29),
    EXPORT_CITY_FROM570TO580("570到580","from570To580",true,false,"CityMakeNumVo",31),
    EXPORT_CITY_FROM560TO570("560到570","from560To570",true,false,"CityMakeNumVo",33),
    EXPORT_CITY_FROM550TO560("550到560","from550To560",true,false,"CityMakeNumVo",35),
    EXPORT_CITY_FROM540TO550("540到550","from540To550",true,false,"CityMakeNumVo",37),
    EXPORT_CITY_FROM530TO540("530到540","from530To540",true,false,"CityMakeNumVo",39),
    EXPORT_CITY_FROM520TO530("520到530","from520To530",true,false,"CityMakeNumVo",41),
    EXPORT_CITY_FROM510TO520("510到520","from510To520",true,false,"CityMakeNumVo",43),
    EXPORT_CITY_FROM500TO510("500到510","from500To510",true,false,"CityMakeNumVo",45),
    EXPORT_CITY_NAME("单位","city",true,false,"CityMakeNumVo",0),
    EXPORT_CITY_TOTAL("总数","dy0",true,false,"CityMakeNumVo",1),
    /*******************************************************************************************************************/
    /*************************************************各师各分数段人数统计***********************************************/
    EXPORT_STUDENT500ZB_SCHOOLNAME("学校","schoolName",true,false,"Student500ZBVo",0),
    EXPORT_STUDENT500ZB_ZB("前500名占比","zb",true,false,"Student500ZBVo",1),

    /*************************************************学年龄段异常数据导出字段***********************************************/
    AGE_STUDENT_YEAR("届次","studentYear",true,false,"StudentAgeExceptVo",0),
    AGE_REAL_NAME("姓名","realName",true,false,"StudentAgeExceptVo",1),
    AGE_EXAM_NO("准考证号","examNo",false,false,"StudentAgeExceptVo",2),
    AGE_CITY("报名地州","city",false,false,"StudentAgeExceptVo",3),
    AGE_SCHOOL_NAME("报名学校","schoolName",false,false,"StudentAgeExceptVo",4),
    AGE_CLASS_INFO("班级","classinfo",false,false,"StudentAgeExceptVo",5),
    AGE_DATE_BIRINFO("出生日期","dateBirth",false,false,"StudentAgeExceptVo",6),
    AGE_AGE("年龄","age",false,false,"StudentAgeExceptVo",7),
    AGE_SEX("性别","sex",true,false,"StudentAgeExceptVo",8),
    AGE_NATION("族别","nation",false,false,"StudentAgeExceptVo",9),
    AGE_PHONE("生联系电话","studentPhone",false,false,"StudentAgeExceptVo",10),
    AGE_ZIP("邮政编码","homeZip",false,false,"StudentAgeExceptVo",11),
    AGE_ADDRESS("通讯地址","address",false,false,"StudentAgeExceptVo",12)

            ;
    private String filedName;
    private String filedKey;
    private boolean isRequired;
    private boolean isSecurity;
    private String model;
    private int sortNum;

    FiledEnum(String filedName,String filedKey,boolean isRequired,boolean isSecurity,String model,int sortNum){
        this.filedName=filedName;
        this.filedKey=filedKey;
        this.isRequired=isRequired;
        this.isSecurity=isSecurity;
        this.model=model;
        this.sortNum=sortNum;
    }

    public String getFiledName() {
        return filedName;
    }

    public String getFiledKey() {
        return filedKey;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean isSecurity() {
        return isSecurity;
    }

    public String getModel() {
        return model;
    }

    public int getSortNum() {
        return sortNum;
    }
}
