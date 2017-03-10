package me.bjxg.demo.mock.user.service;

import me.bjxg.demo.mock.user.model.User;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by chengcy on 2017/3/8.
 */
public interface UserService {
    int addUser(User record);

    List<User> getUserByIDs(int... userIds);

    User getUserByUsername(String username);
}
