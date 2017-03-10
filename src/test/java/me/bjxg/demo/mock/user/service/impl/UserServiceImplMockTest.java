package me.bjxg.demo.mock.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.bjxg.demo.mock.user.dao.UserMapper;
import me.bjxg.demo.mock.user.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengcy on 2017/3/9.
 */
@Slf4j
@PrepareForTest({UserServiceImpl.class,Example.class})
public class UserServiceImplMockTest {
    @Rule
    public PowerMockRule powerMockRule = new PowerMockRule();
    private UserServiceImpl userService = PowerMockito.spy(new UserServiceImpl());
    private UserMapper userMapper = Mockito.mock(UserMapper.class) ;

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
     * 构造方法的mock
     * @throws Exception
     */
    @Test
    public void testGetUserByUsername() throws Exception {
        final String username = "ccy";
        Example example = PowerMockito.mock(Example.class);
        PowerMockito.whenNew(Example.class).withArguments(User.class).thenReturn(example);
        Example.Criteria criteria = PowerMockito.mock(Example.Criteria.class);
        PowerMockito.when(example.createCriteria()).thenReturn(criteria);
        UserMapper userMapper = Mockito.mock(UserMapper.class) ;
        userService.setUserMapper(userMapper);
        PowerMockito.when(userMapper.selectByExample(Mockito.any())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                List<User> list = new ArrayList<>();
                User user = new User();
                user.setUsername(username);
                list.add(user);

                return list;
            }
        });


        User user = userService.getUserByUsername(username);
        log.info("查询记录：{},测试结果：{}", JSONObject.toJSON(user),user!=null);
        Assert.assertTrue(username.equals(user.getUsername()));
        // 异常参数测试
        user = userService.getUserByUsername(null);
        Assert.assertEquals(user,null);
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