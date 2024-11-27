package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE note_id = #{noteId}")
    Note getNoteById(Long noteId);

    @Insert("INSERT INTO NOTES(note_title, note_description, user_id) VALUES (" +
            "#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Delete("DELETE FROM NOTES WHERE note_id = #{noteId}")
    void delete(Long noteId);

    @Delete("DELETE FROM NOTES")
    void deleteAll();

    @Update("UPDATE notes " +
            "SET note_title = #{noteTitle}, note_description = #{noteDescription} " +
            "WHERE note_id = #{noteId}")
    void update(@Param("noteTitle") String noteTitle,
               @Param("noteDescription") String noteDescription,
               @Param("noteId") Long noteId);
}