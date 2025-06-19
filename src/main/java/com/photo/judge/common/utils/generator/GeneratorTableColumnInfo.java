package com.photo.judge.common.utils.generator;

import lombok.Data;

//数据库字段详细信息
@Data
public class GeneratorTableColumnInfo {
    private String columnName;//字段名称   wit_common_rule_id
    private String typeName;//数据库字段名
    private String remarks;//字段备注

    private String smallHump;//小驼峰        witCommonRuleId
    private String javaType;//java的字段类型
    private String mybatisJavaType;//xml文件里面的类型   #{item.id,jdbcType=VARCHAR}
}
