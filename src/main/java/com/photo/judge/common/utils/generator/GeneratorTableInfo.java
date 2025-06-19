package com.photo.judge.common.utils.generator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//数据库表详细信息
@Data
public class GeneratorTableInfo {
    //表的基本信息
    private String tableCat;//数据库名
    private String tableSchem;//模式名称模式
    private String tableName;//表名          wit_common_rule
    private String tableType;//表类型
    private String remarks;//表备注
    //生成的实体信息
    private String smallHump;//小驼峰        witCommonRule
    private String bigHump;//大驼峰          WitCommonRule
    private String packageName;//包名        witcommonrule

    private String idKeyStr;//主键名称
    private String idKeyStrSmallHump;//主键名称 小驼峰
    private List<GeneratorTableColumnInfo> generatorTableColumnList = new ArrayList<>();

}
