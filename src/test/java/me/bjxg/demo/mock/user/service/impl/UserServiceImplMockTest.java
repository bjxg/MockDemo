package me.bjxg.demo.mock.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.bjxg.demo.mock.user.dao.UserMapper;
import me.bjxg.demo.mock.user.model.User;
import me.bjxg.demo.mock.user.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * Created by chengcy on 2017/3/9.
 */
@Slf4j
public class UserServiceImplMockTest {
    @Rule
    public PowerMockRule powerMockRule;

    UserServiceImpl userService = new UserServiceImpl();

    UserMapper userMapper = Mockito.mock(UserMapper.class) ;

    @Before
    public void setUp(){
        userService.setUserMapper(userMapper);

    }

    @Test
    public void testAddUser() throws Exception {
        PowerMockito.when(userMapper.insert(Mockito.<User>any())).thenReturn(1);

        User user = new User();
        user.setUsername("ccy");
        int count = userService.addUser(user);
        log.info("测试结果：{}，插入记录的数量：{}",count==1,count);

        Assert.assertEquals(count,1);
    }

    @Test
    public void testGetUserByID() throws Exception {
        int userId=1;
        User user = new User();
        user.setId(userId);

        PowerMockito.when(userMapper.selectByPrimaryKey(Mockito.anyInt())).thenReturn(user);
        User userResult =  userService.getUserByID(userId);
        log.info("测试结果：{}，查询结果：{}",userResult.getId()==userId,JSONObject.toJSON(userResult));

        Assert.assertTrue(userResult.getId()==userId);
    }

}