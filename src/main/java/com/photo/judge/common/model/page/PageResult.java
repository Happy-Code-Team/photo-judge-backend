package com.photo.judge.common.model.page;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

// 自定义分页响应对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private List<T> list;       // 当前页数据
    private Long total;         // 总记录数
    private Integer current;    // 当前页码
    private Integer size;   // 每页条数

    //page转PageResult
    public static <T> PageResult<T> from(Page<T> page) {
        return new PageResult<>(page.getRecords(), page.getTotal(), Math.toIntExact(page.getCurrent()), Math.toIntExact(page.getSize()));
    }

    //page转PageResult——>拷贝
    public static <T, R> PageResult<R> fromClone(Page<T> page, Class<R> targetType) {
        // 使用流转换List元素类型
        List<R> voList = page.getRecords().stream().map(source -> BeanUtil.copyProperties(source, targetType)).collect(Collectors.toList());
        return new PageResult<>(voList, page.getTotal(), Math.toIntExact(page.getCurrent()), Math.toIntExact(page.getSize()));
    }

}