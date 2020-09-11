package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.OptionalDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionalDetailMapper {
    void insertOptionalDetail(OptionalDetail optionalDetail);
    List<OptionalDetail> getOptionalDetailByOptionalId(@Param("optionalId")Integer optionalId);
}
