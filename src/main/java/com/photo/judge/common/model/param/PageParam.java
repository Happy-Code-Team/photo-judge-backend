package com.photo.judge.common.model.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页信息
 */
@Data
public class PageParam implements Serializable {

    @TableField(exist = false)
    private Integer current;//当前页
    @TableField(exist = false)
    private Integer size;//每页大小

    public <T> Page<T> toPage() {
        return new Page<>(current, size);
    }
}