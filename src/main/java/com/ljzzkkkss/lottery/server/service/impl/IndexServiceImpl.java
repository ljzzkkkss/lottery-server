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
    public User findByUserName(String usersname) {
        return userMapper.findByUserName(usersname);
    }

    @Override
    public void insertLog(Log log) {
        logMapper.insertLog(log);
    }
}
