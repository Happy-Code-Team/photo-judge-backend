package com.photo.judge.common.utils.generator;

import java.util.Objects;

//组装文件内容
public class GeneratorPackageContentUtil {

    //组装实体类
    public static String packageEntity(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("package " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + ";").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import com.baomidou.mybatisplus.annotation.*;").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + ".common.annotation.Desc;").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + ".common.model.param.PageParam;").append(System.lineSeparator());
        content.append("import lombok.AllArgsConstructor;").append(System.lineSeparator());
        content.append("import lombok.Data;").append(System.lineSeparator());
        content.append("import lombok.NoArgsConstructor;").append(System.lineSeparator());
        content.append("import lombok.experimental.Accessors;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import java.io.Serializable;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("@Data").append(System.lineSeparator());
        content.append("@AllArgsConstructor").append(System.lineSeparator());
        content.append("@NoArgsConstructor").append(System.lineSeparator());
        content.append("@TableName(\"" + GeneratorConfigInfo.tableName + "\")").append(System.lineSeparator());
        content.append("@Desc(\"" + generatorTable.getRemarks() + "\")").append(System.lineSeparator());
        content.append("@Accessors(chain = true)//开启链式").append(System.lineSeparator());
        content.append("public class " + generatorTable.getBigHump() + " extends PageParam implements Serializable {").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("    private static final long serialVersionUID = 1L;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        //组装字段
        for (GeneratorTableColumnInfo column : generatorTable.getGeneratorTableColumnList()) {
            if (Objects.equals(column.getColumnName(), generatorTable.getIdKeyStr())) {
                content.append("    @TableId(value = \"" + column.getColumnName() + "\", type = IdType.ASSIGN_ID)").append(System.lineSeparator());
            } else if (Objects.equals(column.getColumnName(), "creater") || Objects.equals(column.getColumnName(), "create_time")) {
                content.append("    @TableField(value = \"" + column.getColumnName() + "\", fill = FieldFill.INSERT)").append(System.lineSeparator());
            } else if (Objects.equals(column.getColumnName(), "updater") || Objects.equals(column.getColumnName(), "update_time")) {
                content.append("    @TableField(value = \"" + column.getColumnName() + "\", fill = FieldFill.UPDATE)").append(System.lineSeparator());
            } else if (Objects.equals(column.getColumnName(), "ts")) {
                content.append("    @TableField(value = \"" + column.getColumnName() + "\", fill = FieldFill.INSERT_UPDATE)").append(System.lineSeparator());
            } else {
                content.append("    @TableField(\"" + column.getColumnName() + "\")").append(System.lineSeparator());
            }
            content.append("    @Desc(\"" + column.getRemarks() + "\")").append(System.lineSeparator());
            content.append("    private " + column.getJavaType() + " " + column.getSmallHump() + ";").append(System.lineSeparator());
            content.append(System.lineSeparator());
        }
        content.append("}");
        return content.toString();
    }

    //组装实体类Extend
    public static String packageEntityExtend(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("package " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + ";").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import com.baomidou.mybatisplus.annotation.TableField;").append(System.lineSeparator());
        content.append("import com.baomidou.mybatisplus.annotation.TableName;").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + ".common.annotation.Desc;").append(System.lineSeparator());
        content.append("import lombok.AllArgsConstructor;").append(System.lineSeparator());
        content.append("import lombok.Data;").append(System.lineSeparator());
        content.append("import lombok.NoArgsConstructor;").append(System.lineSeparator());
        content.append("import lombok.experimental.Accessors;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import java.io.Serializable;").append(System.lineSeparator());
        content.append("import java.util.List;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("@Data").append(System.lineSeparator());
        content.append("@AllArgsConstructor").append(System.lineSeparator());
        content.append("@NoArgsConstructor").append(System.lineSeparator());
        content.append("@TableName(\"" + GeneratorConfigInfo.tableName + "\")").append(System.lineSeparator());
        content.append("@Desc(\"" + generatorTable.getRemarks() + "#扩展\")").append(System.lineSeparator());
        content.append("@Accessors(chain = true)//开启链式").append(System.lineSeparator());
        content.append("public class " + generatorTable.getBigHump() + "Extend extends " + generatorTable.getBigHump() + " implements Serializable {").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("    private static final long serialVersionUID = 1L;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("    @TableField(exist = false)").append(System.lineSeparator());
        content.append("    private List<String> idList;//主键id集合").append(System.lineSeparator());
        content.append("}");
        return content.toString();
    }

    //组装Dao接口
    public static String packageDao(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("package " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.mapper + "." + generatorTable.getPackageName() + ";").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.mapper + ".mydao.MyDao;").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend;").append(System.lineSeparator());
        content.append("import org.apache.ibatis.annotations.Mapper;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("/**").append(System.lineSeparator());
        content.append(" * " + generatorTable.getRemarks() + "Dao").append(System.lineSeparator());
        content.append(" */").append(System.lineSeparator());
        content.append("@Mapper").append(System.lineSeparator());
        content.append("public interface " + generatorTable.getBigHump() + "Dao extends MyDao<" + generatorTable.getBigHump() + "Extend> {").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("}");
        return content.toString();
    }

    //组装Xml
    public static String packageXml(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(System.lineSeparator());
        content.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append(System.lineSeparator());
        content.append("<mapper namespace=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.mapper + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Dao\">").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        //组装xml内部信息
        allColumn(content, generatorTable);
        allColumnAlias(content, generatorTable);
        allColumnValue(content, generatorTable);
        allColumnItemValue(content, generatorTable);
        allColumnSet(content, generatorTable);
        allColumnCond(content, generatorTable);
        insertXml(content, generatorTable);
        insertBatchXml(content, generatorTable);
        delByIdXml(content, generatorTable);
        delByIdListXml(content, generatorTable);
        delXml(content, generatorTable);
        updateByIdXml(content, generatorTable);
        selectByIdXml(content, generatorTable);
        selectXml(content, generatorTable);
        content.append("</mapper>");
        return content.toString();
    }

    //组装Extend.Xml
    public static String packageXmlExtend(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(System.lineSeparator());
        content.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append(System.lineSeparator());
        content.append("<mapper namespace=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.mapper + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Dao\">").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("    <sql id=\"allColumnCondExtend\">").append(System.lineSeparator());
        content.append("    ").append(System.lineSeparator());
        content.append("    </sql>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("</mapper>");
        return content.toString();
    }

    //组装service接口
    public static String packageService(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("package " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.service + "." + generatorTable.getPackageName() + ";").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend;").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.service + ".myservice.MyService;").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("/**").append(System.lineSeparator());
        content.append(" * " + generatorTable.getRemarks() + "service").append(System.lineSeparator());
        content.append(" */").append(System.lineSeparator());
        content.append("public interface " + generatorTable.getBigHump() + "Service extends MyService<" + generatorTable.getBigHump() + "Extend> {").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("}");
        return content.toString();
    }

    //组装service接口实现类
    public static String packageServiceImpl(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("package " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.serviceImpl + "." + generatorTable.getPackageName() + ";").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.mapper + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Dao;").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend;").append(System.lineSeparator());
        content.append("import " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.serviceImpl + ".myservice.MyServiceImpl;").append(System.lineSeparator());
        content.append("import org.springframework.stereotype.Service;").append(System.lineSeparator());
        content.append("/**").append(System.lineSeparator());
        content.append(" * " + generatorTable.getRemarks() + "service实现类").append(System.lineSeparator());
        content.append(" */").append(System.lineSeparator());
        content.append("@Service").append(System.lineSeparator());
        content.append("public class " + generatorTable.getBigHump() + "ServiceImpl extends MyServiceImpl<" + generatorTable.getBigHump() + "Dao, " + generatorTable.getBigHump() + "Extend> implements " + generatorTable.getBigHump() + "Service {").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("}");
        return content.toString();
    }

    //组装controller类
    public static String packageController(GeneratorTableInfo generatorTable) {
        StringBuffer content = new StringBuffer();
        content.append("package " + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.controller + "." + generatorTable.getPackageName() + ";").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("import lombok.extern.slf4j.Slf4j;").append(System.lineSeparator());
        content.append("import org.springframework.web.bind.annotation.RestController;").append(System.lineSeparator());
        content.append("/**").append(System.lineSeparator());
        content.append(" * " + generatorTable.getRemarks() + "controller").append(System.lineSeparator());
        content.append(" */").append(System.lineSeparator());
        content.append("@Slf4j").append(System.lineSeparator());
        content.append("@RestController").append(System.lineSeparator());
        content.append("public class " + generatorTable.getBigHump() + "Controller {").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
        content.append("}");
        return content.toString();
    }

    //===================================================================================================
    //id,
    private static void allColumn(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <sql id=\"allColumn\">").append(System.lineSeparator());
        content.append("        ");
        for (int i = 0; i < generatorTable.getGeneratorTableColumnList().size(); i++) {
            GeneratorTableColumnInfo column = generatorTable.getGeneratorTableColumnList().get(i);
            String symbol = i == generatorTable.getGeneratorTableColumnList().size() - 1 ? "" : ",";//最后一个字段末尾没有逗号
            String columnStr = column.getColumnName();//字段拼接的内容
            if ((i + 1) % 4 == 0) {
                content.append(columnStr + symbol);
                if (i != generatorTable.getGeneratorTableColumnList().size() - 1) {
                    content.append(System.lineSeparator());
                    content.append("        ");
                }
                continue;
            }
            if (i == generatorTable.getGeneratorTableColumnList().size() - 1) {
                content.append(columnStr + symbol);
            } else {
                content.append(String.format("%-50s", columnStr + symbol));
            }
        }
        content.append("").append(System.lineSeparator());
        content.append("    </sql>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    //id as id,
    private static void allColumnAlias(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <sql id=\"allColumnAlias\">").append(System.lineSeparator());
        content.append("        ");
        for (int i = 0; i < generatorTable.getGeneratorTableColumnList().size(); i++) {
            GeneratorTableColumnInfo column = generatorTable.getGeneratorTableColumnList().get(i);
            String symbol = i == generatorTable.getGeneratorTableColumnList().size() - 1 ? "" : ",";//最后一个字段末尾没有逗号
            String columnStr = column.getColumnName() + " as " + column.getSmallHump();//字段拼接的内容
            if ((i + 1) % 4 == 0) {
                content.append(columnStr + symbol);
                if (i != generatorTable.getGeneratorTableColumnList().size() - 1) {
                    content.append(System.lineSeparator());
                    content.append("        ");
                }
                continue;
            }
            if (i == generatorTable.getGeneratorTableColumnList().size() - 1) {
                content.append(columnStr + symbol);
            } else {
                content.append(String.format("%-50s", columnStr + symbol));
            }
        }
        content.append("").append(System.lineSeparator());
        content.append("    </sql>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    //#{item.id,jdbcType=VARCHAR},
    private static void allColumnValue(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <sql id=\"allColumnValue\">").append(System.lineSeparator());
        content.append("        ");
        for (int i = 0; i < generatorTable.getGeneratorTableColumnList().size(); i++) {
            GeneratorTableColumnInfo column = generatorTable.getGeneratorTableColumnList().get(i);
            String symbol = i == generatorTable.getGeneratorTableColumnList().size() - 1 ? "" : ",";//最后一个字段末尾没有逗号
            String columnStr = "#{" + column.getSmallHump() + ",jdbcType=" + column.getMybatisJavaType() + "}";
            if ((i + 1) % 4 == 0) {
                content.append(columnStr + symbol);
                if (i != generatorTable.getGeneratorTableColumnList().size() - 1) {
                    content.append(System.lineSeparator());
                    content.append("        ");
                }
                continue;
            }
            if (i == generatorTable.getGeneratorTableColumnList().size() - 1) {
                content.append(columnStr + symbol);
            } else {
                content.append(String.format("%-50s", columnStr + symbol));
            }
        }
        content.append("").append(System.lineSeparator());
        content.append("    </sql>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    //#{item.id,jdbcType=VARCHAR},
    private static void allColumnItemValue(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <sql id=\"allColumnItemValue\">").append(System.lineSeparator());
        content.append("        ");
        for (int i = 0; i < generatorTable.getGeneratorTableColumnList().size(); i++) {
            GeneratorTableColumnInfo column = generatorTable.getGeneratorTableColumnList().get(i);
            String symbol = i == generatorTable.getGeneratorTableColumnList().size() - 1 ? "" : ",";//最后一个字段末尾没有逗号
            String columnStr = "#{item." + column.getSmallHump() + ",jdbcType=" + column.getMybatisJavaType() + "}";
            if ((i + 1) % 4 == 0) {
                content.append(columnStr + symbol);
                if (i != generatorTable.getGeneratorTableColumnList().size() - 1) {
                    content.append(System.lineSeparator());
                    content.append("        ");
                }
                continue;
            }
            if (i == generatorTable.getGeneratorTableColumnList().size() - 1) {
                content.append(columnStr + symbol);
            } else {
                content.append(String.format("%-50s", columnStr + symbol));
            }
        }
        content.append("").append(System.lineSeparator());
        content.append("    </sql>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    //#{item.id,jdbcType=VARCHAR},
    private static void allColumnSet(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <sql id=\"allColumnSet\">").append(System.lineSeparator());
        content.append("        <trim suffixOverrides=\",\">").append(System.lineSeparator());
        for (int i = 0; i < generatorTable.getGeneratorTableColumnList().size(); i++) {
            GeneratorTableColumnInfo column = generatorTable.getGeneratorTableColumnList().get(i);
            if (Objects.equals(column.getColumnName(), generatorTable.getIdKeyStr())) {
                continue;
            }
            content.append("            <if test=\"" + column.getSmallHump() + " != null and " + column.getSmallHump() + " != ''\">").append(System.lineSeparator());
            content.append("                " + column.getColumnName() + "=#{" + column.getSmallHump() + ",jdbcType=VARCHAR},").append(System.lineSeparator());
            content.append("            </if>").append(System.lineSeparator());
        }
        content.append("        </trim>").append(System.lineSeparator());
        content.append("    </sql>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void allColumnCond(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <sql id=\"allColumnCond\">").append(System.lineSeparator());
        for (int i = 0; i < generatorTable.getGeneratorTableColumnList().size(); i++) {
            GeneratorTableColumnInfo column = generatorTable.getGeneratorTableColumnList().get(i);
            content.append("        <if test=\"" + column.getSmallHump() + " != null and " + column.getSmallHump() + " != ''\">").append(System.lineSeparator());
            content.append("            AND " + column.getColumnName() + "=#{" + column.getSmallHump() + ",jdbcType=VARCHAR}").append(System.lineSeparator());
            content.append("        </if>").append(System.lineSeparator());
        }
        content.append("    </sql>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void insertXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <insert id=\"insertXml\" parameterType=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend\">").append(System.lineSeparator());
        content.append("        INSERT INTO " + generatorTable.getTableName() + " (").append(System.lineSeparator());
        content.append("        <include refid=\"allColumn\" />").append(System.lineSeparator());
        content.append("        ) VALUES (").append(System.lineSeparator());
        content.append("        <include refid=\"allColumnValue\" />").append(System.lineSeparator());
        content.append("        )").append(System.lineSeparator());
        content.append("    </insert>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void insertBatchXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <insert id=\"insertBatchXml\" parameterType=\"java.util.List\">").append(System.lineSeparator());
        content.append("        INSERT INTO " + generatorTable.getTableName() + " (").append(System.lineSeparator());
        content.append("        <include refid=\"allColumn\" />").append(System.lineSeparator());
        content.append("        ) VALUES").append(System.lineSeparator());
        content.append("        <foreach collection=\"list\" item=\"item\" separator=\",\">").append(System.lineSeparator());
        content.append("            (").append(System.lineSeparator());
        content.append("            <include refid=\"allColumnItemValue\" />").append(System.lineSeparator());
        content.append("            )").append(System.lineSeparator());
        content.append("        </foreach>").append(System.lineSeparator());
        content.append("    </insert>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void delByIdXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <delete id=\"delByIdXml\" parameterType=\"string\">").append(System.lineSeparator());
        content.append("        DELETE FROM " + generatorTable.getTableName() + "").append(System.lineSeparator());
        content.append("        WHERE " + generatorTable.getIdKeyStr() + "=#{value}").append(System.lineSeparator());
        content.append("    </delete>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void delByIdListXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <delete id=\"delByIdListXml\" parameterType=\"java.util.List\">").append(System.lineSeparator());
        content.append("        DELETE FROM " + generatorTable.getTableName() + "").append(System.lineSeparator());
        content.append("        WHERE " + generatorTable.getIdKeyStr() + " IN").append(System.lineSeparator());
        content.append("        <foreach collection=\"list\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\">").append(System.lineSeparator());
        content.append("            #{id}").append(System.lineSeparator());
        content.append("        </foreach>").append(System.lineSeparator());
        content.append("    </delete>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void delXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <delete id=\"delXml\" parameterType=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend\">").append(System.lineSeparator());
        content.append("        DELETE FROM " + generatorTable.getTableName() + "").append(System.lineSeparator());
        content.append("        WHERE 1=1").append(System.lineSeparator());
        content.append("        <include refid=\"allColumnCond\" />").append(System.lineSeparator());
        content.append("    </delete>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void updateByIdXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <update id=\"updateByIdXml\" parameterType=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend\">").append(System.lineSeparator());
        content.append("        UPDATE " + generatorTable.getTableName() + " SET").append(System.lineSeparator());
        content.append("        <include refid=\"allColumnSet\" />").append(System.lineSeparator());
        content.append("        WHERE " + generatorTable.getIdKeyStr() + "=#{" + generatorTable.getIdKeyStrSmallHump() + "}").append(System.lineSeparator());
        content.append("    </update>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void selectByIdXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <select id=\"selectByIdXml\" resultType=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend\">").append(System.lineSeparator());
        content.append("        SELECT").append(System.lineSeparator());
        content.append("        <include refid=\"allColumnAlias\" />").append(System.lineSeparator());
        content.append("        FROM " + generatorTable.getTableName() + "").append(System.lineSeparator());
        content.append("        WHERE " + generatorTable.getIdKeyStr() + "=#{value}").append(System.lineSeparator());
        content.append("    </select>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

    private static void selectXml(StringBuffer content, GeneratorTableInfo generatorTable) {
        content.append("    <select id=\"selectXml\" parameterType=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend\" resultType=\"" + GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName() + "." + generatorTable.getBigHump() + "Extend\">").append(System.lineSeparator());
        content.append("        SELECT").append(System.lineSeparator());
        content.append("        <include refid=\"allColumnAlias\" />").append(System.lineSeparator());
        content.append("        FROM " + generatorTable.getTableName() + "").append(System.lineSeparator());
        content.append("        WHERE 1=1").append(System.lineSeparator());
        content.append("        <include refid=\"allColumnCond\" />").append(System.lineSeparator());
        content.append("        <include refid=\"allColumnCondExtend\" />").append(System.lineSeparator());
        content.append("    </select>").append(System.lineSeparator());
        content.append("").append(System.lineSeparator());
    }

}
