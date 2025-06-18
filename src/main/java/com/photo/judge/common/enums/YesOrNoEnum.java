package com.photo.judge.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否枚举  0否  1是
 */
@AllArgsConstructor
@Getter
public enum YesOrNoEnum {

    Y("1", "是"),
    N("0", "否");

    private String code;//编码
    private String name;//名称

}
