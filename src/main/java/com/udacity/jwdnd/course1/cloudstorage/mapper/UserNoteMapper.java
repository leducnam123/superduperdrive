package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserNoteMapper {
    @Select("SELECT * FROM NOTES WHERE user_id = #{userId}")
    List<UserNote> getNotesByUserId(Long userId);

    @Insert("INSERT INTO NOTES (user_id, note_title, note_description) VALUES (#{userId}, #{noteTitle}, #{noteDescription})")
    void insertNoteByUserId(
            Long userId,
            String noteTitle,
            String noteDescription);
}