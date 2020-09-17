package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByPhone(@Param("phone")String phone);
    void updatePassword(User user);
    void updateLastLogin(User user);
    void insertUser(User user);
}
