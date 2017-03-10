package me.bjxg.demo.mock.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.bjxg.demo.mock.user.dao.UserMapper;
import me.bjxg.demo.mock.user.model.User;
import me.bjxg.demo.mock.user.service.UserService;
import me.bjxg.demo.mock.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengcy on 2017/3/8.
 */
@Service
@Slf4j
@Setter
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public int addUser(User user) {
        if(user==null|| user.getUsername()==null){
            log.warn("参数异常：{}", JSONObject.toJSON(user));
            return  0;
        }

        return userMapper.insert(user);
    }

    @Override
    public List<User> getUserByIDs(int ...userIds) {
        if(userIds==null||userIds.length==0) {
            log.warn("查询参数异常：{}", userIds);
            return null;
        }

        List<User> list = new ArrayList();
        for(int userId:userIds){
            User user=getUserByID(userId);
            list.add(user);
        }

        return list;
    }

    @Override
    public  User getUserByUsername(String username) {
        if(Validator.isBlank(username)){
            log.warn("查询参数异常：{}", username);
            return null;
        }

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        List<User> list = userMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return  null;
        }

        return list.get(0);
    }


    private User getUserByID(int userId) {
        if(userId<=0) {
            log.warn("查询参数异常：{}", userId);
            return null;
        }

        return userMapper.selectByPrimaryKey(userId);
    }

}
