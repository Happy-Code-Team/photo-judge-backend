package com.photo.judge.common.utils;


import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 聂文学 最后一次更新时间:2023-06-14 17:34:31
 */
public class ClassUtilsPhoto {
    //获取到对象的第index个的字段名称   0为第一个字段
    public static <T> String getNameByIndex(T entity, int index) {
        return entity.getClass().getDeclaredFields()[index].getName();
    }

    //为对象中第index个字段赋值
    public static <T> void setVByIndex(T entity, int index, String setValue, String format) {
        String setName = getNameByIndex(entity, index);
        setV(entity, setName, setValue, format);
    }

    //1.批量对对象中的第index个字段赋值
    public static <T> void setVByIndexList(T entity, Map<Integer, String> map, String format) {
        for (Integer index : map.keySet()) {
            String setName = getNameByIndex(entity, index);
            setV(entity, setName, map.get(index), format);
        }
    }

    //2.批量对对象中的第index个字段赋值
    public static <T> void setVByIndexList(T entity, int[] indexList, String[] setValueList, String format) {
        if (indexList.length != setValueList.length) {
            return;
        }
        for (int i = 0; i < indexList.length; i++) {
            String setName = getNameByIndex(entity, indexList[i]);
            setV(entity, setName, setValueList[i], format);
        }
    }

    //为对象中的某个名称的字段赋值
    public static <T> void setV(T entity, String setName, String setValue) {
        setV(entity, setName, setValue, "");
    }

    //为对象中的某个名称的字段赋值
    public static <T> void setV(T entity, String setName, String setValue, String format) {
        Map<String, String> map = new HashMap<>();
        map.put(setName, setValue);
        setV(entity, map, format);
    }

    //批量为对象中的某个名称的字段赋值
    public static <T> void setV(T entity, Map<String, String> map) {
        setV(entity, map, "");
    }

    //批量为对象中的某个名称的字段赋值
    public static <T> void setV(T entity, Map<String, String> map, String format) {
        if (map == null || map.size() == 0) {
            return;
        }
        List<Field> list = getNameAll(entity);
        Map<String, List<Field>> fieldMap = list.stream().collect(Collectors.groupingBy(l -> l.getName()));
        Class clazz = entity.getClass();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String setName = entry.getKey();
                String setValue = entry.getValue();
                List<Field> fieldList = fieldMap.get(setName);
                if (fieldList != null && fieldList.size() > 0) {
                    String type = fieldList.get(0).getGenericType().toString();
                    setName = setName.substring(0, 1).toUpperCase() + setName.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                        Method m = clazz.getMethod("set" + setName, String.class);
                        m.invoke(entity, setValue);
                    } else if (type.equals("class java.util.Date")) {
                        Method m = clazz.getMethod("set" + setName, Date.class);
                        Date date = new SimpleDateFormat((format == null || format.length() == 0) ? "yyyy-MM-dd" : format).parse(setValue);
                        m.invoke(entity, date);
                    } else if (type.equals("class java.lang.Integer")) {
                        Method m = entity.getClass().getMethod("set" + setName, Integer.class);
                        m.invoke(entity, Integer.valueOf(setValue));
                    } else if (type.equals("class java.lang.Double")) {
                        Method m = entity.getClass().getMethod("set" + setName, Double.class);
                        m.invoke(entity, Double.valueOf(setValue));
                    } else if (type.equals("class java.lang.Long")) {
                        Method m = entity.getClass().getMethod("set" + setName, Long.class);
                        m.invoke(entity, Long.valueOf(setValue));
                    } else if (type.equals("class java.lang.Boolean")) {
                        Method m = entity.getClass().getMethod("set" + setName, Boolean.class);
                        m.invoke(entity, Boolean.valueOf(setValue));
                    } else if (type.equals("class java.math.BigDecimal")) {
                        Method m = entity.getClass().getMethod("set" + setName, BigDecimal.class);
                        m.invoke(entity, new BigDecimal(setValue));
                    } else if (type.equals("int")) {
                        Method m = entity.getClass().getMethod("set" + setName, int.class);
                        m.invoke(entity, Integer.valueOf(setValue));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取到对象中第index个字段值
    public static <T> Object getVByIndex(T entity, int index) {
        String setName = getNameByIndex(entity, index);
        return getV(entity, setName);
    }

    //获取到对象中的某个名称的字段值
    public static <T> Object getV(T entity, String getName) {
        try {
            //获取到这个名字对应的Field
            List<Field> nameAll = getNameAll(entity);
            Map<String, List<Field>> map = nameAll.stream().collect(Collectors.groupingBy(n -> n.getName()));
            List<Field> fieldList = map.get(getName);
            if(Objects.isNull(fieldList) || fieldList.size()==0){
                throw new RuntimeException("未知的字段："+getName);
            }
            String type = fieldList.get(0).getGenericType().toString();
            //不对基础类型进行get  (因为一般的实体类没有int之类的类型，如果有父类中是有int等之类的，默认值是0，所以在get的时候不允许get出int之类的数据)
            if (type.equals("class java.lang.String") || type.equals("class java.util.Date") || type.equals("class java.lang.Integer") ||
                    type.equals("class java.lang.Double") || type.equals("class java.lang.Long") || type.equals("class java.lang.Boolean") ||
                    type.equals("class java.math.BigDecimal")
            ) {
                getName = getName.substring(0, 1).toUpperCase() + getName.substring(1); // 将属性的首字符大写，方便构造get，set方法
                Method m = entity.getClass().getMethod("get" + getName);
                Object value = m.invoke(entity);
                return value;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("没有get"+getName + "方法--->"+e.getMessage());
        }
    }

    //获取到某个对象的所有非空字段值集合
    public static <T> List<Object> getVList(T entity) {
        return getVList(entity, 0, entity.getClass().getDeclaredFields().length);
    }

    //获取到某个对象在某个区间的全部非空字段值集合
    public static <T> List<Object> getVList(T entity, int begin, int end) {
        List<Object> objList = new ArrayList<>();
        Field[] fieldList = entity.getClass().getDeclaredFields();
        if (end > fieldList.length) {
            return null;
        }
        for (int i = begin; i < end; i++) {
            String name = fieldList[i].getName();
            Object v = getV(entity, name);
            if (!Objects.isNull(v)) {
                objList.add(v);
            }
        }
        return objList;
    }

    //获取到某个对象的全部非空字段值集合(包括父类)
    public static <T> List<Object> getVListAndSuper(T entity) throws Exception {
        List<Field> list = getNameAll(entity);//全部的字段名称集合
        List<Object> objList = new ArrayList<>();//非空字段集合
        for (Field field : list) {
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
            Method m = entity.getClass().getMethod("get" + name);
            Object value = m.invoke(entity); // 调用getter方法获取属性值
            if (!Objects.isNull(value)) {
                objList.add(value);
            }
        }
        return objList;
    }

    //获取到某个对象的全部字段集合
    public static <T> List<Field> getName(T entity) {
        Class clazz = entity.getClass();
        //获取所有的bean中所有的成员变量(包括父类)的名称以及类型
        List<Field> list = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if(field.getName().equals("serialVersionUID")){
                continue;
            }
            if (!list.contains(field)) {
                list.add(field);
            }
        }
        return list;
    }

    //获取到某个对象的全部字段集合(包括父类)
    public static <T> List<Field> getNameAll(T entity) {
        Class clazz = entity.getClass();
        //获取所有的bean中所有的成员变量(包括父类)的名称以及类型
        List<Field> list = new ArrayList<>();
        while (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if(field.getName().equals("serialVersionUID")){
                    continue;
                }
                if (!list.contains(field)) {
                    list.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }

    //获取到某个对象的所有非空字段值集合 返回Map
    public static <T> Map<String, Object> getVMapList(T entity) {
        return getVMapList(entity, 0, entity.getClass().getDeclaredFields().length, true);
    }

    //获取到某个对象的所有非空字段值集合 返回Map
    public static <T> Map<String, Object> getVMapList(T entity, boolean flag) {
        return getVMapList(entity, 0, entity.getClass().getDeclaredFields().length, flag);
    }

    //获取到某个对象在某个区间的全部非空字段值集合  返回Map  flag是判断是否转为下划线 true->转化为下划线 false->保持原来的值  默认为true
    public static <T> Map<String, Object> getVMapList(T entity, int begin, int end, boolean flag) {
        Map<String, Object> map = new HashMap<>();
        Field[] fieldList = entity.getClass().getDeclaredFields();
        if (end > fieldList.length) {
            return null;
        }
        for (int i = begin; i < end; i++) {
            String name = fieldList[i].getName();
            if ("serialVersionUID".equals(name)) {
                continue;
            }
            Object v = getV(entity, name);
            if (!Objects.isNull(v)) {
                if (flag) {
                    String simpleName = StrUtil.toUnderlineCase(name);//驼峰转下划线
                    map.put(simpleName, v);
                } else {
                    map.put(name, v);
                }
            }
        }
        return map;
    }

    //获取到某个对象的所有非空字段值集合 返回Map(包括父类)  flag:为空是否put  默认否
    public static <T> Map<String, Object> getVMapListAndSuper(T entity){
        return getVMapListAndSuper(entity, false);
    }

    //获取到某个对象的所有非空字段值集合 返回Map(包括父类)  flag:为空是否put  true:是  false:否
    public static <T> Map<String, Object> getVMapListAndSuper(T entity, boolean flag){
        List<Field> list = getNameAll(entity);//全部的字段Field集合
        Map<String, Object> map = new HashMap<>();//
        for (Field field : list) {
            String name = field.getName();
            if ("serialVersionUID".equals(name)) {
                continue;
            }
            Object value = getV(entity, name);

            if (Objects.isNull(value)) {
                //如果为空判断flag  如果是true则put一个空字符串如果是false则不进行操作
                if (flag) {
                    value = "";
                    map.put(name, value);
                }
            } else {
                //如果不为空，正常put
                map.put(name, value);
            }
        }
        return map;
    }
}