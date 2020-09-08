package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByUserName(@Param("username")String username);
}
