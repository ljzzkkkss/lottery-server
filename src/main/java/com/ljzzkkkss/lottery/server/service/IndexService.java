package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.Log;
import com.ljzzkkkss.lottery.server.model.User;

public interface IndexService {
    User findByPhone(String phone);
    void insertLog(Log log);
    void updateLastLogin(User user);
    void updatePassword(User user);
    void insertUser(User user);
}
