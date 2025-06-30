package com.photo.judge.userinfo;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import com.photo.judge.service.userinfo.UserInfoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserInfoTest {

    @Resource
    private UserInfoService userInfoService;

    //单表查询相关案例
    @Test
    void test1() {
        //初始化数据
        //INSERT INTO user_info (id, user_code, user_name, user_password, user_avatar, creater, create_time, updater, update_time, ts) VALUES ('1001', 'a', '张三', 'a', 'a', 'admin', '2025-06-27 10:57:28', NULL, NULL, '2025-06-27 10:57:28');
        //INSERT INTO user_info (id, user_code, user_name, user_password, user_avatar, creater, create_time, updater, update_time, ts) VALUES ('1002', 'b', '李四', 'b', 'b', 'admin', '2025-06-27 10:57:28', NULL, NULL, '2025-06-27 10:57:28');
        //INSERT INTO user_info (id, user_code, user_name, user_password, user_avatar, creater, create_time, updater, update_time, ts) VALUES ('1003', 'c', '王五', 'c', 'c', 'admin2', '2025-06-27 10:57:28', NULL, NULL, '2025-06-27 10:57:28');
        //INSERT INTO user_info (id, user_code, user_name, user_password, user_avatar, creater, create_time, updater, update_time, ts) VALUES ('1004', 'd', '于六', 'd', 'd', 'admin2', '2025-06-27 10:57:28', NULL, NULL, '2025-06-27 10:57:28');
        //INSERT INTO user_info (id, user_code, user_name, user_password, user_avatar, creater, create_time, updater, update_time, ts) VALUES ('1005', 'e', '吉七', 'e', 'e', 'admin2', '2025-06-27 10:57:28', NULL, NULL, '2025-06-27 10:57:28');

        //1.基础条件查询（多个AND条件）
        //WHERE (id > '1001' AND creater = 'admin')
        List<UserInfoExtend> list1 = userInfoService.lambdaQuery()
                .gt(UserInfoExtend::getId, "1001")
                .eq(UserInfoExtend::getCreater, "admin")
                .list();
        //2.链式OR条件查询
        //WHERE (user_name LIKE '张%' OR id < '1004')
        List<UserInfoExtend> list2 = userInfoService.lambdaQuery()
                .likeRight(UserInfoExtend::getUserName, "张")
                .or()
                .lt(UserInfoExtend::getId, "1004")
                .list();
        //3.分页查询（带排序）
        //WHERE (creater LIKE '%admin%') ORDER BY create_time DESC LIMIT 3,3
        Page<UserInfoExtend> page3 = new Page<>(2, 3);
        IPage<UserInfoExtend> userPage3 = userInfoService.lambdaQuery()
                .like(UserInfoExtend::getCreater, "admin")
                .orderByDesc(UserInfoExtend::getCreateTime)
                .page(page3);
        List<UserInfoExtend> list3 = userPage3.getRecords();
        //4.SELECT指定字段查询
        //SELECT user_code,user_name FROM user_info WHERE (creater LIKE '%admin%')
        List<UserInfoExtend> list4 = userInfoService.lambdaQuery()
                .select(UserInfoExtend::getUserCode, UserInfoExtend::getUserName)
                .like(UserInfoExtend::getCreater, "admin")
                .list();
        //5.IN查询 + 时间范围
        //WHERE (creater IN ('admin','admin2') AND create_time >= '2025-03-27 10:15:36')
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);//3个月内的
        String dateTime = threeMonthsAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));//格式化
        List<UserInfoExtend> list5 = userInfoService.lambdaQuery()
                .in(UserInfoExtend::getCreater, Arrays.asList("admin", "admin2"))
                .ge(UserInfoExtend::getCreateTime, dateTime)
                .list();
        //6.嵌套条件查询
        // WHERE ((user_code = 'b' AND id > '1001') OR (user_code = 'c' OR user_code = 'd'))
        List<UserInfoExtend> list6 = userInfoService.lambdaQuery()
                .and(w -> {
                    w.eq(UserInfoExtend::getUserCode, "b");
                    w.gt(UserInfoExtend::getId, "1001");
                })
                .or(w -> {
                    w.eq(UserInfoExtend::getUserCode, "c");
                    w.or();
                    w.eq(UserInfoExtend::getUserCode, "d");
                })
                .list();
        //7.EXISTS子查询
        //SELECT id,(SELECT user_code FROM user_info bb WHERE bb.id = user_info.id) AS userCode FROM user_info
        //注意：一般不建议在包含联表查询的时候这样写，因为无法对主表起别名（from user_info as a），部分情况无法实现，遇到这种复杂的sql请使用xml的方式
        //或者使用mybatis-plus的联表查询
        QueryWrapper<UserInfoExtend> queryWrapper7 = new QueryWrapper<>();
        queryWrapper7.select("id", "(SELECT user_code FROM user_info bb WHERE bb.id = user_info.id) AS userCode");
        List<UserInfoExtend> list7 = userInfoService.list(queryWrapper7);
        //8.动态条件查询（前端参数过滤）
        //WHERE (user_code = 'a' AND user_name LIKE '%张%' AND creater LIKE '%admin%' AND create_time BETWEEN '2025-01-01' AND '2025-12-12')
        UserInfoExtend params8 = new UserInfoExtend();
        params8.setUserCode("a");
        params8.setCreater("admin");
        params8.setStartTime("2025-01-01");
        params8.setEndTime("2025-12-12");
        userInfoService.lambdaQuery()
                .eq(StrUtil.isNotBlank(params8.getUserCode()), UserInfoExtend::getUserCode, params8.getUserCode())
                .like(StrUtil.isNotBlank(params8.getUserName()), UserInfoExtend::getUserName, params8.getUserName())
                .like(StrUtil.isBlank(params8.getUserName()), UserInfoExtend::getUserName, "张")
                .like(StrUtil.isNotBlank(params8.getCreater()), UserInfoExtend::getCreater, params8.getCreater())
                .between(params8.getStartTime() != null && params8.getEndTime() != null, UserInfoExtend::getCreateTime, params8.getStartTime(), params8.getEndTime())
                .list();
    }

    //联表查询相关案例
    @Test
    void test2() {

    }

    //增删改相关案例
    @Test
    void test3() {
        //单个新增
        userInfoService.save((UserInfoExtend) new UserInfoExtend().setId(null).setUserCode("a").setUserName("a").setUserPassword("a").setUserAvatar("a"));
        //批量新增
        userInfoService.saveBatch(Arrays.asList(
                (UserInfoExtend) new UserInfoExtend().setId("b").setUserCode("b").setUserName("b").setUserPassword("b").setUserAvatar("b"),
                (UserInfoExtend) new UserInfoExtend().setId("c").setUserCode("c").setUserName("c").setUserPassword("c").setUserAvatar("c"),
                (UserInfoExtend) new UserInfoExtend().setId("d").setUserCode("d").setUserName("d").setUserPassword("d").setUserAvatar("d"),
                (UserInfoExtend) new UserInfoExtend().setId("e").setUserCode("e").setUserName("e").setUserPassword("e").setUserAvatar("e")
        ));
        //根据条件修改
        userInfoService.lambdaUpdate().eq(UserInfoExtend::getUserCode, "a").update((UserInfoExtend) new UserInfoExtend().setUserName("aaa"));
        //根据id批量修改
        userInfoService.updateBatchById(Arrays.asList(
                (UserInfoExtend) new UserInfoExtend().setId("b").setUserCode("b").setUserName("bbbbb").setUserPassword("b").setUserAvatar("b"),
                (UserInfoExtend) new UserInfoExtend().setId("c").setUserCode("c").setUserName("ccccc").setUserPassword("c").setUserAvatar("c"),
                (UserInfoExtend) new UserInfoExtend().setId("d").setUserCode("d").setUserName("ddddd").setUserPassword("d").setUserAvatar("d"),
                (UserInfoExtend) new UserInfoExtend().setId("e").setUserCode("e").setUserName("eeeee").setUserPassword("e").setUserAvatar("e")
        ));
        //根据条件查询
        //SELECT t.id,t.user_code,t.user_name,t.user_password,t.user_avatar,t.creater,t.create_time,t.updater,t.update_time,t.ts
        //FROM user_info t WHERE (
        //    t.user_code = 'a'
        //    OR t.user_code = 'b'
        //    OR (t.user_code = 'c' AND t.user_name = 'c')
        //)
        MPJLambdaWrapper<UserInfoExtend> wrapper1 = new MPJLambdaWrapper<>();
        wrapper1.eq(UserInfoExtend::getUserCode, "a");
        wrapper1.or();
        wrapper1.eq(UserInfoExtend::getUserCode, "b");
        wrapper1.or(w -> {
            w.eq(UserInfoExtend::getUserCode, "c");
            w.eq(UserInfoExtend::getUserName, "c");
        });
        userInfoService.list(wrapper1);
        //根据条件删除
        userInfoService.lambdaUpdate().in(UserInfoExtend::getUserCode, "a", "b", "c", "d", "e").remove();
    }


}
