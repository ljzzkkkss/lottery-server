package com.ljzzkkkss.lottery.server.service.impl;

import com.ljzzkkkss.lottery.server.mapper.OptionalDetailMapper;
import com.ljzzkkkss.lottery.server.mapper.OptionalMapper;
import com.ljzzkkkss.lottery.server.model.Optional;
import com.ljzzkkkss.lottery.server.model.OptionalDetail;
import com.ljzzkkkss.lottery.server.model.OptionalParam;
import com.ljzzkkkss.lottery.server.service.OptionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OptionalServiceImpl implements OptionalService {
    @Resource
    private OptionalMapper optionalMapper;
    @Resource
    private OptionalDetailMapper optionalDetailMapper;

    @Override
    @Transactional
    public void addOptional(OptionalParam optionalParam) {
        Optional optional = optionalParam.getOptional();
        List<OptionalDetail> optionalDetailList = optionalParam.getOptionalDetailList();
        optionalMapper.insertOptional(optional);
        for(OptionalDetail optionalDetail : optionalDetailList){
            optionalDetail.setOptionalId(optional.getId());
            optionalDetailMapper.insertOptionalDetail(optionalDetail);
        }
    }
}
