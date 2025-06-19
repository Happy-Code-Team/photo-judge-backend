package com.photo.judge.userinfo;

import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.photo.judge.model.entity.userinfo.UserInfoExtend;
import com.photo.judge.service.userinfo.UserInfoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class UserInfoTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    void test1() {
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
        wrapper1.or(w->{
            w.eq(UserInfoExtend::getUserCode, "c");
            w.eq(UserInfoExtend::getUserName, "c");
        });
        userInfoService.list(wrapper1);
        //根据条件删除
        userInfoService.lambdaUpdate().in(UserInfoExtend::getUserCode, "a","b","c","d","e").remove();
    }
}
