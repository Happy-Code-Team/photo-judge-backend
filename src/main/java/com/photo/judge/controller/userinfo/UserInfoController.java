package com.photo.judge.controller.userinfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.photo.judge.common.model.page.PageResult;
import com.photo.judge.common.response.Response;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import com.photo.judge.model.vo.userinfo.UserInfoVO;
import com.photo.judge.service.userinfo.UserInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 用户信息表controller
 */
@Slf4j
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /** 分页查询 **/
    @PostMapping("/userinfo/selectPage")
    public Response selectPage(@RequestBody UserInfoExtend userInfoExtend) {
        Page<UserInfoExtend> page = userInfoService.lambdaQuery().eq(UserInfoExtend::getUserPassword, "123456").page(userInfoExtend.getPage());
        return Response.success(PageResult.fromClone(page, UserInfoVO.class));

    }
}