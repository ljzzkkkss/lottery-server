package com.ljzzkkkss.lottery.server.model;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private static final long serialVersionUID = 3204195509891615080L;

    private Long id;
    private String content;
    private String phone;
    private Date logTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
