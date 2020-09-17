package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.LogMapper;
import com.ljzzkkkss.lottery.server.mapper.UserMapper;
import com.ljzzkkkss.lottery.server.model.Log;
import com.ljzzkkkss.lottery.server.model.User;
import com.ljzzkkkss.lottery.server.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexServiceImpl implements IndexService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LogMapper logMapper;

    @Override
    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }

    @Override
    public void insertLog(Log log) {
        logMapper.insertLog(log);
    }

    @Override
    public void updateLastLogin(User user) {
        userMapper.updateLastLogin(user);
    }

    @Override
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
