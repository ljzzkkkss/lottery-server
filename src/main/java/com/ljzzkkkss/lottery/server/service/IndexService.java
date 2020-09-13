package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.Log;
import com.ljzzkkkss.lottery.server.model.User;

public interface IndexService {
    User findByUserName(String usersname);
    void insertLog(Log log);
}
