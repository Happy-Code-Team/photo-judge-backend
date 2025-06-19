package com.photo.judge.common.utils.generator;

import lombok.Data;

/**
 * 配置信息
 */
@Data
public class GeneratorConfigInfo {
    //数据库相关
    public static final String databaseName = "photo";//数据库名
    public static final String tableName = "user_info";//表名
    public static final String driverName = "com.mysql.cj.jdbc.Driver";//驱动
    public static final String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useUnicode=true&amp&characterEncoding=utf-8&serverTimezone=UTC";//地址
    public static final String username = "root";//用户名
    public static final String password = "123456";//密码
    //路径相关
    public static final String parent = "com.photo.judge";//父包名
    public static final String entity = "model.entity";//设置实体类包名
    public static final String mapper = "dao";//设置 Mapper 接口包名
    public static final String service = "service";//设置 Service 接口包名
    public static final String serviceImpl = "service";//设置 Service 实现类接口包名
    public static final String xml = "dao";//设置 Mapper XML 文件包名   默认放到 src/main/resources 下
    public static final String controller = "controller";//设置 controller 文件包名
    //固定路径
    public static final String ftlUrl = System.getProperty("user.dir") + "\\src\\main\\resources\\com\\demo";//ftl文件所在的位置
    public static final String outUrl = System.getProperty("user.dir") + "\\file";//文件生成出来的位置
    //数据库详细信息字段名
    public static final String TABLE_CAT = "TABLE_CAT";//数据库名
    public static final String TABLE_SCHEM = "TABLE_SCHEM";//模式名称模式
    public static final String TABLE_NAME = "TABLE_NAME";//表名
    public static final String TABLE_TYPE = "TABLE_TYPE";//表类型
    public static final String REMARKS = "REMARKS";//表备注
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String DATA_TYPE = "DATA_TYPE";
    public static final String TYPE_NAME = "TYPE_NAME";
    public static final String COLUMN_SIZE = "COLUMN_SIZE";
    public static final String BUFFER_LENGTH = "BUFFER_LENGTH";
    public static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
    public static final String NUM_PREC_RADIX = "NUM_PREC_RADIX";
    public static final String NULLABLE = "NULLABLE";
    public static final String COLUMN_DEF = "COLUMN_DEF";
    public static final String SQL_DATA_TYPE = "SQL_DATA_TYPE";
    public static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB";
    public static final String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
    public static final String ORDINAL_POSITION = "ORDINAL_POSITION";
    public static final String IS_NULLABLE = "IS_NULLABLE";
    //固定的特殊字符
    private String hashTag = "#";//井号

}
