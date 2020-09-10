package com.ljzzkkkss.lottery.server.model;

import java.io.Serializable;

public class Optional implements Serializable {
    private static final long serialVersionUID = 7201201657467771832L;

    private Integer id;
    private Integer userId;
    private String lotteryImg;
    private Integer status;
    private String name;
    private String optionDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLotteryImg() {
        return lotteryImg;
    }

    public void setLotteryImg(String lotteryImg) {
        this.lotteryImg = lotteryImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptionDate() {
        return optionDate;
    }

    public void setOptionDate(String optionDate) {
        this.optionDate = optionDate;
    }
}
