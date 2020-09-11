package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.Optional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionalMapper {
    void insertOptional(Optional optional);
    List<Optional> getOptionalListByUserId(@Param("userId")Integer userId);
    Optional getOptionalByOptionalId(@Param("id")Integer id);
}
