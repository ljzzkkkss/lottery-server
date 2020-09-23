package com.ljzzkkkss.lottery.server.mapper;

import com.ljzzkkkss.lottery.server.model.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteMapper {
    List<Note> getNoteListByPage(@Param("userId")Integer userId,@Param("start") Integer start, @Param("pageSize") Integer pageSize);
    void insertNote(Note note);
    String getReply();
}
