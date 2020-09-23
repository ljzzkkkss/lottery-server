package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.Optional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionalMapper {
    void insertOptional(Optional optional);
    List<Optional> getOptionalListByUserId(@Param("userId")Integer userId,@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    List<Optional> getPayedOptionalListByUserId(@Param("userId")Integer userId,@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    Optional getOptionalByOptionalId(@Param("id")Integer id);
    void confirmSendOptional(@Param("id")Integer id);
}
