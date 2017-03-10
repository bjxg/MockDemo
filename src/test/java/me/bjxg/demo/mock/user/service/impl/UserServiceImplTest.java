package me.bjxg.demo.mock.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.bjxg.demo.mock.user.dao.UserMapper;
import me.bjxg.demo.mock.user.model.User;
import me.bjxg.demo.mock.user.service.UserService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chengcy on 2017/3/9.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml"})
public class UserServiceImplTest {
    @Rule
    public PowerMockRule powerMockRule;

    @Autowired
    UserService userService;

    /**
     * 不使用mock的单测，执行了真实的入库操作
     * @throws Exception
     */
    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUsername("ccy");
        int count = userService.addUser(user);
        log.info("插入记录的数量：{},测试结果：{}",count,count==1);

        Assert.assertEquals(count,1);
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        String username = "ccy";
        User user = userService.getUserByUsername(username);
        log.info("查询记录：{},测试结果：{}", JSONObject.toJSON(user),user!=null);
        Assert.assertTrue(username.equals(user.getUsername()));

        user = userService.getUserByUsername(null);
        Assert.assertEquals(user,null);
    }

    /**
     * 使用DAO层Mock的单测，没有执行真实的入库操作
     * @throws Exception
     */
    @Test
    public void testAddUserMock() throws Exception {
        // mock userService 引用的userMapper对象的方法insert
        UserMapper userMapper = PowerMockito.mock(UserMapper.class);
        ((UserServiceImpl)userService).setUserMapper(userMapper);
        PowerMockito.when(userMapper.insert(Mockito.<User>any())).thenReturn(1);

        User user = new User();
        user.setUsername("ccy");
        int count = userService.addUser(user);
        log.info("插入记录的数量：{},mock测试结果：{}",count,count==1);

        Assert.assertEquals(count,1);
    }

}