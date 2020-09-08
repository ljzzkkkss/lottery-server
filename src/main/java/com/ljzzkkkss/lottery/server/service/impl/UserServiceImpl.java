package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.UserMapper;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUserName(String usersname) {
        return userMapper.findByUserName(usersname);
    }
}
