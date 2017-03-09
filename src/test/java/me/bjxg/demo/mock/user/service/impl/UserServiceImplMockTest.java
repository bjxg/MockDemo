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

import java.util.List;

/**
 * Created by chengcy on 2017/3/9.
 */
@Slf4j
public class UserServiceImplMockTest {
    @Rule
    public PowerMockRule powerMockRule;

    UserServiceImpl userService = PowerMockito.spy(new UserServiceImpl());

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

    /**
     * 私有方法的mock
     * @throws Exception
     */
    @Test
    public void testGetUserByIDs() throws Exception {
        int userId=1;
        User user = new User();
        user.setId(userId);
        // 模拟私有方法或者是 Final 方法时，依次指定模拟对象、被指定的函数名字以及针对该函数的输入参数列表
        PowerMockito.when(userService,"getUserByID",userId).thenReturn(user);
        List<User> list =  userService.getUserByIDs(userId);
        log.info("测试结果：{}，查询结果：{}",list.size()>0,JSONObject.toJSON(list));

        Assert.assertTrue(list.size()>0);
    }

}