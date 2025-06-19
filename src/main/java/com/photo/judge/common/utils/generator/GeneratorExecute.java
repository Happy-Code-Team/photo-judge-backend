package com.photo.judge.common.utils.generator;

import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

//执行器
public class GeneratorExecute {

    public static void main(String[] args) throws Exception {
        GeneratorTableInfo generatorTable = getGeneratorTable();
        generateEntity(generatorTable);//生成实体类
        generateEntityExtend(generatorTable);//生成实体类Extend
        generateDao(generatorTable);//生成dao接口
        generateXml(generatorTable);//生成dao.xml文件
        generateXmlExtend(generatorTable);//生成dao.xml文件Extend
        generateService(generatorTable);//生成service接口
        generateServiceImpl(generatorTable);//生成service接口实现类
        generateController(generatorTable);//生成controller类
    }

    //生成实体类
    private static void generateEntity(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageEntity(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\java\\" + (GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + ".java");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //生成实体类Extend
    private static void generateEntityExtend(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageEntityExtend(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\java\\" + (GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.entity + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + "Extend.java");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //生成dao接口
    private static void generateDao(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageDao(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\java\\" + (GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.mapper + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + "Dao.java");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //生成dao.xml文件
    private static void generateXml(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageXml(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\resources\\" + (GeneratorConfigInfo.xml + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + "Dao.xml");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //生成dao.xml文件Extend
    private static void generateXmlExtend(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageXmlExtend(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\resources\\" + (GeneratorConfigInfo.xml + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + "DaoExtend.xml");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //生成service接口
    private static void generateService(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageService(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\java\\" + (GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.service + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + "Service.java");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //生成service接口实现类
    private static void generateServiceImpl(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageServiceImpl(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\java\\" + (GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.serviceImpl + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + "ServiceImpl.java");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //生成controller类
    private static void generateController(GeneratorTableInfo generatorTable) throws Exception {
        //组装文件内容
        String fileContent = GeneratorPackageContentUtil.packageController(generatorTable);
        //构建输出位置的路径
        Path dirPath = Paths.get(GeneratorConfigInfo.outUrl + "\\java\\" + (GeneratorConfigInfo.parent + "." + GeneratorConfigInfo.controller + "." + generatorTable.getPackageName()).replace(".", File.separator));
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);// 创建目录（如果不存在）
        }
        //构建完整文件路径
        Path filePath = dirPath.resolve(generatorTable.getBigHump() + "Controller.java");
        //写入文件内容
        Files.write(filePath, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    //================================================================================================================

    /**
     * 获取到表详细信息
     **/
    public static GeneratorTableInfo getGeneratorTable() throws Exception {
        Class.forName(GeneratorConfigInfo.driverName);
        Properties props = new Properties();
        props.setProperty("user", GeneratorConfigInfo.username);//用户名
        props.setProperty("password", GeneratorConfigInfo.password);//密码
        props.setProperty("remarks", "true");//设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
        Connection connection = DriverManager.getConnection(GeneratorConfigInfo.url, props);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet rs = databaseMetaData.getTables(GeneratorConfigInfo.databaseName, null, GeneratorConfigInfo.tableName, null);
        GeneratorTableInfo generatorTable = new GeneratorTableInfo();
        while (rs.next()) {
            generatorTable.setTableCat(rs.getString(GeneratorConfigInfo.TABLE_CAT));
            generatorTable.setTableSchem(rs.getString(GeneratorConfigInfo.TABLE_SCHEM));
            generatorTable.setTableType(rs.getString(GeneratorConfigInfo.TABLE_TYPE));
            generatorTable.setRemarks(rs.getString(GeneratorConfigInfo.REMARKS));
            generatorTable.setTableName(rs.getString(GeneratorConfigInfo.TABLE_NAME));

            generatorTable.setSmallHump(StrUtil.toCamelCase(generatorTable.getTableName().toLowerCase()));//小驼峰
            generatorTable.setBigHump(generatorTable.getSmallHump().substring(0, 1).toUpperCase() + generatorTable.getSmallHump().substring(1));//大驼峰
            generatorTable.setPackageName(generatorTable.getBigHump().toLowerCase());//包名

            packageTableKey(generatorTable, databaseMetaData);
            packageTableColumn(generatorTable, databaseMetaData);
        }
        closeRs(rs);
        return generatorTable;
    }

    /**
     * 组装字段信息
     **/
    public static void packageTableColumn(GeneratorTableInfo generatorTable, DatabaseMetaData databaseMetaData) throws Exception {
        ResultSet rs = databaseMetaData.getColumns(generatorTable.getTableCat(), generatorTable.getTableSchem(), generatorTable.getTableName(), null);
        while (rs.next()) {
            GeneratorTableColumnInfo column = new GeneratorTableColumnInfo();
            column.setColumnName(rs.getString(GeneratorConfigInfo.COLUMN_NAME));
            if (Objects.equals(rs.getString(GeneratorConfigInfo.TYPE_NAME), "SMALLINT UNSIGNED") || Objects.equals(rs.getString(GeneratorConfigInfo.TYPE_NAME), "TINYINT UNSIGNED")) {
                column.setTypeName("INT");
            } else {
                column.setTypeName("VARCHAR");
            }
            column.setRemarks(rs.getString(GeneratorConfigInfo.REMARKS));

            column.setSmallHump(StrUtil.toCamelCase(column.getColumnName().toLowerCase()));//小驼峰
            column.setJavaType(getJavaType(column.getTypeName()));//java的字段类型
            String mybatisJavaType = "VARCHAR";
            if (Objects.equals(column.getTypeName(), "INT") || Objects.equals(column.getTypeName(), "TINYINT")) {
                mybatisJavaType = "INTEGER";
            }
            column.setMybatisJavaType(mybatisJavaType);

            generatorTable.getGeneratorTableColumnList().add(column);
        }
        closeRs(rs);
    }

    /**
     * 组装主键信息
     **/
    public static void packageTableKey(GeneratorTableInfo generatorTable, DatabaseMetaData databaseMetaData) throws Exception {
        ResultSet rs = databaseMetaData.getPrimaryKeys(generatorTable.getTableCat(), generatorTable.getTableSchem(), generatorTable.getTableName());
        while (rs.next()) {
            generatorTable.setIdKeyStr(rs.getString(4));
            generatorTable.setIdKeyStrSmallHump(StrUtil.toCamelCase(generatorTable.getIdKeyStr().toLowerCase()));
        }
        closeRs(rs);
    }

    /**
     * 获取java字段类型
     **/
    public static String getJavaType(String dataType) {
        dataType = dataType.toLowerCase();
        String type = "";
        if (dataType.contains("varchar") || dataType.contains("char")) {
            type = "String";
        } else if (dataType.contains("decimal")) {
            type = "BigDecimal";
        } else if (dataType.contains("int")) {
            type = "Integer";
        } else {
            type = "String";
        }
        return type;
    }

    public static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}