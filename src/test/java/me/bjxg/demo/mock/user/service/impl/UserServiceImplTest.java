package me.bjxg.demo.mock.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.bjxg.demo.mock.user.model.User;
import me.bjxg.demo.mock.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by chengcy on 2017/3/9.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml"})
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUsername("ccy");
        int count = userService.addUser(user);
        log.info("插入记录的数量：{},测试结果：{}",count,count==1);

        Assert.assertEquals(count,1);
    }
    @Test
    public void testGetUserByID() throws Exception {
        User user =  userService.getUserByID(1);
        log.info("查询结果：{}", JSONObject.toJSON(user));

        Assert.assertTrue(user!=null);
    }
}