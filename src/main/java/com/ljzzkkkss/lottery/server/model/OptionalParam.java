package com.ljzzkkkss.lottery.server.model;

import java.util.List;

public class OptionalParam {
    private Optional optional;
    private List<OptionalDetail> optionalDetailList;

    public Optional getOptional() {
        return optional;
    }

    public void setOptional(Optional optional) {
        this.optional = optional;
    }

    public List<OptionalDetail> getOptionalDetailList() {
        return optionalDetailList;
    }

    public void setOptionalDetailList(List<OptionalDetail> optionalDetailList) {
        this.optionalDetailList = optionalDetailList;
    }
}
