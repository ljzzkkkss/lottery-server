package com.ljzzkkkss.lottery.server.service;

import com.ljzzkkkss.lottery.server.model.User;

public interface UserService {
    User findByUserName(String usersname);
}
