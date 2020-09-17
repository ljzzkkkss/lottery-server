package com.ljzzkkkss.lottery.server.model;

import java.io.Serializable;

public class Banner implements Serializable {
    private static final long serialVersionUID = 8343823525576170733L;

    private String img;
    private String url;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
