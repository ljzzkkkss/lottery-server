package com.ljzzkkkss.lottery.server.model;

import java.io.Serializable;

public class Note implements Serializable {
    private static final long serialVersionUID = 555955273586910780L;

    private Long id;
    private Integer userId;
    private String content;
    private Integer optionalId;
    private String noteTime;

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

    public Integer getOptionalId() {
        return optionalId;
    }

    public void setOptionalId(Integer optionalId) {
        this.optionalId = optionalId;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
